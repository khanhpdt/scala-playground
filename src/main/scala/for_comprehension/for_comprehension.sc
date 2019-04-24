import scala.util.Try

val t = Try(1)
val list = List(1, 2, 3)

// for comprehension is actually composed from higher order functions, e.g., map, flatMap
for {
  t1 <- t
  i <- Try(list) // must wrap list by Try() here b/c otherwise you get compile error. See the desugar for-comprehension for why.
} yield i
// desugar for comprehension
t.flatMap(t1 =>
  Try(list).map(i => i)
)

for {
  i <- list
  t1 <- List("a", "b")
} yield i
// desugar
list.flatMap(i =>
  List("a", "b").map(t1 => i)
)

// note that this will return a list of (1, 2, 3).
// See the desugar version for why.
for {
  i <- list
  t1 <- Set("a", "b")
} yield i
