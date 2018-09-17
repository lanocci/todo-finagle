package todofinagle.todobackend

import java.util.UUID
import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Duration, Future, JavaTimer}
import io.circe._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._
import todofinagle.model.Todo
import todofinagle.infra.db.mysqlDb.TodoRepositoryOnSql

object TodoBackend extends App {
  // val getAllTodosService = new Service[http.Request, http.Response] {
  //   def apply(req: http.Request): Future[http.Response] = {
  //     Future.value {
  //       val response = http.Response(req.version, http.Status.Ok)
  //       val todoRepo = new TodoRepositoryOnSql()
  //       val todos = todoRepo.getAllTodos()
  //       val res = todos.asJson.noSpaces
  //       response.setContentString(res)
  //       response
  //     }
  //   }
  // }
  // val addTodoService = new Service[http.Request, http.Response] {
  //   def apply(req: http.Request): Future[http.Response] = {
  //     Future.value {
  //       val response = http.Response(req.version, http.Status.Ok)
  //       val todoRepo = new TodoRepositoryOnSql()
  //       todoRepo.create()
  //       val todos = todoRepo.getAllTodos()
  //       val res = todos.asJson.noSpaces
  //       response.setContentString(res)
  //       response
  //     }
  //   }
  // }
  // val router = RoutingService.byPathObject[Request] {
  //   case Root / "todos" => getAllTodosService
  //   case Root / "todos"/ "create" => addTodoSerivce
  //   case _ => blackHole
  // }
  val todoService = new TodoService()

  val timeoutFilter = new TimeoutFilter[http.Request, http.Response](Duration.fromNanoseconds(1), new JavaTimer(false))
  val serviceWithTimeout = timeoutFilter.andThen(todoService)
  val server = Http.serve(":8081", serviceWithTimeout)
  // val server = ServerBuilder()
  //   .codec(HTTP())
  //   .bindTo(new InetSocketService(8080))
  //   .name("example")
  //   .build(service)
  Await.ready(server)
}