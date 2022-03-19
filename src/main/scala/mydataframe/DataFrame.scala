package mydataframe

case class DataFrame(rows: Seq[Seq[Any]], columnsNames: Seq[String]) {
  private val _frame = columnsNames +: rows

  def show(): Unit = {
    val columnsSizes = _frame.transpose.map(col => col.map(_.toString.length).max)
    val borderString = columnsSizes.map(cs => "-" * cs).mkString("+", "+", "+")
    println(borderString)
    println(rowToString(columnsNames, columnsSizes))
    println(borderString)
    rows.map(rowToString(_, columnsSizes)).foreach(println)
    println(borderString)
  }

  def join(dataFrame: DataFrame, joinColumns: Seq[String]): DataFrame = {
    val columnsIndices = joinColumns
      .map(colName => (columnsNames.indexOf(colName), dataFrame.columnsNames.indexOf(colName)))
    val newRows = rows.flatMap(joinWithMatchingRow(_, dataFrame, columnsIndices))
    val newColumns = columnsNames :++ dataFrame.columnsNames.filter(!columnsNames.contains(_))
    DataFrame(newRows, newColumns)
  }

  def select(selectNames: String*): DataFrame = {
    val newRows = _frame.transpose.filter(col => selectNames.contains(col.head)).transpose.tail
    DataFrame(newRows, selectNames)
  }

  private def rowToString(row: Seq[Any], columnsSizes: Seq[Int]): String =
    row.zip(columnsSizes).map { case (elem, cs) => s"%1$$${cs}s".format(elem) }.mkString("|", "|", "|")

  private def joinWithMatchingRow(row: Seq[Any], dataFrame: DataFrame, indices: Seq[(Int, Int)]): Seq[Seq[Any]] =
    dataFrame.rows
             .filter(dfRow => joinEquals(row, dfRow, indices))
             .map(dfRow => row :++ getRowWithoutIndices(dfRow, indices.map(_._2)))

  private def joinEquals(thisRow: Seq[Any], otherRow: Seq[Any], indices: Seq[(Int, Int)]): Boolean =
    // toString is needed because we are comparing objects of type Any
    indices.forall { case (i, j) => thisRow(i).toString == otherRow(j).toString }

  private def getRowWithoutIndices(row: Seq[Any], indices: Seq[Int]): Seq[Any] =
    row.indices.filter(!indices.contains(_)).map(row(_))


}
