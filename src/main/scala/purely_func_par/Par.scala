package purely_func_par

import java.util.concurrent.{ExecutorService, Future, TimeUnit}

import purely_func_par.Par.{Par, fork, map2, unit}

object Par {

  type Par[A] = ExecutorService => Future[A]

  private case class UnitFuture[A](get: A) extends Future[A] {
    override def cancel(mayInterruptIfRunning: Boolean): Boolean = false

    override def isCancelled: Boolean = false

    override def isDone: Boolean = true

    override def get(timeout: Long, unit: TimeUnit): A = get
  }

  def unit[A](a: A): Par[A] = _ => UnitFuture(a)

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def map2[A, B, C](p1: Par[A], p2: Par[B])(f: (A, B) => C): Par[C] = es => {
    val a1 = p1(es)
    val a2 = p2(es)
    UnitFuture(f(a1.get, a2.get))
  }

  def map[A, B](p: Par[A])(f: A => B): Par[B] = map2(p, unit(()))((a, _) => f(a))

  // separation of concerns b/w who control which should be scheduled for parallelism
  // and who control when and how to execute the parallel computations

  // fork marks a computation for concurrent evaluation
  def fork[A](p: => Par[A]): Par[A] = es => es.submit(() => p(es).get)

  // run extracts a value from a Par by actually performing the computation
  def run[A](es: ExecutorService)(p: Par[A]): Future[A] = p(es)

  def asyncF[A, B](f: A => B): A => Par[B] = a => lazyUnit(f(a))

  def sequenceRight[A](ps: List[Par[A]]): Par[List[A]] = ps.foldRight(unit(List.empty[A]))((a, b) => map2(a, b)(_ :: _))

  def sequenceBalanced[A](ps: IndexedSeq[Par[A]]): Par[IndexedSeq[A]] = fork {
    if (ps.isEmpty) unit(Vector())
    else if (ps.length == 1) map(ps.head)(Vector(_))
    else {
      val (l, r) = ps.splitAt(ps.length / 2)
      map2(sequenceBalanced(l), sequenceBalanced(r))(_ ++ _)
    }
  }

  def sequence[A](ps: List[Par[A]]): Par[List[A]] = map(sequenceBalanced(ps.toIndexedSeq))(_.toList)

  def parMap[A, B](as: List[A])(f: A => B): Par[List[B]] = fork {
    val fbs = as.map(asyncF(f))
    sequence(fbs)
  }

  def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] = fork {
    val fbs = as.map(asyncF(a => if (f(a)) List(a) else List()))
    map(sequence(fbs))(_.flatten)
  }

}

object Examples {

  def sum(ints: IndexedSeq[Int]): Par[Int] = {
    if (ints.length <= 1) unit(ints.headOption getOrElse 0)
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      map2(fork(sum(l)), fork(sum(r)))(_ + _)
    }
  }

}
