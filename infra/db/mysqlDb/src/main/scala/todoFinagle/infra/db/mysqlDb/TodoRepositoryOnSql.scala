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
          sql"select id, title, completed, user_id from todos".map(rs => Todo(rs.int("id"), rs.string("title"), rs.boolean("completed"), rs.int("user_id"))).list.apply()
      }
    }
    DBs.close()
    return allTodos
  }

  def create(todo: Todo) {
    DBs.setupAll()
    val id = DB autoCommit { implicit s =>
      val (title, completed, user_id) = (todo.title, todo.completed, todo.user_id)
      sql"insert into todos (title, completed, user_id) values (${title}, ${completed}, ${user_id})".updateAndReturnGeneratedKey.apply()
    }
    DBs.close()
  }
}