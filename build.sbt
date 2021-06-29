name := "scala-playground"

version := "0.1"

scalaVersion := "2.13.0"

val scalazVersion = "7.3.3"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",

  "org.scalaz" %% "scalaz-core" % scalazVersion
)