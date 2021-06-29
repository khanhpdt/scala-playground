## References

- Chapter 11 & 12, Functional programming in Scala

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

## Applicative functors

Applicative functor is an implementation containing two primitive combinators: unit and map2, and satisfies a certain laws.

All monads are applicative functors, but not all applicative functors are monads.

Monads are typically more powerful than applicative functors.

The applicative laws:


## Traversable functors

## TODO

- Re-read Chapter 12.
