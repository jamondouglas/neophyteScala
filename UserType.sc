//Interface Java
trait User {
  def name: String
}

class FreeUser(val name: String) extends User
class PremiumUser(val name: String) extends User

//companion Object(singleton)
object FreeUser {
  def unapply(user: FreeUser): Option[String]= Some(user.name)
}
//companion object(singleton)
object PremiumUser {
  def unapply(user: PremiumUser): Option[String] = Some(user.name)
}

//calling unapply dircetly from FreeUser companion object, using FreeUser constructor
FreeUser.unapply(new FreeUser("Jamon"))

val user: User = new FreeUser("Terril")

user match {
    // unapply method from companion object called
  case FreeUser(name) => "Hello " + name
  case PremiumUser(name) => "Welcome back, dear "+ name
}

//Extractor with multiple values
trait UserV2 {
  def name: String
  def score: Int
}

class FreeUserV2(val name: String, val score: Int, val upgradeProbability: Double) extends UserV2
class PremiumUserV2(val name: String, val score: Int) extends UserV2

object FreeUserV2 {
  def unapply(user: FreeUserV2): Option[(String, Int, Double)] = Some((user.name, user.score, user.upgradeProbability))
}

object PremiumUserV2 {
  def unapply(user: PremiumUserV2): Option[(String, Int)] = Some((user.name, user.score))
}

val user2: UserV2 = new FreeUserV2("Jamon", 100, 0.7d)
user2 match {
  case FreeUserV2(name, _, p) => if(p > 0.75) name + ", what can we do for you today?" else "Hello " + name
  case PremiumUserV2(name, _) => "Welcome back, dear " + name
}


//Boolean Extractor
def initiateSpamProgram(user: FreeUserV2): String = "We need to spam you now "+ user.name

//extractor returning Boolean
object premiumCandidate {
  def unapply(user: FreeUserV2): Boolean = user.upgradeProbability > 0.75
}

val user3: UserV2 = new FreeUserV2("Terr", 4300, 0.8d)


user3 match {
  case freeUser @ premiumCandidate() => initiateSpamProgram(freeUser)
}

//Stram Extractor
val xs = 58 #:: 43 #:: 93 #:: Stream.empty
val cs = 32 :: 34 :: 32 :: List()

cs match {
  case first :: second :: _ => first - second
  case _ => -1
}

xs match {
  case #::(first, #::(second, _)) => first - second
  case _ => -1
}
