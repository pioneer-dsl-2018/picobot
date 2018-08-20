package picobot.api

import picolib._

class Robot(var rules: List[Rule] = List()) {

  var currentStateNumber: Int = 0

  /** add a rule that steps the robot in the given direction */
  def step(direction: MoveDirection): Robot = {
    val currentState = State(this.currentStateNumber)
    val newStateNumber = this.currentStateNumber + 1
    val newState = State(newStateNumber)
    val newRule =
      Rule(
        currentState,
        openDirection(direction),
        direction,
        newState
      )

    currentStateNumber = newStateNumber
    this.rules = this.rules :+ newRule

    this
  }

  /**
    * add rules that repeatedly step a robot in the given direction,
    * until that direction is blocked
    */
  def sweep(direction: MoveDirection): Robot = {
    val currentState = State(this.currentStateNumber)
    val newStateNumber = this.currentStateNumber + 1
    val newState = State(newStateNumber)

    val openRule =
      Rule(
        currentState,
        openDirection(direction),
        direction,
        currentState
      )

    val blockedRule =
      Rule(
        currentState,
        blockedDirection(direction),
        StayHere,
        newState
      )

    currentStateNumber = newStateNumber
    this.rules = this.rules ++ List(openRule, blockedRule)

    this
  }

  /** add a rule that transitions to another state, without moving the robot */
  def goto(stateNumber: Int): Robot = {
    val newRule =
      Rule(
        State(this.currentStateNumber),
        Surroundings(Anything, Anything, Anything, Anything),
        StayHere,
        State(stateNumber)
      )

    rules = this.rules :+ newRule

    this
  }

  /** creates a Surroundings that matches an `Open` state in the given direction */
  def openDirection(direction: MoveDirection): Surroundings =
    direction match {
      case North => Surroundings(Open, Anything, Anything, Anything)
      case East  => Surroundings(Anything, Open, Anything, Anything)
      case West  => Surroundings(Anything, Anything, Open, Anything)
      case South => Surroundings(Anything, Anything, Anything, Open)
    }

  /** creates a Surroundings that matches a `Blocked` state in the given direction */
  def blockedDirection(direction: MoveDirection): Surroundings =
    direction match {
      case North => Surroundings(Blocked, Anything, Anything, Anything)
      case East  => Surroundings(Anything, Blocked, Anything, Anything)
      case West  => Surroundings(Anything, Anything, Blocked, Anything)
      case South => Surroundings(Anything, Anything, Anything, Blocked)
    }
}
