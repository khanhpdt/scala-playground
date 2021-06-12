package vn.khanhpdt.playgrounds.future

import scala.collection.BuildFrom
import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds
import scala.util.{Failure, Random, Success}

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

  println()
  println("Note that items 1,5,7 are not printed out because in our test we throw error when processing them.")
  println()

  println("Testing Future.traverse. The items are processed in parallel.")
  traverseF((1 to 20).toList, isParallel = true) { i =>
    Future {
      Thread.sleep(Random.nextInt(300))
      if (Set(1, 5, 7).contains(i)) {
        sys.error(s"Failed: $i")
      }
      print(i + " ")
    }
  }.onComplete {
    case Success(is) =>
      println()
      println(s"The output of traverse: ${is.mkString(",")}")
    case Failure(ex) =>
      println()
      println(s"The resulting future is failed with error: ${ex.getMessage}")
  }

  // wait for the future to finish
  Thread.sleep(3000)

  println()
  println("Only one error logged, which means Future.traverse will keep only the first error and skip the other errors.")
  println("This is probably because Future.traverse uses Future.zipWith to combine the futures.")
  println("Although error might happen for some item in the middle of processing, Future.traverse still processes the other items.")

  println("\n")

  println("Testing sequential traverse. The items are processed sequentially.")

  print("Items are processed in order: ")
  traverseF((1 to 20).toList, isParallel = false) { i =>
    Future {
      Thread.sleep(Random.nextInt(300))
      if (Set(1, 5, 7).contains(i)) {
        sys.error(s"Failed: $i")
      }
      print(i + " ")
      i
    }
  }.onComplete {
    case Success(is) =>
      println()
      println(s"The output of traverse: ${is.mkString(",")}")
    case Failure(ex) =>
      println()
      println(s"The resulting future is failed with error: ${ex.getMessage}")
  }

  // wait for the future to finish
  Thread.sleep(5000)

}
