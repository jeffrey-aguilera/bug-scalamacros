# project bug-scalamacros

This project demonstrates a Typers.scala assertion error with a quasiquote generated inner class.

 * `sbt compile` 	--- compiles macro
 * `sbt test:compile` 	--- attempts compilation of object annotated with macro 

The problem has been reproduced for Scala 2.11.7 and 2.12.0-M3 generating 1.7 and 1.8 bytecode.
For Scala 2.11.7, the assertion fails on Typers.scala:1759; and for Scala 2.12.0-M3, it fails on
Typers.scala:1748.
