package pioneer

import picobot.library._
import picobot.library.displays._

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
