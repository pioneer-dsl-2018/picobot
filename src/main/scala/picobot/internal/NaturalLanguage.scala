package picobot.internal

import picobot.api.Robot
import picolib._

trait NaturalLanguage {
  val robot: Robot

  implicit class Rule(val StateNow: Int){
    def sweep (FutureDirection: MoveDirection): Robot={

    }

    def goto (FutureState: Int): Robot = {

    }

    def step (FutureDirection: MoveDirection): Robot = {

    }
  }

}
