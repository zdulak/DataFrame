package mydataframe

object DataFrameUtilities {
  implicit class ListExtensions(val rows: List[List[Any]]) {
    def toDF(columnsNames: String*): DataFrame = DataFrame(rows, columnsNames.toList)
  }

}
