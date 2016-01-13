
//Sequence Extractor
object GivenNames {
  def unapplySeq(name: String): Option[Seq[String]] = {
    val names = name.trim.split(" ")
    if (names.forall(_.isEmpty)) None else Some(names)
  }
}

def greetWithFirstName(name: String) = name match {
  case GivenNames(firstName, _*) => "Good Morning, " + firstName
  case _ => "Welcome! Please make sure to fill in your name!"
}

greetWithFirstName("Jamon Douglas")

val x = List(1,2,3,4)


object Name {
  def unapplySeq(name: String): Option[(String, String, Array[String])] = {
    val names = name.trim.split(" ")
    if(names.size < 2 ) None
    else Some((names.last, names.head, names.drop(1).dropRight(1)))
  }
}

def greet(fullName: String) = fullName match {
  case Name(lastName, firstName, _*) => "Good Morning " + firstName +" " + lastName + "!"
  case _ => "Welcome ! Please make sure to fill in your name!"
}

greet("Jamon Douglas")
