import java.nio.charset.Charset

val s = "Hello world".stripMargin
s.length
s.getBytes.length
s.getBytes(Charset.forName("UTF8")).length
s.getBytes(Charset.forName("UTF16")).length
s.getBytes(Charset.forName("UTF32")).length

// split empty string does not provide empty array
"".split(",")


val s2 = "test.com.test.com.test"
s2.lastIndexOf("test.com")

val s3 = "abc"
s3.substring(0, 0)