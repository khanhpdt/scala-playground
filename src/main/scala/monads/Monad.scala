package monads

import functors.Functor

trait Monad[F[_]] extends Functor[F] {

  def unit[A](a: => A): F[A]

  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  def map[A, B](fa: F[A])(f: A => B): F[B] =
    flatMap(fa)(a => unit(f(a)))

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] =
    flatMap(fa)(a => map(fb)(b => f(a, b)))

  def sequence[A](lma: List[F[A]]): F[List[A]] =
    lma.foldRight(unit(List.empty[A]))((fa, mlb) => map2(fa, mlb)(_ :: _))

  def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] =
    la.foldRight(unit(List.empty[B]))((a, mlb) => map2(f(a), mlb)(_ :: _))

  def replicateM[A](n: Int, ma: F[A]): F[List[A]] =
    sequence(List.fill(n)(ma))

  def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = a => flatMap(f(a))(g)

  def join[A](mma: F[F[A]]): F[A] = flatMap(mma)(ma => ma)

}

object Monad {

  val optMonad = new Monad[Option] {
    override def unit[A](a: => A): Option[A] = Some(a)

    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa flatMap f
  }

  val listMonad = new Monad[List] {
    override def unit[A](a: => A): List[A] = Nil

    override def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = fa flatMap f
  }

}