// "if" is an expression, and thus it returns value
val x = 10
val s =
  if (x > 9) "greater than 9"
  else "less than or equal 9"

// while
var y = 3
while (y < 10) {
  println(y)
  y += 1
}

// do-while
var y2 = 3
do {
  println(y2)
  y2 += 1
} while (y2 < 10)

// () is a instance of Unit, and is called unit value
()

// method can return unit value
def greet(): Unit = { println("hello") }
//assert(() == greet()) // IDE warning

// reassignment returns unit value. this is different from Java which returns the assigned value
var y3 = 2
val z: Unit = y3 += 2
//assert(z == ()) // IDE warning

/*
It is recommended to avoid using loops (i.e., while and do-while) because these are not pure functional.
Just as it is recommended to use vals instead of vars.
 */

/*
Try-catch-finally
 */
def tryCatch(x: Int): String = {
  try {
    if (x == 0)
      throw new IllegalArgumentException
    if (x == 1)
      throw new IllegalStateException
    "OK"
  } catch {
    case _: IllegalArgumentException =>
      println("Caught IllegalArgumentException!")
      "IllegalArgumentException caught"
    case _: IllegalStateException =>
      println("Caught IllegalStateException!")
      "IllegalStateException caught"
  } finally {
    println("finally")
    // without using "return", the value of the try-catch expression is determined by the returned value in the try or catch block
    if (x == 0) "Hey 0"
    // using "return", the value of the try-catch expression is determined by the returned value here
    else return "Finally returns"
  }
}
tryCatch(0)
tryCatch(1)
tryCatch(2)

/*
It is recommended not to return value in the finally block, b/c this it is confusing.
The finally block should only be used to handle side effects, e.g., closing a file.
 */

