package picobot.api

import picolib._

class Robot(var rules: List[Rule] = List()) {
  private var stateNow: Int = 0

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

  def sweep(FutureDirection:MoveDirection): Robot={

    val Newrule = Rule(
      State(this.stateNow),
      identifyOpenSurroundings(FutureDirection),
      FutureDirection,
      State(this.stateNow+1))
    this.rules = this.rules :+ Newrule

    this
  }

  def step(pos:MoveDirection): Robot={

  }

  def goto(state: Int): Robot ={

  }

}
