import Dependencies._

lazy val todoFinagle = (project in file("."))
  .aggregate(server, client)

lazy val server = (project in file("server"))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "finagleTestServer",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.twitter" %% "finagle-http" % "18.6.0"
    )
  )
lazy val client = (project in file("client"))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "finagleTestServer",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.twitter" %% "finagle-http" % "18.6.0"
    )
  )
