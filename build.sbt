import Dependencies._

lazy val todoFinagle = (project in file("."))
  .aggregate(server, client, backend)

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

lazy val backend = (project in file("backend"))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "todoBackend",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.twitter" %% "finagle-http" % "18.6.0",
      "org.postgresql" %% "postgresql" % "42.1.4"
    )
  )
