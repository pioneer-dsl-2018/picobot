package pioneer

import picolib._
import picolib.displays._

object LibraryEmpty extends PicobotGUIApp {
  map = Map(resource("/empty.txt"))

  rules =
    List(
      Rule(
        State(0),
        Surroundings(Anything, Anything, Anything, Anything),
        StayHere,
        State(0)
      )
    )

  run()
}
