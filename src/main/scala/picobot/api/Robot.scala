package picobot.api

import picolib.{MoveDirection, Rule}

class Robot(var rules: List[Rule] = List()) {
  def sweep(pos:MoveDirection): Robot{}

  def step(pos:MoveDirection): Robot{}

  def goto(state: Int): Robot {}

}
