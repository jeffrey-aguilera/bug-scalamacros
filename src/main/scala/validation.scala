
package validation

import language.higherKinds
import language.implicitConversions
import language.experimental.macros

//--[[Issue]]-----------------------------------------------------------------------------------------------------------

trait Issue

final case class Error(reason: Throwable) extends Issue {
  override def toString: String = {
    reason.getMessage
  }
}

//--[[Validated]]-------------------------------------------------------------------------------------------------------

sealed trait Validated[+T] {
  def issues: List[Issue]
  def foreach[U](f: T => U): Unit
  def map[U](f: T => U): Validated[U]
  def flatMap[U](f: T => Validated[U]): Validated[U]
}

/** [[Valid]] represents a [[Validated]] computation that carries a `value` of the proper type. */
final case class Valid[+T](value: T) extends Validated[T] {
  def issues: List[Issue] = Nil
  def foreach[U](f: T => U): Unit = f(value)
  def map[U](f: T => U): Validated[U] = Validated(f(value)) // captures exceptions
  def flatMap[U](f: T => Validated[U]): Validated[U] = f(value)
}

/** [[Issues]] represents a [[Validated]] computation that failed to produce a `value` of the proper type. */
final case class Issues(issues: List[Issue]) extends Validated[Nothing] {
  def foreach[U](f: Nothing => U): Unit = ()
  def map[U](f: Nothing => U): Validated[Nothing] = this
  def flatMap[U](f: Nothing => Validated[U]): Validated[Nothing] = this
}

object Validated {
  def apply[T](t: => T): Validated[T] = {
    try Valid(t) catch {
      case scala.util.control.NonFatal(reason) =>
        Issues(List(Error(reason)))
    }
  }
}

//--[[Validator]]-------------------------------------------------------------------------------------------------------

trait Validator[+T] extends (Data => Validated[T]) {
  def apply(data: Data): Validated[T] = ??? // declare to make IDE happy
}

//--[[Data]]------------------------------------------------------------------------------------------------------------

trait Data {

  final def apply[T: Validator](key: String): Validated[T] = at(key).flatMap(implicitly[Validator[T]])

  def at(key: String): Validated[Data]

  // conversions to primitive types
  def asBoolean: Validated[Boolean]
  def asNumber: Validated[Number]
  def asString: Validated[String]
}

object Data {

  implicit object BooleanValidator extends Validator[Boolean] {
    override def apply(data: Data): Validated[Boolean] = data.asBoolean
  }

  implicit object NumberValidator extends Validator[Number] {
    override def apply(data: Data): Validated[Number] = data.asNumber
  }

  implicit object StringValidator extends Validator[String] {
    override def apply(data: Data): Validated[String] = data.asString
  }
}

//--[[boilerplate]]-----------------------------------------------------------------------------------------------------

@annotation.compileTimeOnly("enable macro paradise to expand macro annotations")
class boilerplate extends annotation.StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro boilerplate.transform
}

object boilerplate {
  import scala.reflect.macros.whitebox

  val usage = "@validation.boilerplate can only be applied to an object that extends Validator[T] for some type T"

