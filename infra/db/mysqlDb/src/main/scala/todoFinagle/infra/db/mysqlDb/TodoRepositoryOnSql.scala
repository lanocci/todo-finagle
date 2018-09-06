package todofinagle.infra.db.mysqlDb

import scalikejdbc._
import scalikejdbc.config._
import todofinagle.model.TodoRepository

// DB にアクセスして Todo のリストを返す実装

class TodoRepositoryOnSql(db: DB) {

  // これだとアプリケーション起動時のDBの状態をずっと維持しちゃうかも？？
  val allTodos = {
    DB readOnly {
      implicit session =>
        sql"select * from todos".map(Todo(todo)).list.apply()
    }
  }
}

object TodoRepositoryOnSql {
  DBs.setupAll()
  def apply() = new TodoRepositoryOnSql(DB)
}