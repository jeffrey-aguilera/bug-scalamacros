
import language.implicitConversions

import validation._

trait Trait {
  def name: String
}

object Model {

  /* -------------------------------------------------------------------------------------------------------------------

Error:scalac: Error: assertion failed:
  final class $anon extends Trait() {
  def <init>() = {
    super.<init>();
    ()
  };
  override val name: String = _0.asInstanceOf[Valid[String]].value
}
     while compiling: /Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/src/test/scala/validation.scala
        during phase: typer
     library version: version 2.11.7
    compiler version: version 2.11.7
  reconstructed args: -Ywarn-nullary-unit -javabootclasspath : -classpath /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/javafx-doclet.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/tools.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/htmlconverter.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/target/scala-2.11/test-classes:/Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/target/scala-2.11/classes:/Users/jeffrey/.ivy2/cache/org.scala-lang/scala-compiler/jars/scala-compiler-2.11.7.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang/scala-library/jars/scala-library-2.11.7.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang/scala-reflect/jars/scala-reflect-2.11.7.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang.modules/scala-parser-combinators_2.11/bundles/scala-parser-combinators_2.11-1.0.4.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang.modules/scala-xml_2.11/bundles/scala-xml_2.11-1.0.4.jar -language:dynamics -language:postfixOps -language:reflectiveCalls -language:implicitConversions -language:higherKinds -language:existentials -language:experimental.macros -feature -Ywarn-inaccessible -unchecked -nobootcp -deprecation -Xlint:adapted-args -Xlint:nullary-unit -Xlint:inaccessible -Xlint:nullary-override -Xlint:infer-any -Xlint:missing-interpolator -Xlint:doc-detached -Xlint:private-shadow -Xlint:type-parameter-shadow -Xlint:poly-implicit-overload -Xlint:option-implicit -Xlint:delayedinit-select -Xlint:by-name-right-associative -Xlint:package-object-classes -Xlint:unsound-match -Xlint:stars-align -Xplugin:/Users/jeffrey/.ivy2/cache/org.scalamacros/paradise_2.11.7/jars/paradise_2.11.7-2.1.0.jar -Ywarn-nullary-override -explaintypes -Ywarn-dead-code -target:jvm-1.7
  last tree to typer: type $anon
       tree position: line 13 of /Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/src/test/scala/validation.scala
              symbol: <none>
   symbol definition: <none> (a NoSymbol)
      symbol package: <none>
       symbol owners:
           call site: <none> in <none>
== Source file context for tree position ==
    10
    11   // bug: assertion failed at Typer:
    12   @validation.boilerplate implicit object ImplicitTraitValidator extends Validator[Trait]
    13
    14   // this is the code that the macro generates
    15   object ExplicitTraitValidator extends Validator[Trait] {
    16     override def apply(data: Data): Validated[Trait] = {
java.lang.AssertionError: assertion failed:
  final class $anon extends Trait() {
  def <init>() = {
    super.<init>();
    ()
  };
  override val name: String = _0.asInstanceOf[Valid[String]].value
}
     while compiling: /Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/src/test/scala/validation.scala
        during phase: typer
     library version: version 2.11.7
    compiler version: version 2.11.7
  reconstructed args: -Ywarn-nullary-unit -javabootclasspath : -classpath /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/javafx-doclet.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/lib/tools.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/htmlconverter.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/target/scala-2.11/test-classes:/Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/target/scala-2.11/classes:/Users/jeffrey/.ivy2/cache/org.scala-lang/scala-compiler/jars/scala-compiler-2.11.7.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang/scala-library/jars/scala-library-2.11.7.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang/scala-reflect/jars/scala-reflect-2.11.7.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang.modules/scala-parser-combinators_2.11/bundles/scala-parser-combinators_2.11-1.0.4.jar:/Users/jeffrey/.ivy2/cache/org.scala-lang.modules/scala-xml_2.11/bundles/scala-xml_2.11-1.0.4.jar -language:dynamics -language:postfixOps -language:reflectiveCalls -language:implicitConversions -language:higherKinds -language:existentials -language:experimental.macros -feature -Ywarn-inaccessible -unchecked -nobootcp -deprecation -Xlint:adapted-args -Xlint:nullary-unit -Xlint:inaccessible -Xlint:nullary-override -Xlint:infer-any -Xlint:missing-interpolator -Xlint:doc-detached -Xlint:private-shadow -Xlint:type-parameter-shadow -Xlint:poly-implicit-overload -Xlint:option-implicit -Xlint:delayedinit-select -Xlint:by-name-right-associative -Xlint:package-object-classes -Xlint:unsound-match -Xlint:stars-align -Xplugin:/Users/jeffrey/.ivy2/cache/org.scalamacros/paradise_2.11.7/jars/paradise_2.11.7-2.1.0.jar -Ywarn-nullary-override -explaintypes -Ywarn-dead-code -target:jvm-1.7
  last tree to typer: type $anon
       tree position: line 13 of /Users/jeffrey/Code/jeffrey-aguilera/bug-scalamacros/src/test/scala/validation.scala
              symbol: <none>
   symbol definition: <none> (a NoSymbol)
      symbol package: <none>
       symbol owners:
           call site: <none> in <none>
== Source file context for tree position ==
    10
    11   // bug: assertion failed at Typer:
    12   @validation.boilerplate implicit object ImplicitTraitValidator extends Validator[Trait]
    13
    14   // this is the code that the macro generates
    15   object ExplicitTraitValidator extends Validator[Trait] {
    16     override def apply(data: Data): Validated[Trait] = {
	at scala.tools.nsc.typechecker.Typers$Typer.typedClassDef(Typers.scala:1759)
	at scala.tools.nsc.typechecker.Typers$Typer.typedMemberDef$1(Typers.scala:5308)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5358)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typedArg(Typers.scala:3164)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedArgToPoly$1(Typers.scala:3515)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$75.apply(Typers.scala:3523)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$75.apply(Typers.scala:3523)
	at scala.reflect.internal.util.Collections$class.map2(Collections.scala:79)
	at scala.reflect.internal.SymbolTable.map2(SymbolTable.scala:16)
	at scala.tools.nsc.typechecker.Typers$Typer.handlePolymorphicCall$1(Typers.scala:3523)
	at scala.tools.nsc.typechecker.Typers$Typer.doTypedApply(Typers.scala:3534)
	at scala.tools.nsc.typechecker.Typers$Typer.normalTypedApply$1(Typers.scala:4545)
	at scala.tools.nsc.typechecker.Typers$Typer.typedApply$1(Typers.scala:4579)
	at scala.tools.nsc.typechecker.Typers$Typer.typedInAnyMode$1(Typers.scala:5342)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5359)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typedArg(Typers.scala:3164)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedArgToPoly$1(Typers.scala:3515)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$75.apply(Typers.scala:3523)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$75.apply(Typers.scala:3523)
	at scala.reflect.internal.util.Collections$class.map2(Collections.scala:79)
	at scala.reflect.internal.SymbolTable.map2(SymbolTable.scala:16)
	at scala.tools.nsc.typechecker.Typers$Typer.handlePolymorphicCall$1(Typers.scala:3523)
	at scala.tools.nsc.typechecker.Typers$Typer.doTypedApply(Typers.scala:3534)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$tryTupleApply$1$1.apply(Typers.scala:3335)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$tryTupleApply$1$1.apply(Typers.scala:3335)
	at scala.tools.nsc.typechecker.Typers$Typer.silent(Typers.scala:680)
	at scala.tools.nsc.typechecker.Typers$Typer.tryTupleApply$1(Typers.scala:3335)
	at scala.tools.nsc.typechecker.Typers$Typer.tryNamesDefaults$1(Typers.scala:3367)
	at scala.tools.nsc.typechecker.Typers$Typer.doTypedApply(Typers.scala:3453)
	at scala.tools.nsc.typechecker.Typers$Typer.normalTypedApply$1(Typers.scala:4545)
	at scala.tools.nsc.typechecker.Typers$Typer.typedApply$1(Typers.scala:4579)
	at scala.tools.nsc.typechecker.Typers$Typer.typedInAnyMode$1(Typers.scala:5342)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5359)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5462)
	at scala.tools.nsc.typechecker.Typers$Typer.typedIf$1(Typers.scala:4254)
	at scala.tools.nsc.typechecker.Typers$Typer.typedOutsidePatternMode$1(Typers.scala:5318)
	at scala.tools.nsc.typechecker.Typers$Typer.typedInAnyMode$1(Typers.scala:5352)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5359)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typedBlock(Typers.scala:2382)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$typedOutsidePatternMode$1$1.apply(Typers.scala:5317)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$typedOutsidePatternMode$1$1.apply(Typers.scala:5317)
	at scala.tools.nsc.typechecker.Typers$Typer.typedOutsidePatternMode$1(Typers.scala:5316)
	at scala.tools.nsc.typechecker.Typers$Typer.typedInAnyMode$1(Typers.scala:5352)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5359)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.transformedOrTyped(Typers.scala:5604)
	at scala.tools.nsc.typechecker.Typers$Typer.typedDefDef(Typers.scala:2208)
	at scala.tools.nsc.typechecker.Typers$Typer.typedMemberDef$1(Typers.scala:5307)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5358)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typedByValueExpr(Typers.scala:5451)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedStat$1(Typers.scala:3047)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$65.apply(Typers.scala:3151)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$65.apply(Typers.scala:3151)
	at scala.collection.immutable.List.loop$1(List.scala:173)
	at scala.collection.immutable.List.mapConserve(List.scala:189)
	at scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3151)
	at scala.tools.nsc.typechecker.Typers$Typer.typedTemplate(Typers.scala:1921)
	at scala.tools.nsc.typechecker.Typers$Typer.typedModuleDef(Typers.scala:1808)
	at scala.tools.nsc.typechecker.Typers$Typer.typedMemberDef$1(Typers.scala:5309)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5358)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typedByValueExpr(Typers.scala:5451)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedStat$1(Typers.scala:3047)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$65.apply(Typers.scala:3151)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$65.apply(Typers.scala:3151)
	at scala.collection.immutable.List.loop$1(List.scala:173)
	at scala.collection.immutable.List.mapConserve(List.scala:189)
	at scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3151)
	at scala.tools.nsc.typechecker.Typers$Typer.typedTemplate(Typers.scala:1921)
	at scala.tools.nsc.typechecker.Typers$Typer.typedModuleDef(Typers.scala:1808)
	at scala.tools.nsc.typechecker.Typers$Typer.typedMemberDef$1(Typers.scala:5309)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5358)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typedByValueExpr(Typers.scala:5451)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedStat$1(Typers.scala:3047)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$65.apply(Typers.scala:3151)
	at scala.tools.nsc.typechecker.Typers$Typer$$anonfun$65.apply(Typers.scala:3151)
	at scala.collection.immutable.List.loop$1(List.scala:173)
	at scala.collection.immutable.List.mapConserve(List.scala:189)
	at scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3151)
	at scala.tools.nsc.typechecker.Typers$Typer.typedPackageDef$1(Typers.scala:5014)
	at scala.tools.nsc.typechecker.Typers$Typer.typedMemberDef$1(Typers.scala:5311)
	at scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5358)
	at scala.tools.nsc.typechecker.Typers$Typer.runTyper$1(Typers.scala:5395)
	at scala.tools.nsc.typechecker.Typers$Typer.scala$tools$nsc$typechecker$Typers$Typer$$typedInternal(Typers.scala:5422)
	at scala.tools.nsc.typechecker.Typers$Typer.body$2(Typers.scala:5369)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5373)
	at scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5447)
	at scala.tools.nsc.typechecker.Analyzer$typerFactory$$anon$3.apply(Analyzer.scala:102)
	at scala.tools.nsc.Global$GlobalPhase$$anonfun$applyPhase$1.apply$mcV$sp(Global.scala:440)
	at scala.tools.nsc.Global$GlobalPhase.withCurrentUnit(Global.scala:431)
	at scala.tools.nsc.Global$GlobalPhase.applyPhase(Global.scala:440)
	at scala.tools.nsc.typechecker.Analyzer$typerFactory$$anon$3$$anonfun$run$1.apply(Analyzer.scala:94)
	at scala.tools.nsc.typechecker.Analyzer$typerFactory$$anon$3$$anonfun$run$1.apply(Analyzer.scala:93)
	at scala.collection.Iterator$class.foreach(Iterator.scala:742)
	at scala.collection.AbstractIterator.foreach(Iterator.scala:1194)
	at scala.tools.nsc.typechecker.Analyzer$typerFactory$$anon$3.run(Analyzer.scala:93)
	at scala.tools.nsc.Global$Run.compileUnitsInternal(Global.scala:1501)
	at scala.tools.nsc.Global$Run.compileUnits(Global.scala:1486)
	at scala.tools.nsc.Global$Run.compileSources(Global.scala:1481)
	at scala.tools.nsc.Global$Run.compile(Global.scala:1582)
	at xsbt.CachedCompiler0.run(CompilerInterface.scala:115)
	at xsbt.CachedCompiler0.run(CompilerInterface.scala:94)
	at xsbt.CompilerInterface.run(CompilerInterface.scala:22)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at sbt.compiler.AnalyzingCompiler.call(AnalyzingCompiler.scala:101)
	at sbt.compiler.AnalyzingCompiler.compile(AnalyzingCompiler.scala:47)
	at sbt.compiler.AnalyzingCompiler.compile(AnalyzingCompiler.scala:41)
	at org.jetbrains.jps.incremental.scala.local.IdeaIncrementalCompiler.compile(IdeaIncrementalCompiler.scala:29)
	at org.jetbrains.jps.incremental.scala.local.LocalServer.compile(LocalServer.scala:26)
	at org.jetbrains.jps.incremental.scala.remote.Main$.make(Main.scala:67)
	at org.jetbrains.jps.incremental.scala.remote.Main$.nailMain(Main.scala:24)
	at org.jetbrains.jps.incremental.scala.remote.Main.nailMain(Main.scala)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at com.martiansoftware.nailgun.NGSession.run(NGSession.java:319)
  ------------------------------------------------------------------------------------------------------------------- */

  @validation.boilerplate implicit object ImplicitTraitValidator extends Validator[Trait]

  // this is the code that the macro generates ... it compiles fine
  object ExplicitTraitValidator extends Validator[Trait] {
    override def apply(data: Data): Validated[Trait] = {
      import validation._

      val _1 = data[String]("name")

      val issues = {
        val builder = scala.collection.mutable.ListBuffer[Issue]()
        builder ++= _1.issues
        builder.toList
      }

      if (issues.isEmpty)
        Validated(new Trait() {
          val name: String = _1.asInstanceOf[Valid[String]].value
        })
      else
        Issues(issues)
    }
  }
}
