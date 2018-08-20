package picobot.internal

import picolib._
import picobot.api.Robot

trait NaturalLanguage {
  val robot: Robot

  implicit class NumberedRule(val stateNumber: Int) {

    def step(direction: MoveDirection): Robot = {
      robot.currentStateNumber = stateNumber
      robot.step(direction)
    }

    def sweep(direction: MoveDirection): Robot = {
      robot.currentStateNumber = stateNumber
      robot.sweep(direction)
    }

    def goto(newState: Int): Robot = {
      robot.currentStateNumber = stateNumber
      robot.goto(newState)
    }

  }

  /**
    * There's another way to implement numbered rules, using implicit defs:
    *
    *   implicit def numberToRule(ruleNumber: Int): Robot = {
    *     robot.currentStateNumber = ruleNumber
    *     robot
    *   }
    */
}
