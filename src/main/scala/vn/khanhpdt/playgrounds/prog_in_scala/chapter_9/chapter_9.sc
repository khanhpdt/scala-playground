// Currying is the technique of converting a function that takes multiple arguments
// into a sequence of functions that each takes a single argument.
// (From https://en.wikipedia.org/wiki/Currying)

// From the book, currying is a way to write functions with multiple parameter lists. For instance
// def f(x: Int)(y: Int) is a curried function with two parameter lists.
// This is because, when executing a function with multiple parameter lists,
// there are multiple steps, each of which involves a function with one parameter.
// e.g., applying f(X)(Y) involves the following steps:
//  - Create a function (x) => (y) => f(x)(y)
//  - Apply X to that function: (y) => f(X)(y)
//  - Apply Y to that function: f(X)(Y)

def f(x: Int)(y: Int) = x + y

// Use placeholder syntax to create a partially applied function from a curried function
def f2 = f(1) _
f2(2) // 3


// x is called by-name parameter
def f3(x: => String): Unit = {
  println(x)
}

// By-name parameters are lazy, i.e., only evaluated when used.
// At runtime, what gets passed to, for example, the function f3 is
// a function value whose apply method returns the value of the argument.

// By-name parameters also help to write a nicer function call
// when the param is a no-parameter function. e.g.,
// not using by-name parameter
def f4(f5: () => String) {
  println(f5())
}
f4(() => "abc")
// or
f4 { () =>
  "abc"
}
// using by-name parameter
def f5(x: => String): Unit = {
  println(x)
}
f5 {
  // whatever code to return a string
  "hi"
}
