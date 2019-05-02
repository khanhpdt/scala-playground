package monoids

// A Monoid is basically a type, together with an operation and three laws.
trait Monoid[A] {
  // This op must satisfy two laws:
  // - Closure: produces the output of the same type as the inputs
  // - Associativity: i.e., op(op(x1, x2), x3) == op(x1, op(x2, x3))
  def op(x: A, y: A): A

  // Identity law: op(x1, unit) == x1, and op(unit, x1) == x1
  val unit: A
}

object Monoid {

  val intAddition = new Monoid[Int] {
    override def op(x: Int, y: Int) = x + y

    override val unit = 0
  }

  val intMultiplication = new Monoid[Int] {
    override def op(x: Int, y: Int) = x * y

    override val unit = 1
  }

  val booleanOr = new Monoid[Boolean] {
    override def op(x: Boolean, y: Boolean) = x || y

    override val unit = false
  }

  val booleanAnd = new Monoid[Boolean] {
    override def op(x: Boolean, y: Boolean) = x && y

    override val unit = true
  }

  def optionMonoid[A] = new Monoid[Option[A]] {
    override def op(x: Option[A], y: Option[A]) = x orElse y

    override val unit = None
  }

  // Endofunctions are those with input and output of the same type
  def endoMonoid[A] = new Monoid[A => A] {
    override def op(x: A => A, y: A => A) = x compose y

    override val unit = x => x
  }

  def dual[A](m: Monoid[A]) = new Monoid[A] {
    override def op(x: A, y: A): A = m.op(y, x)

    override val unit: A = m.unit
  }

  def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = {
    as.foldLeft(m.unit)((r, a) => m.op(r, f(a)))
  }

  val stringConcatenation = new Monoid[String] {
    override def op(x: String, y: String) = x + y

    override val unit = ""
  }

  def foldMapV[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B = {
    if (v.isEmpty) m.unit
    else if (v.length == 1) f(v(0))
    else {
      val (l, r) = v.splitAt(v.length / 2)
      m.op(foldMapV(l, m)(f), foldMapV(r, m)(f))
    }
  }

}