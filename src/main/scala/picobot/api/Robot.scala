package picobot.api

import picolib._

class Robot(var rules: List[Rule] = List()) {
  private var stateNow: Int = 0
/*
  //Hope this works
  def identifyOpenSurroundings(FutureDirection:MoveDirection):Surroundings={
    if(FutureDirection==North)
      Surroundings(Open,Anything, Anything, Anything)
    if(FutureDirection==East)
      Surroundings(Anything, Open, Anything, Anything)
    if(FutureDirection==West)
      Surroundings(Anything,Anything, Open, Anything)
    else
      Surroundings(Anything,Anything, Anything, Open)
  }

  def identifyBlockedSurroundings(FutureDirection:MoveDirection):Surroundings={
    if(FutureDirection==North)
      Surroundings(Blocked,Anything, Anything, Anything)
    if(FutureDirection==East)
      Surroundings(Anything, Blocked, Anything, Anything)
    if(FutureDirection==West)
      Surroundings(Anything,Anything, Blocked, Anything)
    else
      Surroundings(Anything,Anything, Anything, Blocked)
  }
*/
  def identifyOpenSurroundings(direction: MoveDirection): Surroundings =
    direction match {
      case North => Surroundings(Open, Anything, Anything, Anything)
      case East  => Surroundings(Anything, Open, Anything, Anything)
      case West  => Surroundings(Anything, Anything, Open, Anything)
      case South => Surroundings(Anything, Anything, Anything, Open)
    }


  def identifyBlockedSurroundings(direction: MoveDirection): Surroundings =
    direction match {
      case North => Surroundings(Blocked, Anything, Anything, Anything)
      case East  => Surroundings(Anything, Blocked, Anything, Anything)
      case West  => Surroundings(Anything, Anything, Blocked, Anything)
      case South => Surroundings(Anything, Anything, Anything, Blocked)
    }



  def step(FutureDirection:MoveDirection): Robot={

    val Newrule = Rule(
      State(this.stateNow),
      identifyOpenSurroundings(FutureDirection),
      FutureDirection,
      State(this.stateNow+1))
    this.stateNow = this.stateNow +1
    this.rules = this.rules :+ Newrule

    //return this Robot object
    this
  }

  def sweep(FutureDirection:MoveDirection): Robot={

    val Newrule = List(

      Rule(
      State(this.stateNow),
      identifyOpenSurroundings(FutureDirection),
      FutureDirection,
      State(this.stateNow)),

      Rule(
      State(this.stateNow),
      identifyBlockedSurroundings(FutureDirection),
      StayHere,
      State(this.stateNow+1)

      ))
    this.stateNow = this.stateNow + 1
    this.rules = this.rules ++ Newrule

    //return this Robot object
    this
  }

  def goto(Newstate: Int): Robot ={
    val Newrule = Rule(
      State(this.stateNow),
      Surroundings(Anything, Anything, Anything, Anything),
      StayHere,
      State(Newstate)
    )
    this.rules = this.rules :+ Newrule

    //return this Robot object
    this

  }

}
