// placeholder _
// intuitively, placeholder is where the parameter is filled in.
// the replacement will be done only once though. so if there are multiple placeholders,
// each placeholder will be replaced by one parameter once in the function body.
List(1, 2, 3).filter(_ > 1) // 2, 3

// multiple placeholders means multiple parameters
val f = (_: Int) + (_: Int) // (_ : Int) allows to explicitly specify the type of the param
f(1, 2) // 3

// placeholder can also be used to create partially applied functions. e.g., f1, f2 below
def sum(x: Int, y: Int, z: Int) = x + y + z

val f1 = sum _ // _ replaces the whole parameter list
f1(1, 2, 3) // 6

val f2 = sum(1, _, 3) // _ replaces the second param
f2(2) // 6

// Tail-call optimization is limited to situations where a method or
// nested function calls itself directly as its last operation (i.e., tail-recursive),
// without going through a function value or some other intermediary.

// not tail-recursive b/c the last operation is the plus operation
def boom(x: Int): Int =
  if (x == 0) throw new Exception("boom!")
  else boom(x - 1) + 1

// tail-call optimization not applicable: last call is to another function
def isEven(x: Int): Boolean =
  if (x == 0) true else isOdd(x - 1)
def isOdd(x: Int): Boolean =
  if (x == 0) false else isEven(x - 1)

// tail-call optimization not applicable
val funValue = nestedFun _
def nestedFun(x: Int) : Unit = {
  if (x != 0) { println(x); funValue(x - 1) }
}
