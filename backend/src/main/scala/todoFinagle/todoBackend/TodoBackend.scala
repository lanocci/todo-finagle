package todoFinagle.todoBackend

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Duration, Future, JavaTimer}

object TodoBackend extends App {
  val service = new Service[http.Request, http.Response] {
    def apply(req:http.Request): Future[http.Response] = {
      Future.value {
        val response = http.Response(req.version, http.Status.Ok)
        response.setContentString("OK")
        response
      }
    }
  }
  val timeoutFilter = new TimeoutFilter[http.Request, http.Response](Duration.fromNanoseconds(1), new JavaTimer(false))
  val serviceWithTimeout = timeoutFilter.andThen(service)
  val server = Http.serve(":8080", serviceWithTimeout)
  Await.ready(server)
}
