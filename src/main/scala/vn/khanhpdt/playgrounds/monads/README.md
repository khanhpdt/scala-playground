## References

- Chapter 11, Functional programming in Scala

## Monad

> A monad is an implementation of one of the minimal sets of monadic combinators, satisfying the laws of associativity and identity. 

Possible sets of combinators:
  - unit and flatMap
  - unit and compose
  - unit, map, and join

Monad laws:
  - Associative law:
  ```scala
  x.flatMap(f).flatMap(g) == x.flatMap(a => f(a).flatMap(g));
  // or with Monad.compose
  compose(compose(f, g), h) == compose(f, compose(g, h))
  ```
  - Identity law:
  ```scala
  compose(f, unit) == f
  compose(unit, f) == f
  ```
  
```scala
def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = a => flatMap(f(a))(g)
```

One typical and very common usage of vn.khanhpdt.playgrounds.monads in Scala is in for comprehension.

## Terms

- Type lambda
