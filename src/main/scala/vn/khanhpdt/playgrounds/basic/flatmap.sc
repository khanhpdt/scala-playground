// shortcut flatMap on empty list
val l1 = List.empty[Option[String]]
l1.flatMap {
  case Some(x) => Some(x)
  case _ => None
}
// it works as expected