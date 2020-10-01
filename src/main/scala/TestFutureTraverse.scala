import scala.collection.generic.CanBuildFrom
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

object TestFutureTraverse extends App {
  implicit val ec = scala.concurrent.ExecutionContext.Implicits.global

  def traverseF[A, B, M[X] <: TraversableOnce[X]](
    in: M[A],
    isParallel: Boolean
  )(fn: A => Future[B])(implicit cbf: CanBuildFrom[M[A], B, M[B]], executor: ExecutionContext): Future[M[B]] = {
    if (isParallel) {
      Future.traverse(in)(fn)
    } else {
      in.foldLeft(Future.successful(cbf(in))) { case (res, item) =>
        for {
          r1 <- res
          r2 <- fn(item).map(Some(_)).recover { case _ => Option.empty[B] } // ignore failure for now
        } yield (
          r2.fold(r1)(r2v => r1 += r2v)
          )
      }.map(_.result())
    }
  }

  traverseF(1 to 20, true) { case i =>
    Future {
      Thread.sleep(Random.nextInt(300))
      if (Set(1, 5, 7).contains(i)) { sys.error("Failed") }
      print(i + " ")
    }
  }

  Thread.sleep(3000)
  println()

  traverseF(1 to 20, false) { case i =>
    Future {
      Thread.sleep(Random.nextInt(300))
      if (Set(1, 5, 7).contains(i)) { sys.error("Failed") }
      print(i + " ")
      i
    }
  }.onSuccess { case is => println(is.mkString(","))}

  Thread.sleep(3000)
}
