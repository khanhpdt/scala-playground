/**
  * Chapter 2 and 3
  */

import scala.collection.mutable.ListBuffer

/*
Normal classes are defined using the "class" keyword as in Java.
There is a shortcut to define class constructor when defining
the class itself. For example, here the class Person is created
with the constructor including one parameter "name".
By default, the fields in the constructors are public.
 */
class Person(val name: String) {
  def greet(otherPerson: Person): Unit =
    println(name + " greets " + otherPerson.name)

  def greet(p1: Person, p2: Person): Unit = {
    greet(p1)
    greet(p2)
  }
}

/*
Objects can be created using the new operator, as in Java.
 */
val p1 = new Person("Foo")
val p2 = new Person("Bar")
val p3 = new Person("Bar2")

/*
If a method takes only one parameter, it can be called
without using . and parentheses. But this is only possible
if there is a receiver in the statement. e.g., p1 in the
following.
 */
p1 greet p2

/*
If a method takes multiple params, it must be called
using . and parentheses
 */
// p1 greet p2 p3 // compile error
p1.greet(p2, p3) // this is ok

val arr = new Array[String](2)
/*
Array indexing uses parentheses instead of brackets.
*/
arr(0) = "Foo"
arr(1) = "Bar"
for (i <- 0 to 1) {
  println(arr(i))
}

/*
In Scala, if you apply parentheses surrounding values or variables,
what actually happens is that Scala will call a designated method on
on the receiver with the values or variables as arguments.

e.g.,
val x = arr(0) is transformed to val x = arr.apply(0).
arr(0) = 1 is transformed to arr.update(0, 1).
 */

/*
In Scala, everything is objects, even for numbers.
e.g., the literal 1 is an object of type Int, and when we execute
1 + 2, what happens is that we call the method named "+" on the
Int object 1.
 */
val x = 1

/*
Following the functional style of programming, methods should not have
side effect. This usually leads to the requirement that the involved
objects are immutable.
 */

/*
Arrays are mutable, but Lists are immutable.
 */
val arr2 = Array(1,2, 3)
arr2(0) = 10 // OK to mutate the array content
val l1 = List(1, 2, 3)
// l1(0) = 10 // runtime error

/*
List operations:
 */
// concat two lists
val l2 = List(4, 5)
val l3 = l1 ::: l2
println(l3)

// concat element to list
val e1 = -1
// :: pronounces as "cons"
// What actually happens here is that the operator "::" will
// be transformed to the method "::" on the object l3.
// In Scala, if a method name ends with the colon ":", it
// is invoked on the right operand.
val l4 = e1 :: l3
println(l4)

/*
Ways to append to list:
 */
// Use :: as before: always takes constant time

// Use list append method: not recommended b/c it takes time
// linear to list size
val l5 = List(1, 2, 3)
val l6 = l5 :+ 4
println(l6)

// Use operator ":::": not recommended b/c it takes time
// linear to list size

// Use ListBuffer: very efficient and takes constant time
// So if it is not convenient to use :: to append to a list,
// ListBuffer could be used.
val lb = new ListBuffer[Int]
for (i <- 1 to 4) {
  lb += i
}
println(lb.toList)

/*
Tuples:
  - Immutable (like Lists)
  - Contains items of different types (unlike Lists and Arrays)
 */
val tuple1 = (100, "One hundred")
// one-based index
println(tuple1._1)
println(tuple1._2)

/*
Sets:
  - Support both mutable and immutable sets. The default is the immutable one.
 */
var s1 = Set(1, 2, 3)
// extend an immutable set returns a new one. after this statement,
// s1 will point to a new set.
s1 += 4

// to use a mutable set, explicit import or fully qualified name
// must be used
val s2 = scala.collection.mutable.Set(1, 2, 3)
// this is not an assignment, but a call to the method named "+="
// in s2.
s2 += 4

/*
Map:
  - Provide an API to create maps similar to sets. Basically, there is
  a main trait called Map and two subtraits for mutable and
  immutable maps.
 */
var m1 = Map[String, Int](("first", 1), ("second", 2), ("third", 3))
// alternative way to create a map
var m11 = Map[String, Int]("first" -> 1, "second" -> 2, "third" -> 3)
// adding a new pair to map returns a new map
// this -> operator is actually a method named "->" which exists in
// most objects and returns a tuple of two elements
m1 += ("fourth" -> 4) // same as, m1 += (("fourth", 4))
println(m1)

val m2 = scala.collection.mutable.Map[String, Int](
  ("first", 1), ("second", 2), ("third", 3))
m2 += ("fourth" -> 4)
println(m2)

/*
Some guidelines to program in functional style:
  - Try to use val instead of var. If there are many vars, it's a sign
  of imperative style of programming.
  - Try to avoid creating side effects in functions or methods.
  If the return type of a function is Unit, it's a sign that the
  function generates some kind of side effect.
  - Try to use immutable objects.
  - However, bear in mind that the imperative style is not always bad.
  If there are situations where it offers cleaner solution, use it
  instead of blindly trying to use the functional style.
 */



