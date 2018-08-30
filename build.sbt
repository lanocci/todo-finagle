import Dependencies._

lazy val todoFinagle = (project in file("."))
  .aggregate(todoBackend, model)

lazy val todoBackend = (project in file("todoBackend"))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "todoBackend",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      circeCore,
      circeGeneric,
      circeParser,
      mysql,
      "com.twitter" %% "finagle-http" % "18.6.0",
      "org.scalikejdbc" %% "scalikejdbc" % "3.3.0"
    )
  )
  .dependsOn(
    model
  )
lazy val model = (project in file("model"))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "model",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.twitter" %% "finagle-http" % "18.6.0",
    )
  )

lazy val schemaTodo = (project in file("schema/todo"))
  .settings(
    name := "schema-todo"
  )
  .enablePlugins(FlywayPlugin)