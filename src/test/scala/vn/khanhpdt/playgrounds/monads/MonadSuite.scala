package vn.khanhpdt.playgrounds.monads

import vn.khanhpdt.playgrounds.monads.Monad._
import org.scalatest.{FunSuite, Matchers}

class MonadSuite extends FunSuite with Matchers {

  test("sequence") {
    optMonad.sequence(List(Some(1), Some(2), Some(3))) shouldBe Some(List(1, 2, 3))
  }

  test("sequence 2") {
    optMonad.sequence(List(Some(1), None, Some(3))) shouldBe None
  }

  test("traverse") {
    optMonad.traverse(List(1, 2, 3))(a => Some(a.toString)) shouldBe Some(List("1", "2", "3"))
  }

}
