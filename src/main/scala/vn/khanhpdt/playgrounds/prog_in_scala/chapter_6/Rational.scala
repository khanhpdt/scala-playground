package vn.khanhpdt.playgrounds.prog_in_scala.chapter_6

// Without using "val" or "var", the parameters are not fields but only parameters of the primary constructor.
// However, these parameters can still be accessed inside the class body.
class Rational(n: Int, d: Int) {

  // The Scala compiler will compile any code you place in the class body, which isn't part of a field
  // or a method definition, into the primary constructor.
  require(d != 0)

  override def toString = s"$numer/$denom"

  /*
   * The Scala compiler will place the code for the initializers of Rational's three fields into the primary
   * constructor in the order in which they appear in the source code.
   */
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  /*
   Auxiliary constructors:
   - Every auxiliary constructor must invoke another constructor of the same class as its first action.
   - In other words, the first statement in every auxiliary constructor in every Scala class will have
   the form "this(...)". The invoked constructor is either the primary constructor (as in the Rational example),
   or another auxiliary constructor that comes textually before the calling constructor.
   */
  def this(n: Int) = this(n, 1)

  def add(that: Rational): Rational = {
    new Rational(n * that.denom + that.numer * d, d * that.denom)
  }

  @scala.annotation.tailrec
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

}

object Rational {

  val x = new Rational(1, 0)

}