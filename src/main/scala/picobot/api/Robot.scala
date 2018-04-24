package picobot.api

import picolib._

class Robot(var rules: List[Rule] = List()) {

  var stateCurrent = 0

  def checkOpenDirection(directionOpen: MoveDirection): Surroundings = {

    directionOpen match {
      case North => Surroundings(Open, Anything, Anything, Anything)
      case South => Surroundings(Anything, Anything, Anything, Open)
      case East => Surroundings(Anything, Open, Anything, Anything)
      case West => Surroundings(Anything, Anything, Open, Anything)
    }
  }
  def checkBlockedDirection(directionBlocked: MoveDirection): Surroundings = {
    directionBlocked match {
      case North => Surroundings(Blocked, Anything, Anything, Anything)
      case South => Surroundings(Anything, Anything, Anything, Blocked)
      case East => Surroundings (Anything, Blocked, Anything, Anything)
      case West => Surroundings(Anything, Anything, Blocked, Anything)
    }
  }
  def sweep(directionSweep: MoveDirection): Robot = {

      val newRule = List (
        Rule(
          State(this.stateCurrent),
          checkOpenDirection(directionSweep),
          directionSweep,
          State(this.stateCurrent)
        ),

        Rule(
          State(this.stateCurrent),
          checkBlockedDirection(directionSweep),
          StayHere,
          State(this.stateCurrent+1)
        )
      )

    this.stateCurrent = this.stateCurrent+1
    this.rules = rules ++ newRule

    //the next line ensures the object returned concurs with expected value, i.e., it is a Robot object

    Robot.this

  }

  def step(directionStep: MoveDirection): Robot = {

    val newRule = List (
      Rule(
        State(this.stateCurrent),
        checkOpenDirection(directionStep),
        directionStep,
        State(this.stateCurrent+1)
      )
    )

    this.stateCurrent = this.stateCurrent+1
    this.rules = rules ++ newRule

    //the next line ensures the object returned concurs with expected value, i.e., it is a Robot object

    Robot.this

  }
  def goto(newStateNum: Int): Robot = {
    val newRule = List (
      Rule (
        State(this.stateCurrent),
        Surroundings(Anything, Anything, Anything, Anything),
        StayHere,
        State(newStateNum)
      )
    )

    this.stateCurrent = this.stateCurrent+1
    this.rules = rules ++ newRule

    //the next line ensures the object returned concurs with expected value, i.e., it is a Robot object

    Robot.this

  }

}

