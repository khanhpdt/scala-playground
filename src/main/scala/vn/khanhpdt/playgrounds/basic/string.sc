import java.nio.charset.Charset

val s = "Hello world".stripMargin
s.length
s.getBytes.length
s.getBytes(Charset.forName("UTF8")).length
s.getBytes(Charset.forName("UTF16")).length
s.getBytes(Charset.forName("UTF32")).length
