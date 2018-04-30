package picobot.internal

import picobot.api.Robot
import picolib.MoveDirection

trait NaturalLanguage {
  val robot: Robot
  implicit def convertToInternal (stateNum: Int): Robot = {
    robot.stateCurrent = stateNum
    robot
  }
}
