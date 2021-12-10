// collect with pattern matching
case class C1(f: Option[String])
List(C1(Some("1")), C1(None), C1(Some("2"))).collect {
  case c@C1(Some(f)) => c
}