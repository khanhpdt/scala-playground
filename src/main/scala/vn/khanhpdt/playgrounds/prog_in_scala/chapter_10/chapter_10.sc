// Parameterless vs. Empty-paren method
// Parameterless should be used when the method does not change any state,
// or more generally does not cause side effect. Otherwise, use empty-paren method.

// parameterless method
def m1 = "abc"
// empty-paren method
def m2() = "abc"

// Parameterless method supports the uniform access principle, which says that
// client code should not be affected by a decision to implement an attribute
// as a field or method.
//
// e.g., a parameterless method can be overrided as a val.
class P {
  def m = "m"
}
class S extends P {
  override val m: String = "m2"
}

// Generally, Scala has just two namespaces for definitions in place of Java's four. Java's four
// namespaces are fields, methods, types, and packages. By contrast, Scala's two namespaces are:
//  - values (fields, methods, packages, and singleton objects)
//  - types (class and trait names)
// The reason Scala places fields and methods into the same namespace is precisely so you can
// override a parameterless method with a val, something you can't do with Java.
// e.g.,
class WontCompile {
//  private var f = 0 // Won't compile, because a field
//  def f = 1         // and method have the same name, which is fine in Java
}

