// The method called by super, e.g., super.methodA(), is resolved following the
// linearization rule. (see 12.6 for more details on linearization)
// When you instantiate a class with new, Scala takes the class, and all of its
// inherited classes and traits, and puts them in a single, linear order.
// Then, whenever you call super inside one of those classes, the invoked
// method is the next one up the chain. If all of the methods but the last call super,
// the net result is stackable behavior.
trait T2 {
  // here super refers to AnyRef
  def method(): Unit = println(super.toString)
}
class C1 {
  override def toString: String = "C1"
}
class C2 extends C1 with T2 {
}
// but if we mixin T2 to C2, at runtime, super of C2 will refer to C1 b/c of
// the linearization
new C2().method() // C1

// One of the main usages of traits is to make thick interface out of thin
// interfaces by mixing thin interfaces to some common interface. That common
// interface becomes the thick interface.
//
// Thin versus rich interfaces represents a commonly faced trade-off in
// object-oriented design. The trade-off is between the implementers and
// the clients of an interface. A rich interface has many methods, which
// make it convenient for the caller. Clients can pick a method that exactly
// matches the functionality they need. A thin interface, on the other hand,
// has fewer methods, and thus is easier on the implementers.
// Clients calling into a thin interface, however, have to write more code.
// Given the smaller selection of methods to call, they may have to choose
// a less than perfect match for their needs and write extra code to use it.


