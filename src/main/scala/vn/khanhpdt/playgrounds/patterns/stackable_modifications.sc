// One of the main usages of traits is to make stackable modifications.
// e.g.,

abstract class IntQueue {
  def get(): Int

  def put(x: Int): Unit
}

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  val buf = new ArrayBuffer[Int]

  def get() = buf.remove(0)

  def put(x: Int): Unit = {
    buf += x
  }
}

// These traits are stackable modifications, because they modify the operations
// in their implementations and they can be stacked on each other to provide a
// combinations of their operations.
trait Doubling extends IntQueue {
  // "abstract override" also enforces that the implementation of this trait must have a concrete
  // definition of this method.
  // e.g., the Impl1, Impl2, and Impl3 below all have the concrete "put" method (inherited from BasicIntQueue).
  abstract override def put(x: Int): Unit = super.put(2 * x)
}
trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x + 1)
}
trait Filtering extends IntQueue {
  abstract override def put(x: Int): Unit = if (x >= 0) super.put(x)
}

val items = List(-1, 0, 1, 2)
// an implementation with 1 modification

class Impl1 extends BasicIntQueue with Doubling

val impl1 = new Impl1
items.foreach(impl1.put)
items.foreach(_ => print(s"${impl1.get()} ")) // -2, 0, 2, 4
println()

// an implementation with 2 modifications
class Impl2 extends BasicIntQueue with Doubling with Incrementing

val impl2 = new Impl2
items.foreach(impl2.put)
items.foreach(_ => print(s"${impl2.get()} ")) // 0, 2, 4, 6
println()

// an implementation with 3 modifications
class Impl3 extends BasicIntQueue with Doubling with Incrementing with Filtering

val impl3 = new Impl3
items.foreach(impl3.put)
items.foreach { _ =>
  if (impl3.buf.nonEmpty) {
    print(s"${impl3.get()} ")
  }
} // 2, 4, 6
println()

// Note: The order of mixins is significant.[2] The precise rules are given in the following section, but,
// roughly speaking, traits further to the right take effect first. When you call a method on a class
// with mixins, the method in the trait furthest to the right is called first. If that method calls super,
// it invokes the method in the next trait to its left, and so on.

