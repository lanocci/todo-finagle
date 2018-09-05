package todofinagle.todobackend

import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.util.{Duration, Future, Timer}

class TimeoutFilter[Req, Rep](timeout: Duration, timer: Timer) extends SimpleFilter[Req, Rep] {
  def apply (request: Req, service: Service[Req, Rep]): Future[Rep] = {
    val res = service(request)
    res.within(timer, timeout)
  }
}
