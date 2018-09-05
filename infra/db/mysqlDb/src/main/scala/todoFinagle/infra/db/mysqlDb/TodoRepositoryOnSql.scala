package todofinagle.infra.db.mysqlDb

import scalikejdbc._
import scalikejdbc.config._
import todofinagle.model.TodoRepository

// DB にアクセスして Todo のリストを返す実装

class TodoRepositoryOnSql(db: DB) {
  val allTodos = {
    DB readOnly {
      implicit session =>
        sql"select * from todos".map(_.long(1)).list.apply()
    }
  }
}

object TodoRepositoryOnSql {
  DBs.setupAll()
  def apply() = new TodoRepositoryOnSql(DB)
}