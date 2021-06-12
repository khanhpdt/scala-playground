/*
Use "class" keyword to define a class.

If no modifier is defined, the scope is public.
So, in this case, the class is public.

Fields and methods are collectively called members of a class.

Fields are instance variables, meaning that they belong to only their owing instance.
*/
class ChecksumAccumulator {

  private var sum = 0

  // public field by default
  val sum2 = 0

  /*
  Methods returning Unit are those creating side-effects.
  e.g., here the add method creates a side-effect mutating the
  value of the field sum.
   */
  def add(b: Byte): Unit = sum += b

  /*
  It is recommended to explicitly declare the return type of public methods.

  If a method computes a single result, no curly brace is needed.

  Try to think of a method as an expression that yields one value.
   */
  def checkSum(): Int = ~(sum  & 0xFF)  + 1

  // if there is more logic in the method, curly braces must be used
  private def methodA(a: Int): Int = {
    // a = 10 // ERROR: method parameters are vals, not vars
    val b = 10
    if (a < 0) a else a + b
  }

}

// instantiate an object
val a = new ChecksumAccumulator

/*
Multiline statements.

A line ending is treated as a semicolon unless one of the following
conditions is true:
  1. The line in question ends in a word that would not be legal as the
   end of a statement, such as a period or an infix operator.
  2. The next line begins with a word that cannot start a statement.
  3. The line ends while inside parentheses (...) or brackets [...],
   because these cannot contain multiple statements anyway.
 */
val x = 1 +
        2
val y = (1
  + 2)

/*
Singleton object.

When an object and a class have the same name, the object is called the
class's companion object and the class called the object's companion
class.

It is required that companion object and class must be defined
in the same source file.

Companion object and class can access their private members.

Singleton objects are first-class objects.

Singleton objects are not types, meaning you can't create
instances from them.

Singleton objects are commonly used to implement utilities as
static methods or static field values in Java.

A singleton object is initialized the first time it is accessed.
 */
object ChecksumAccumulator {
  import scala.collection.mutable

  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checkSum()
      cache += (s -> cs)
      cs
    }

}

// to call a method on singleton object
val x2 = ChecksumAccumulator.calculate("test")
