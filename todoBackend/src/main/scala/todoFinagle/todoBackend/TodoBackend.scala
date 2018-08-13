package todoFinagle.todoBackend

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Duration, Future, JavaTimer}
import io.circe._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._

object TodoBackend extends App {
  val service = new Service[http.Request, http.Response] {
    def apply(req:http.Request): Future[http.Response] = {
      Future.value {
        val response = http.Response(req.version, http.Status.Ok)
        val todo = new Todo(1, "test", false)
        val res = todo.asJson.noSpaces
        response.setContentString(res)
        response
      }
    }
  }
  val timeoutFilter = new TimeoutFilter[http.Request, http.Response](Duration.fromNanoseconds(1), new JavaTimer(false))
  val serviceWithTimeout = timeoutFilter.andThen(service)
  val server = Http.serve(":8081", serviceWithTimeout)
  Await.ready(server)
}

case class Todo(id: Int, title: String, completed: Boolean)