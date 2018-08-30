import sbt._

object Dependencies {
  lazy val circeVersion = "0.6.1"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val circeCore = "io.circe" %% "circe-core" % circeVersion
  lazy val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
  lazy val circeParser = "io.circe" %% "circe-parser" % circeVersion
  lazy val mysql = "mysql" % "mysql-connector-java" % "5.1.24"
}
