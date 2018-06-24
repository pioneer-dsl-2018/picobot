package pioneer

import java.io.FileNotFoundException

import picobot.external.parser.RegexParser.ParseError
import picolib.{Map, Picobot, Rule}
import picolib.displays.PicobotGUIApp
import picobot.external.parser.{RegexParser => Parser}
import picobot.external.semantics._

object External extends PicobotGUIApp {
    val args: Seq[String] = parameters.raw
    
    // Error handling: did the user pass two arguments?
    if (args.length != 2) {
      println(usage)
      sys.exit(1)
    }

    // parse the maze file
    val mazeFileName: String = args.head
    map = Map(getFileLines(mazeFileName))
    
    // parse the program file
    val programFilename: String = args(1)
    val contents: String = getFileContents(programFilename)

    try {
      rules = Parser.parse(contents)
      checkErrors(rules)
      run()
    }
    catch {
      case e: ParseError => println(e.getMessage)
    }

  /** A string that describes how to use the program **/
  def usage = "usage: piconot.external.Piconot <maze-file> <rules-file>"

  /** Given a filename, get a list of the lines in the file */
  def getFileLines(filename: String): List[String] =
    getFileContents(filename).lines.toList

  /** Given a filename, get the contents of the file */
  def getFileContents(filename: String): String =
    try {
      io.Source.fromFile(filename).mkString
    }
    catch { // Error handling: non-existent file
      case e: FileNotFoundException ⇒ { println(e.getMessage); sys.exit(1) }
    }


  /** Check for errors. If there are any print them and exit */
  def checkErrors(rules: Seq[Rule]): Unit = {
    object checker extends DefaultChecker[Seq[Rule]]
        with MoveToWall with BoxedIn with UndefinedStates with UnreachableStates
    val errors = checker.check(rules)
    if (errors.nonEmpty) {
      errors foreach println
      sys.exit(1)
    }
  }
}
