package picobot.internal

import picobot.api.Robot
import picolib._
import picolib.MoveDirection

trait NaturalLanguage {
  val robot: Robot

  implicit class State(statenumber: Int) {

    def step(direction:MoveDirection): Robot = {
      robot.currentstate = statenumber
      robot.step(direction)
    }

    def sweep(direction:MoveDirection): Robot = {
      robot.currentstate = statenumber
      robot.sweep(direction)
    }

    def goto(state:Int): Robot = {
      robot.currentstate = statenumber
      robot.goto(state)
    }

  }


}
