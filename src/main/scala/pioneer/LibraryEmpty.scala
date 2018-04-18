package pioneer

import picolib.{Rule, _}
import picolib.displays._

object LibraryEmpty extends PicobotGUIApp {
  map = Map(resource("/empty.txt"))

  rules =
    List(
      Rule(
        State(0),
        Surroundings(Anything, Open, Anything, Anything),
        East,
        State(0)
      ),
      Rule(
        State(0),
        Surroundings(Anything, Blocked, Anything, Open),
        South,
        State(1)
      ),
      Rule(
        State(0),
        Surroundings(Open, Blocked, Anything, Blocked),
        StayHere,
        State(2)
      ),
      Rule(
        State(1),
        Surroundings(Anything, Anything, Open, Open),
        West,
        State(1)
      ),
      Rule(
        State(1),
        Surroundings(Anything, Anything, Blocked, Anything),
        South,
        State(0)
      ),
      Rule(
        State(1),
        Surroundings(Open, Blocked, Open, Blocked),
        StayHere,
        State(2)
      ),
      Rule(
        State(2),
        Surroundings(Open, Anything, Anything, Anything),
        North,
        State(2)
      ),
      Rule(
        State(2),
        Surroundings(Blocked, Anything, Anything, Anything),
        West,
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
        West,
        State(2)
      ),
    )

  run()
}
