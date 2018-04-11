package pioneer

import picolib.{State, _}
import picolib.displays._

object LibraryEmpty extends PicobotGUIApp {
  map = Map(resource("/empty.txt"))

  rules =
    List(
      Rule(
        State(0),
        Surroundings(Open, Anything, Anything, Anything),
        North,
        State(0)
      ),
      Rule(
        State(0),
        Surroundings(Blocked, Anything, Anything, Anything),
        StayHere,
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
        StayHere,
        State(2)
      ),
        Rule(
        State(2),
        Surroundings(Anything, Anything, Open, Anything),
        West,
        State(2)
      ),
        Rule(
        State(2),
        Surroundings(Anything, Anything, Blocked, Anything),
        South,
        State(1)
      ),
// always forgetting to put the commas after the parenthesis

    )

  run()
}
