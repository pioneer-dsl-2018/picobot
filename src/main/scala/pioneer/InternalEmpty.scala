package pioneer

import java.io.File

import picobot.library._
import picobot.library.displays.PicobotGUIApp

object InternalEmpty extends PicobotGUIApp {
  maze = Maze("resources" + File.separator + "empty.txt")

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


