libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
flywayUrl := sys.env.getOrElse("DB_URL,_TODO, ""jdbc:mysql://127.0.0.1:3306")
flywayUser := sys.env.getOrElse("DB_RW_USER", "migrate")
flywayPassword := sys.env.getOrElse("DB_RW_PASSWORD", "migratepasswd")
flywayLocations += Seq("filesystem:schema/todo/src/resources/schema/migration")