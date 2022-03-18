package mydataframe

import mydataframe.DataFrameUtilities.ListExtensions

object TestMain {
  def main(args: Array[String]): Unit = {
    val frame = DataFrame(List(List("Michael", 1), List("Andy", 30), List("Justin", 19)), List("name", "age"))
    val frame2 = List(List("Anna", 1), List("Monika", 30), List("Julia", 19)).toDF(List("name", "age"): _*)
    frame.show()
    frame2.show()
  }

}
