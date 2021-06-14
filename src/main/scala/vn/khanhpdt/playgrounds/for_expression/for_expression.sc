import scala.util.Try

val t = Try(1)
val list = List(1, 2, 3)

// for expression is actually composed from higher order functions, e.g., map, flatMap
for {
  t1 <- t
  i <- Try(list) // must wrap list by Try() here b/c otherwise you get compile error. See the desugar for-expression for why.
} yield i
// desugar for expression
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

/*
for expression is an expression because it can return value
 */
val l1 = List(1, 2, 3)
// note that, the for expression here returns unit value
for (i <- l1) {
  //i += 1 // error b/c i is a val
  println(i)
}

// filtering
for (i <- 1 to 10 if i % 2 == 0)
  println(i)

// multiple filtering criteria
for (
  i <- 1 to 10
  if i % 2 == 0
  if i % 3 == 0
) println(i)

// nested loop: when there are multiple generators
for (
  i <- 1 to 2;
  j <- 11 to 12
) println(s"i = $i, j = $j")

// other form of nested loop: use curly brace -> no semicolon needed to separate the generators (i.e., the <- clauses)
for {
  i <- 1 to 2
  j <- 11 to 12
} println(s"i = $i, j = $j")

// mid-stream variable binding
for {
  i <- 1 to 2
  k = i + 2 // bind variable k mid-stream
  j <- 11 to 12
} println(s"i=$i, j=$j, k=$k")

// produce a new collection: use yield
val l3 = for {
  i <- List(1, 2, 3, 4, 5, 6)
  if i % 2 == 0
} yield i.toString

// l3 will have the same type as the first generator, e.g., if the right-hand side of <- is a list, then it will be a list.
l3.foreach(println)
