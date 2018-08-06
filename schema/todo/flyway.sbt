libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
flywayUrl := sys.env.getOrElse("DB_URL_TODO", "jdbc:mysql://127.0.0.1:3306/main")
flywayUser := sys.env.getOrElse("DB_RW_USER", "migrate")
flywayPassword := sys.env.getOrElse("DB_RW_PASSWORD", "migratepasswd")
flywayLocations := Seq("filesystem:schema/todo/src/main/resources/schema/migration")