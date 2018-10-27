package prog_in_scala.chapter_4

class ACompanionClass {
  def method2(): Unit = {
    import ACompanionClass._
    // class can access private members of its companion object
    privateMethodInCompanionObject()
    println("Method2 in companion class")
  }

  private def privateMethodInCompanionClass(): Unit = println("Method3 in companion class")
}

object ACompanionClass {
  private def privateMethodInCompanionObject(): Unit = println("Method in companion object")

  def method4(o: ACompanionClass): Unit = {
    // singleton object can access private members of its companion class
    o.privateMethodInCompanionClass()
    println("Method4 in companion object")
  }
}
