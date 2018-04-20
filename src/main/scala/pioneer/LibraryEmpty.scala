package pioneer

import picolib._
import picolib.displays._

object LibraryEmpty extends PicobotGUIApp {
  map = Map(resource("/empty.txt"))

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
        North,
        State(1)
      ),
      Rule(
        State(1),
        Surroundings(Open, Anything, Anything, Anything),
        North,
        State(1)
      ),
      Rule(
        State(1),
        Surroundings(Blocked, Anything, Anything, Anything),
        South,
        State(2)
      ),
      Rule(
        State(2),
        Surroundings(Anything, Anything, Anything, Open),
        South,
        State(2)
      ),
      Rule(
        State(2),
        Surroundings(Anything, Anything, Anything, Blocked),
        East,
        State(3)
      ),
      Rule(
        State(3),
        Surroundings(Open, Anything, Anything, Anything),
        North,
        State(3)
      ),
      Rule(
        State(3),
        Surroundings(Blocked, Anything, Anything, Anything),
        East,
        State(1)
      ),
    )

  run()
}
