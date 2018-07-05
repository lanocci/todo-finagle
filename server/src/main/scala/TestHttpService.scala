package server

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}

import scala.language.postfixOps

object TestHttpServer extends App {
  val service = new Service[http.Request, http.Response] {
    def apply(req:http.Request): Future[http.Response] = {
      Future.value {
        val response = http.Response(req.version, http.Status.Ok)
        response.setContentString("OK")
        response
      }
    }
  }
  val server = Http.serve(":8080", service)
  Await.ready(server)
}

