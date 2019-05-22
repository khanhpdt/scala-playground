## References

- Chapter 11, Functional programming in Scala

## Functor

```scala
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
```
We say that a type constructor like List (or Option , or F ) is a functor, and the Functor[F] instance constitutes proof that F is in fact a functor.

Informally, functor is a type constructor whose instances can be mapped over.

Functor law:
  - `map(x)(a => a) == x`:
    - map(x) preserves the structure of x, i.e., only the elements of the structure are modified by map; the shape or structure itself is left intact.

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

One typical and very common usage of monads is in for comprehension.

## Terms

- Type lambda
