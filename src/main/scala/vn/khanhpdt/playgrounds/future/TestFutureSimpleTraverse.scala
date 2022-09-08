package vn.khanhpdt.playgrounds.future

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object TestFutureSimpleTraverse extends App {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  println("Simple traverse")
  val items = List(1, 2, 3)
  val res = Future.traverse(items) { i =>
    Future {
      if (i == 2)
        sys.error(s"Item [$i] error")
      i
    }
  }
  res.onComplete {
    case Success(_) => println("Should not be successful")
    case Failure(ex) => println(s"Got exception: ${ex.getMessage}")
  }

  println("Nested traverse")
  val items2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val res2 = items2.grouped(2).foldLeft(Future.successful(List.empty[Int])) { case (res, is) =>
    for {
      r <- res
      r2 <- Future.traverse(is) { i =>
        Future {
          if (i == 5)
            sys.error(s"Item [$i] error")
          println(s"Processed $i")
          i
        }
      }
    } yield r ++ r2
  }
  res2.onComplete {
    case Success(_) => println("Should not be successful")
    case Failure(ex) => println(s"Got exception: ${ex.getMessage}")
  }

}
