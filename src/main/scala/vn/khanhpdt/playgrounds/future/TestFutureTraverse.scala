package vn.khanhpdt.playgrounds.future

import scala.collection.BuildFrom
import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds
import scala.util.{Random, Success}

object TestFutureTraverse extends App {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  def traverseF[A, B, M[X] <: IterableOnce[X]](
    in: M[A],
    isParallel: Boolean
  )(fn: A => Future[B])(implicit cbf: BuildFrom[M[A], B, M[B]]): Future[M[B]] = {
    if (isParallel) {
      Future.traverse(in)(fn)
    } else {
      in.iterator.foldLeft(Future.successful(cbf(in))) { case (res, item) =>
        for {
          r1 <- res
          r2 <- fn(item).map(Some(_)).recover { case _ => Option.empty[B] }
        } yield r2.fold(r1)(r2v => r1 += r2v)
      }.map(_.result())
    }
  }

  traverseF((1 to 20).toList, isParallel = true) { i =>
    Future {
      Thread.sleep(Random.nextInt(300))
      if (Set(1, 5, 7).contains(i)) {
        sys.error("Failed")
      }
      print(i + " ")
    }
  }

  Thread.sleep(3000)
  println()

  traverseF((1 to 20).toList, isParallel = false) { i =>
    Future {
      Thread.sleep(Random.nextInt(300))
      if (Set(1, 5, 7).contains(i)) {
        sys.error("Failed")
      }
      print(i + " ")
      i
    }
  }.onComplete {
    case Success(is) => println(is.mkString(","))
    case _ =>
  }

  Thread.sleep(3000)
}