  def transform(context: whitebox.Context)(annottees: context.Expr[Any]*): context.Expr[Any] = {
    import context.universe._

    def warn(message: String): Unit = context.warning(context.enclosingPosition, message)
    def abort(message: String): Nothing = context.abort(context.enclosingPosition, message)

    def typecheck(tree: Tree): Type = context.typecheck(tree, context.TYPEmode, silent = true).tpe

    // find potential Validator[T] targets, but only look at direct parents due to nasty typechecker problems
    def targets(parents: List[Tree]): List[Type] = {
      val options =
        for (parent <- parents) yield {
          typecheck(parent) match {
            case TypeRef(_, sym, args) if sym.fullName == "validation.Validator" && args.length == 1 =>
              Some(args.head)
            case NoType =>
              warn(s"cannot typecheck $parent; see: https://github.com/scalamacros/paradise/issues/14")
              None
            case _ =>
              None
          }
        }

      // should only be one target due to erasure restrictions
      options.collect{ case Some(target) => target }.distinct
    }

    // need body of expansion context (to override defaults) and target type (to know what's being built)
    def injection(body: List[Tree], target: Type): Tree = {

      /*
        override def apply(data: Data): Validated[T] = {
          import validation._

          // data bindings (3 distinct forms)
          val _1: Validated[T1] = data[T1]("name1")
          val _2: Validated[T2] = data[T2]("name2")
          ...

          // sweep up issues
          val issues = {
            val builder = mutable.ListBuffer[Issue]()
            builder ++= _1.issues
            builder ++= _2.issues
            ...
            builder.toList
          }

          // validate bindings
          if (issues.isEmpty)
            Validated(new T(_1.asInstanceOf[Valid[T1]].value, ...) {...})
          else
            Issues(issues)
        }
       */

      // extract constructor (term:type) pairs from target
      val ctors =
        target.member(termNames.CONSTRUCTOR).info match {
          case MethodType(params, TypeRef(_, sym, Nil)) if sym == target.typeSymbol =>
            params.map(sym => sym.asTerm.name -> sym.info)
          case NoType =>
            Nil
          case _ =>
            abort(s"$target constructor is curried or otherwise unsupported")
        }

      // extract abstract nullary types from target type (def term:type) that need to be defined
      val methods =
        (for (member <- target.members if member.isAbstract) yield {
          member.info match {
            case NullaryMethodType(tpe) =>
              Some(member.asTerm.name -> tpe)
            case _ =>
              None
          }
        }).collect{ case Some(pair) => pair }.toList

      // (term:type) pairs that need to be bound to data in either constructor parameters or class body
      val vals = ctors ++ methods

      // assign unique integer to each term
      val indices = vals.map(_._1).zipWithIndex.toMap

      // data[type](name)
      def data(trm: TermName, typ: Type): Tree = {
        val name = trm.decodedName.toString
        q"data[$typ]($name)"
      }

      // val _n: Validated[type] = data[type](name)
      def valdefs =
        for ((trm, typ) <- vals) yield {
          val idx = TermName(s"_${indices(trm)}")
          val rhs = data(trm, typ)
          q"val $idx: Validated[$typ] = $rhs"
        }

      // val issues = _1.issues ++ _2.issues ++ ... (basically)
      def issues = {
        def statements = {
          q"val builder = _root_.scala.collection.mutable.ListBuffer[Issue]()" +:
            (for (i <- vals.indices) yield {
              val trm = TermName(s"_$i")
              q"builder ++= $trm.issues"
            })
        }

        q"val issues = { ..$statements; builder.toList }"
      }

      // new T(...) {...}
      val constructor: Tree = {
        // name = _n.asInstanceOf[Valid[Tn]].value
        val parameters =
          for (ctor <- ctors) yield {
            val (trm, typ) = ctor
            val idx = TermName(s"_${indices(trm)}")
            q"$trm = $idx.asInstanceOf[Valid[$typ]].value" // "$trm =" fragment is for sanity purposes
          }
        // override lazy val name: type = _n.asInstanceOf[Valid[Tn]].value
        val overrides =
          for (method <- methods) yield {
            val (trm, typ) = method
            val idx = TermName(s"_${indices(trm)}")
            q"override val $trm: $typ = $idx.asInstanceOf[Valid[$typ]].value"
          }

        if (overrides.isEmpty && !target.typeSymbol.isAbstract)
          q"new $target(..$parameters)"
        else {

          // -----------------------------------------------------------------------------------------------------------
          //
          // This is converted to a block: final class $anon ...; new $anon()
          //
          // But ClassDef in this block does not have a symbol, causing an assertion failure in Typers.scala.
          //
          // -----------------------------------------------------------------------------------------------------------

          q"new $target(..$parameters) { ..$overrides }"
        }
      }

      val body = q"..$valdefs; ..$issues; if (issues.isEmpty) Validated(..$constructor) else Issues(issues)"

      q"override def apply(data: Data): Validated[$target] = $body"
    }

    def imports: Tree = {
      q"import _root_.validation._"
    }

    // see: http://docs.scala-lang.org/overviews/macros/annotations.html
    annottees.map(_.tree).toList match {
      case ModuleDef(modifiers, name, Template(parents, self, body)) :: Nil =>
        targets(parents) match {
          case target :: Nil =>
            // inject a single "override def apply(data: Data): Validated[T] = {...}" method
            context.Expr[Any](ModuleDef(modifiers, name, Template(parents, self, imports +: injection(body, target) +: body)))
          case _ =>
            abort(s"$usage: could not find T")
        }

      case _ =>
        // annotation applied to `val`, `type`, or `class`
        abort(usage)
    }
  }
}
