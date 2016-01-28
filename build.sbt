// build.sbt:

name := "bug-scalamacros"
version := "0.1"

scalaVersion := "2.11.7" 	// jvm-1.7 and jvm-1.8: Typers.scala:1759
//scalaVersion := "2.12.0-M3"	// jvm-1.8: Typers.scala:1748

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:_",
//"-target:jvm-1.7")
  "-target:jvm-1.8")

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
autoCompilerPlugins := true

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)
