## References

- Chapter 10, Functional programming in Scala
- https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/monoids/Monoid.scala
- https://kubuszok.com/compiled/kinds-of-types-in-scala/

## Terms

- A monoid homomorphism `f` between monoids M and N obeys the following general law for all values x and y: `M.op(f(x), f(y)) == f(N.op(x, y))`
    - e.g., `"foo".length + "bar".length == ("foo" + "bar").length`, where `f = length`, `M = intAddition`, `N = stringConcatenation`

- A monoid isomorphism between M and N has two homomorphisms `f` and `g`, where both `f andThen g` and `g andThen f` are an identity function.
    - For example, the String and List[Char] monoids with concatenation are isomorphic. 
    - The two Boolean monoids (false, ||) and (true, &&) are also isomorphic, via the ! (negation) function.

- Type constructor

- Higher-kinded types
