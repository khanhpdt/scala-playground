val x = new String("a")
val y = new String("a")

// == is a method in Any which is the superclass
// of everything (both AnyVal and AnyRef and their subclasses).
// == simply calls the equals method
x == y // true

// on the other hand, eq is a method in AnyRef and is used for
// reference equality,
// i.e., two things are equals if they refer to the same thing
x eq y // false

// Nothing is the subtype of every types.
// It does not have any instance, so it seems that
// the only use of this type is to be used in
// some definition (e.g., the return type of some method)

// Value class: A value class must have exactly one parameter
// and it must have nothing inside it except defs.
// Furthermore, no other class can extend a value class,
// and a value class cannot redefine equals or hashCode.
//
// To define a value class, make it subclass of AnyVal.
class Dollars(val amount: Int) extends AnyVal {
  override def toString = "$" + amount
}
// Benefit of value class: An instance of a value class
// will usually compile to Java bytecode that does not
// use the wrapper class.
// e.g.,
// d is of type Dollars in Scala source code, but the
// compiled Java bytecode will use type Int directly.
val d = new Dollars(100)
