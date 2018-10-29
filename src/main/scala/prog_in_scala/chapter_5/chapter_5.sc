// integer literals can be in two forms
val i1 = 10 // decimal
val i2 = 0xFF // hexadecimal

// explicitly declare Long values
val l1 = 10L
val l2 = 0xFFL

// float and double
val f = 1.2F
val d = 1.2 // double is the default of floating-point numbers
val d2 = 1e2
val d3 = 1E2

// character
val c1 = 'A'
val c2 = '\u0041' // use unicode code point

// string
val s1 = "Hello"
// raw string
val s2 = """Hello "World""""
val s3 =
  """
    |Hello
    |"World"
  """.stripMargin
val s4 =
  """
     Hello
     "World"
  """
println("with stripMargin: " + s3)
println("without stripMargin: " + s4)

/*
Symbol literal.

Symbols provide a simple way to get unique objects
for equal strings.

Symbols are interned as Strings in Java.
i.e., literals with the same name reference
to the same Symbol object.
 */
val sl1 = 'hello
val sl2 = 'hello
println(sl1 == sl2)

// what we can do with Symbols is to get their names
sl1.name

