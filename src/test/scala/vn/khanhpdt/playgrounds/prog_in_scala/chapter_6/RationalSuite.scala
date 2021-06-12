package vn.khanhpdt.playgrounds.prog_in_scala.chapter_6

import org.scalatest.{FunSuite, Matchers}

class RationalSuite extends FunSuite with Matchers {

  test("require non-zero denominator") {
    a[IllegalArgumentException] shouldBe thrownBy {
      new Rational(1, 0)
    }
  }

  test("add") {
    val res = new Rational(1, 3).add(new Rational(2, 5))
    res.numer shouldBe 11
    res.denom shouldBe 15
  }

}
