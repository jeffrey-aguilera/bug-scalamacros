// build.sbt:

name := "bug-scalamacros"
version := "0.1"

javaOptions += "-Xmx4G"
javacOptions ++= Seq(
  "-source", "1.7",
  "-target", "1.7")
scalaVersion := "2.11.7"
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-explaintypes",
  "-feature",
  "-language:_",
//"-optimize",
  "-target:jvm-1.7",
  "-unchecked",
//"-verbose",
  "-Xlint:_",
//"-Ymacro-debug-verbose", // not as useful as I had hoped
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit")

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
autoCompilerPlugins := true

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % "2.11.7",
  "org.scala-lang" % "scala-reflect" % "2.11.7"
)
