package picobot.library.displays

import picobot.library.{Maze, Picobot, Rule}
import scala.collection.AbstractSeq

trait PicobotController {
  protected var _maze: Maze = null
  protected var _rules: Seq[Rule] = null
  protected var _bot: Picobot = null

  initBot()

  protected def initBot(): Unit = {
    if (this.maze != null && this.rules != null) {
      this._bot = new Picobot(maze, rules)
    }
  }

  def bot: Picobot = _bot

  def maze: Maze = _maze
  def maze_=(newMaze: Maze): Unit = {
    this._maze = newMaze
    initBot()
  }

  def rules: Seq[Rule] = _rules
  def rules_=(newRules: Seq[Rule]): Unit = {
    this._rules = newRules
    initBot()
  }

  def run(): Unit = {
    if (this.bot != null) {
      while (this.bot.canMove) {
        this.step()
      }
    }
  }
  def step(): Unit = if (this.bot != null) this.bot.step()
  def reset(): Unit = if (this.bot != null) this.bot.reset()
}


