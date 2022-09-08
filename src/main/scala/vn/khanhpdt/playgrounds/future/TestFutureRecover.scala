package vn.khanhpdt.playgrounds.future

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object TestFutureRecover extends App {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  val r = Future {
    throw new IllegalStateException("IllegalStateException")
  }.recover {
    case ex: Throwable =>
      println(s"Got exception 1: ${ex.getMessage}")
      throw new IllegalStateException("Rethrow IllegalStateException")
  }

  r.onComplete {
    case Success(_) => println("Should not be successful")
    case Failure(ex) => println(s"Got exception final: ${ex.getMessage}")
  }

}
