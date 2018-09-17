package todofinagle.todobackend

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Duration, Future, JavaTimer}

class TodoService() extends Service[http.Request, http.Response] {
  val get = new GetService()
  val post = new PostService()
  override def apply(request: http.Request): Future[http.Response] = request.method match {
    case http.Method.Get => get(request)
    case http.Method.Post => post(request)
  }
}