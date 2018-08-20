package picobot.external.parser

import picolib._

import scala.util.matching.Regex

object RegexParser {

  class ParseError(message: String) extends Exception(message)

  def fail(message: String) = throw new ParseError(message)

  def parse(input: String): Seq[Rule] = {
    val rules = input.lines flatMap parseLine
    if (rules.isEmpty) {
      fail("Program needs at least one rule")
    } else {
      rules.toList
    }
  }

  def parseLine(line: String): Option[Rule] = {
    val rulePattern = new Regex(
      """^((\d+)\s+([N|\*|x])([E|\*|x])([W|\*|x])([S|\*|x])\s+\->\s+([N|E|W|S|X])\s+(\d+))?\s*(#.*)?$""",
      "rule", "start", "north", "east", "west", "south", "direction", "end", "comment"
    )

    // Try to parse the line
    val result = rulePattern findFirstMatchIn line

    // Parsing failed
    if (result.isEmpty) {
      fail("Syntax error")
    }

    val data = result.get

    // A blank line or a line containing only a comment
    if (data.group("rule") == null) {
      None
    } else {
      // If parsing succeeds, convert each piece of the rule to the
      // corresponding data structure in the Picobot library
      val startState = State(data.group("start"))
      val surroundings =
        Surroundings(
          parseSurrounding(data.group("north")),
          parseSurrounding(data.group("east")),
          parseSurrounding(data.group("west")),
          parseSurrounding(data.group("south"))
        )
      val moveDirection = parseMoveDirection(data.group("direction"))
      val endState = State(data.group("end"))

      // Return the result
      val rule = Rule(startState, surroundings, moveDirection, endState)
      Some(rule)
    }
  }

  /** Converts a string description of the surroundings in one direction to
    * a picolib description of the surroundings
    */
  def parseSurrounding(description: String): RelativeDescription =
    description match {
      case "N" => Blocked
      case "E" => Blocked
      case "W" => Blocked
      case "S" => Blocked
      case "x" => Open
      case "*" => Anything
    }

  /** Converts a string description of a move direction to a picolib description
    * of a move direction
    */
  def parseMoveDirection(direction: String): MoveDirection =
    direction match {
      case "N" => North
      case "E" => East
      case "W" => West
      case "S" => South
      case "X" => StayHere
    }
}