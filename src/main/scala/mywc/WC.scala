package mywc

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

object WC {
  def wc(filename: String, countSpaces: Boolean): Try[(Int, Int, Int)] = {
    Using(Source.fromFile(filename)) {
      source => {
        // We need toSeq because Iterator can be used only once
        val lines = source.getLines().toSeq
        val linesCount = lines.size
        val words = lines.flatMap(line => line.split("\\s+"))
        val wordsCount = words.size
        val charsCount = if (countSpaces) lines.flatten.size else words.flatten.size
        (linesCount, wordsCount, charsCount)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    wc(args(0), args(1).toBoolean) match {
      case Failure(exception) => println("Failed to read file\n", exception)
      case Success(value) => println(value._1, value._2, value._3)
    }
  }
}
