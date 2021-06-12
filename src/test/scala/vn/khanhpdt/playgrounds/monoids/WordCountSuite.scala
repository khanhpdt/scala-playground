package vn.khanhpdt.playgrounds.monoids

import org.scalatest.{FunSuite, Matchers}
import WC._

class WordCountSuite extends FunSuite with Matchers {

  test("Count \"\"") {
    count("") shouldBe 0
  }

  test("Count \"    \"") {
    count("    ") shouldBe 0
  }

  test("Count \"lorem ipsum dolor sit amet,\"") {
    count("lorem ipsum dolor sit amet,") shouldBe 5
  }

}
