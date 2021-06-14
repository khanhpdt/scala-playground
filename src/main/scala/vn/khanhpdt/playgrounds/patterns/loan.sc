// The loan pattern.

import java.io.{File, PrintWriter}

// There is an abstraction (e.g., a function) that creates a resource and then loans it to the client code.
// e.g.
def withPrintWriter(file: File, op: PrintWriter => Unit): Unit = {
  // the resource
  val writer = new PrintWriter(file)
  try {
    // loan the resource to the client code (i.e., the op function)
    op(writer)
  } finally {
    // close the resource
    writer.close()
  }
}

// Benefit:
// - The main benefit is that the resource is managed by the abstraction and the client does not need to concern about that.
// e.g., the writer above is created and then closed by the abstraction.
