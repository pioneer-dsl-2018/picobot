package picobot.api


import picolib.{MoveDirection, Rule, _}

class Robot(var rules: List[Rule] = List()) {

  var currentstate: Int = 0

  def step(direction_call: MoveDirection): Robot = {
    val input_state = State(this.currentstate)
    val output_state_number = this.currentstate + 1
    val output_state = State(output_state_number)
    val newRule = Rule (
      input_state,
      open_Surroundings(direction_call),
      direction_call,
      output_state
    )

    currentstate = output_state_number
    this.rules = this.rules :+ newRule

    this
  }

  def sweep(direction_call: MoveDirection): Robot = {
    val input_state = State(this.currentstate)
    val output_state_number = this.currentstate + 1
    val output_state = State(output_state_number)
    val Rule_1 = Rule (
      input_state,
      open_Surroundings(direction_call),
      direction_call,
      input_state
    )
    val Rule_2 = Rule (
      input_state,
      blocked_Surroundings(direction_call),
      StayHere,
      output_state
    )
    currentstate = output_state_number
    this.rules = this.rules :+ Rule_1 :+ Rule_2

    this

  }

  def goto(newstate: Int): Robot = {
    val newRule = Rule(
      State(currentstate),
      Surroundings(Anything, Anything, Anything, Anything),
      StayHere,
      State(newstate)
    )
    this.rules = this.rules :+ newRule

    this

  }

  def open_Surroundings(direction: MoveDirection): Surroundings = direction match {
    case North => Surroundings(Open, Anything, Anything, Anything)
    case South => Surroundings(Anything, Anything, Anything, Open)
    case East => Surroundings(Anything, Open, Anything, Anything)
    case West => Surroundings(Anything, Anything, Open, Anything)
  }

  def blocked_Surroundings(direction: MoveDirection): Surroundings = direction match {
    case North => Surroundings(Blocked, Anything, Anything, Anything)
    case South => Surroundings(Anything, Anything, Anything, Blocked)
    case East => Surroundings(Anything, Blocked, Anything, Anything)
    case West => Surroundings(Anything, Anything, Blocked, Anything)
  }

}
