package picobot.api

import picolib._

class Robot(var rules: List[Rule] = List()) {

  var state = 0

  def moveBlocked(dir: MoveDirection): Surroundings = {
    if(dir == North)
      Surroundings(Blocked, Anything, Anything, Anything)
    else if (dir == East)
      Surroundings(Anything, Blocked, Anything, Anything)
    else if (dir == South)
      Surroundings(Anything, Anything, Anything, Blocked)
    else
      Surroundings(Anything, Anything, Blocked, Anything)


  }

  def moveOpen(dir: MoveDirection): Surroundings = {
    if(dir == North)
      Surroundings(Open, Anything, Anything, Anything)
    else if (dir == East)
      Surroundings(Anything, Open, Anything, Anything)
    else if (dir == South)
      Surroundings(Anything, Anything, Anything, Open)
    else
      Surroundings(Anything, Anything, Open, Anything)
  }

  def step(dir: MoveDirection): Robot={

    val rule = Rule(
      State(state),
      moveOpen(dir),
      dir,
      State(state + 1))
    state = state + 1
    rules = rules :+ rule

    //return this Robot object
    this
  }
  def sweep(dir: MoveDirection): Robot = {
    val rule =
      List(
        Rule(
          State(state),
          moveOpen(dir),
          dir,
          State(state)
        ),
        Rule(
          State(state),
          moveBlocked(dir),
          StayHere,
          State(state + 1)
        )
      )
    this.state = this.state + 1
    rules = rules ++ rule

    return this
  }
  def goto(state: Int): Robot = {
    val rule = Rule(
      State(this.state),
      Surroundings(Anything, Anything, Anything, Anything),
      StayHere,
      State(state)
    )
    this.state = state
    rules = rules :+ rule
    return this
  }
}
