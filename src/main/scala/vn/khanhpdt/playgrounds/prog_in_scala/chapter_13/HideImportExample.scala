package vn.khanhpdt.playgrounds.prog_in_scala.chapter_13

object HideImportExample {
  // to hide a member from an imported package, use: member_name => _
  // e.g.,
  import vn.khanhpdt.playgrounds.prog_in_scala.chapter_13.TestImport.{fieldA => _, _}
  // print(fieldA) // compile error b/c fieldA is not imported
  print(fieldB)

}
