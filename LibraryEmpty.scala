package pioneer

import picolib._
import picolib.displays._

object LibraryEmpty extends PicobotGUIApp {
  map = Map(".\\empty.txt")
  rules =
    List(
      Rule(
        State(0),
        Surroundings(Anything, Anything, Open, Anything),
        West,
        State(0)
      ),
      Rule(
        State(0),
        Surroundings(Anything, Anything, Blocked, Anything),
        East,
        State(1)
      ),
      Rule(
        State(1),
        Surroundings(Anything, Open, Anything, Anything),
        East,
        State(1)
      ),
      Rule(
        State(1),
        Surroundings(Anything, Blocked, Anything, Anything),
        West,
        State(2)
      ),
      Rule(
        State(2),
        Surroundings(Open, Anything, Anything, Anything),
        North,
        State(0)
      ),
      Rule(
        State(2),
        Surroundings(Blocked, Anything, Anything, Anything),
        South,
        State(3)
      ),
      Rule(
        State(3),
        Surroundings(Anything, Anything, Anything, Open),
        South,
        State(3)
      ),
      Rule(
        State(3),
        Surroundings(Anything, Anything, Anything, Blocked),
        StayHere,
        State(0)
      ),

    )

  run()
}
