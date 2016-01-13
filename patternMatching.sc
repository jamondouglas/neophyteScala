import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState

//patterns in value definitions


class Player(name: String, score: Int)
def currentPlayer(): Player = new Player("Daniel", 3500)

//this isn't working even with val name change complains unresolved Symbol
//val TestPlayer(name, _) = currentPlayer()

//patterns in val var definitions

//patterns in for comprehensions
def gameResults(): Seq[(String, Int)] =
  ("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil

def hallOfFame = for {
  (name, score) <- gameResults()
  if( score > 5000)
} yield name


val lists = List(1,2,3) :: List.empty :: List(5,3) :: Nil

for {
  list @ head :: _ <- lists
} yield list.size


val q = List(1,2,3)