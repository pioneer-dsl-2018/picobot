package pioneer

import picobot.api.Robot
import picolib._
import picolib.displays._

object APIEmpty extends PicobotGUIApp {
  map = Map(".\\empty.txt")

  val robot = new Robot
  robot.sweep(West)
    .sweep(North)
    .sweep(South)
    .step(East)
    .sweep(North)
    .step(East)
    .goto(2)

  rules = robot.rules

  run()
}