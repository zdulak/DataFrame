package mydataframe

object DataFrameUtilities {
  implicit class SeqExtensions(val rows: Seq[Seq[Any]]) {
    def toDF(columnsNames: String*): DataFrame = DataFrame(rows, columnsNames)
  }

  implicit class ProductExtensions(val rows: Seq[Product]) {
    def toDF(columnsNames: String*): DataFrame = DataFrame(rows.map(_.productIterator.toSeq), columnsNames)
  }
}
