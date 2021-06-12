## References

- Chapter 10, Functional programming in Scala
- https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/vn.khanhpdt.playgrounds.monoids/Monoid.scala
- https://kubuszok.com/compiled/kinds-of-types-in-scala/

## Monoid

A Monoid is basically a type, together with an operation and three laws.
```scala
trait Monoid[A] {
  // This op must satisfy two laws:
  // - Closure: produces the output of the same type as the inputs
  // - Associativity: i.e., op(op(x1, x2), x3) == op(x1, op(x2, x3))
  def op(x: A, y: A): A

  // Identity law: op(x1, unit) == x1, and op(unit, x1) == x1
  val unit: A
}
```

## Terms

- A monoid homomorphism `f` between vn.khanhpdt.playgrounds.monoids M and N obeys the following general law for all values x and y: `M.op(f(x), f(y)) == f(N.op(x, y))`
  - e.g., `"foo".length + "bar".length == ("foo" + "bar").length`, where `f = length`, `M = intAddition`, `N = stringConcatenation`

- A monoid isomorphism between M and N has two homomorphisms `f` and `g`, where both `f andThen g` and `g andThen f` are an identity function.
  - For example, the String and List[Char] vn.khanhpdt.playgrounds.monoids with concatenation are isomorphic.
  - The two Boolean vn.khanhpdt.playgrounds.monoids (false, ||) and (true, &&) are also isomorphic, via the ! (negation) function.

- Type constructor

- Higher-kinded types
