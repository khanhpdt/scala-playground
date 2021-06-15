// "private[X] def z" means z is visible up to X.
// "protected[X] def z" means z is visible in subclasses (protected)
// and also visible up to X (private[X]).
//
// These two private[X] and protected[X] are called qualified modifiers
// and X is called the qualifier.
// X can be some enclosing package, class or singleton object.

// Special qualified modifier: private[this], which means private to the
// current instance.
// e.g.,
class C1 {
  private[this] val a = "a"
  def method(c2: C1): Unit = {
    // c2.a // compile error
  }
}
// compared to normal private
class C2 {
  private val a = "a"
  def method(c2: C2): Unit = {
    println(c2.a) // OK
  }
}


// Package objects are frequently used to hold package-wide type aliases and
// implicit conversions.