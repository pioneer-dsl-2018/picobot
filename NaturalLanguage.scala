package picobot.internal

import picolib._
import picobot.api.Robot

trait NaturalLanguage {
  val robot: Robot

  implicit class natural(num: Int) {
    def step(dir: MoveDirection): Robot = {
      robot.state = num
      robot.step(dir)
    }

    def sweep(dir: MoveDirection): Robot = {
      robot.state = num
      robot.sweep(dir)
    }

    def goto(newState: Int): Robot = {
      robot.state = num
      robot.goto(newState)
    }
  }
}
