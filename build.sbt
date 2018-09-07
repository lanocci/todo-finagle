import Dependencies._

lazy val todoFinagle = (project in file("."))
  .aggregate(todobackend, model)

lazy val todobackend = (project in file("todobackend"))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "todo-backend",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      circeCore,
      circeGeneric,
      circeParser,
      "com.twitter" %% "finagle-http" % "18.6.0"
    )
  )
  .dependsOn(
    model,
    infraMysql
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

lazy val infraMysql = (project in file("infra/db/mysqlDb"))
  .settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "infra-mysql",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      mysql,
      "org.scalikejdbc" %% "scalikejdbc" % "3.3.0",
      "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.0"
    )
  )
  .dependsOn(
    model
  )

lazy val schemaTodo = (project in file("schema/todo"))
  .settings(
    name := "schema-todo"
  )
  .enablePlugins(FlywayPlugin)