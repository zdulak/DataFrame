package mydataframe

object TestMain {
  def main(args: Array[String]): Unit = {
    val frame = DataFrame(List(List("Michael", 1), List("Andy", 30), List("Justin", 19)), List("name", "age"))
    frame.show()
  }

}
