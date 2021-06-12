package vn.khanhpdt.playgrounds.monoids

import Monoid.foldMapV

sealed trait WC
case class Stub(chars: String) extends WC
case class Part(lStub: String, words: Int, rStub: String) extends WC

object WC {

  private def countSingleWord(s1: String) = if (s1.isEmpty) 0 else 1

  val wcMonoid: Monoid[WC] = new Monoid[WC] {
    // assumption: x stands right before y
    override def op(x: WC, y: WC): WC = (x, y) match {
      case (Stub(x1), Stub(x2)) => Stub(x1 + x2)
      case (Stub(x1), Part(l, w, r)) => Part(x1 + l, w, r)
      case (Part(l, w, r), Stub(y1)) => Part(l, w, r + y1)
      case (Part(l1, w1, r1), Part(l2, w2, r2)) => Part(l1, w1 + countSingleWord(r1 + l2) + w2, r2)
    }
    override val unit: WC = Stub("")
  }

  def count(s: String): Int = {
    def charToWC(c: Char) = if (c.isWhitespace) Part("", 0, "") else Stub(c.toString)
    foldMapV(s.toIndexedSeq, wcMonoid)(charToWC) match {
      case Stub(s1) => countSingleWord(s1)
      case Part(l, wc, r) => countSingleWord(l) + wc + countSingleWord(r)
    }
  }

}
