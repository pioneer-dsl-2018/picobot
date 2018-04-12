package pioneer

import picolib._
import picolib.displays._

import picobot.api.Robot
import picobot.internal.NaturalLanguage

object InternalEmpty extends PicobotGUIApp with NaturalLanguage {
  map = Map(resource("/empty.txt"))

  val robot = new Robot

  0 sweep West
  1 sweep North
  2 sweep South
  3 step East
  4 sweep North
  5 step East
  6 goto 2

  rules = robot.rules

  run()
}
