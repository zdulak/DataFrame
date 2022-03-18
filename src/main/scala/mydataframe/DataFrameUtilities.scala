package mydataframe

object DataFrameUtilities {
  implicit class SeqExtensions(val rows: Seq[Seq[Any]]) {
    def toDF(columnsNames: String*): DataFrame = DataFrame(rows, columnsNames)
  }

}
