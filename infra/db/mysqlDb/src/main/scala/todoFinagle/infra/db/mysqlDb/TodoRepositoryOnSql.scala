package todofinagle.infra.db.mysqlDb

import scalikejdbc._
import scalikejdbc.config._
import todofinagle.model.Todo

// DB にアクセスして Todo のリストを返す実装

class TodoRepositoryOnSql() {

  def getAllTodos(): Seq[Todo] = {
    // これだとアプリケーション起動時のDBの状態をずっと維持しちゃうかも？？
    DBs.setupAll()
    val allTodos = {
      DB readOnly {
        implicit session =>
          sql"select id, title, completed from todos".map(rs => Todo(rs.int("id"), rs.string("title"), rs.boolean("completed"))).list.apply()
      }
    }
    DBs.close()
    return allTodos
  }
}