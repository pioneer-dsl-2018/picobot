package picobot.internal

import picobot.api.Robot
import picolib._

trait NaturalLanguage {
  val robot: Robot

  implicit class Rule(var StateNow: Int){
    def sweep (FutureDirection: MoveDirection): Robot={
      robot.stateNow = this.StateNow
      robot.sweep(FutureDirection)
    }

    def goto (FutureState: Int): Robot = {
      robot.stateNow = this.StateNow
      robot.goto(FutureState)
    }

    def step (FutureDirection: MoveDirection): Robot = {
      robot.stateNow = this.StateNow
      robot.step(FutureDirection)
    }
  }

}
