package mydataframe

case class DataFrame(rows: List[List[Any]], columnsNames: List[String]) {
  private val _frame = columnsNames :: rows
  
  def show(): Unit = {
    val columnsSizes = _frame.transpose.map(col => col.map(_.toString.length).max)
    val borderString = columnsSizes.map(cs => "-" * cs).mkString("+", "+", "+")
    println(borderString)
    println(rowToString(columnsNames, columnsSizes))
    println(borderString)
    rows.map(rowToString(_, columnsSizes)).foreach(println)
    println(borderString)
  }

  private def rowToString(row: List[Any], columnsSizes: List[Int]): String = {
    row.zip(columnsSizes).map { case (elem, cs) => s"%1$$${cs}s".format(elem) }.mkString("|", "|", "|")
  }
}
