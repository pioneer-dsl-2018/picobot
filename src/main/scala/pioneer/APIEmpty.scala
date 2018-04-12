package pioneer

import picolib._
import picolib.displays._
import picobot.api.Robot

object APIEmpty extends PicobotGUIApp  {
  map = Map(resource("/empty.txt"))

  val robot = new Robot

  robot.sweep(West)  // state 0
       .sweep(North) // state 1
       .sweep(South) // state 2
       .step(East)   // state 3
       .sweep(North) // state 4
       .step(East)   // state 5
       .goto(2)      // state 6

  rules = robot.rules

  run()
}