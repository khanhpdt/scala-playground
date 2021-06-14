# Terminology

_Local functions_ are those defined inside another function.

_Function literal_, e.g.,
```scala
(x, y) => x + y

x => {
  // do something
}
```

_Function value_ is an object representing a function literal at runtime. 
e.g., when you pass a function literal to a method, what gets passed at run time is the function value, which is an object.

Because function value is an object, function is a first-class citizen in Scala. e.g., you can use it as the return result, or you can pass it as arguments.
