package monoids

import monoids.Monoid._
import org.scalatest.{FunSuite, Matchers}

class MonoidSuite extends FunSuite with Matchers {

  test("mapMergeMonoid") {
    M.op(
      Map("o1" -> Map("i1" -> 1, "i2" -> 2)),
      Map("o1" -> Map("i2" -> 3))
    ) shouldBe Map("o1" -> Map("i1" -> 1, "i2" -> 5))
  }

  test("bag") {
    bag(Vector("a", "rose", "is", "a", "rose")) shouldBe Map("a" -> 2, "rose" -> 2, "is" -> 1)
  }

}
