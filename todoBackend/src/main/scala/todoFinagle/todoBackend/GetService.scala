package todofinagle.todobackend

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Duration, Future, JavaTimer}
import io.circe._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._
import todofinagle.infra.db.mysqlDb.TodoRepositoryOnSql

class GetService() extends Service[http.Request, http.Response] {
  override def apply(req: http.Request): Future[http.Response] = {
    Future.value {
      val response = http.Response(req.version, http.Status.Ok)
      val todoRepo = new TodoRepositoryOnSql()
      val todos = todoRepo.getAllTodos()
      val res = todos.asJson.noSpaces
      response.setContentString(res)
      response
    }
  }
}