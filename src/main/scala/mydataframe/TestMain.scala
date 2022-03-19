package mydataframe

import mydataframe.DataFrameUtilities._

object TestMain {
  def main(args: Array[String]): Unit = {
    val frame = DataFrame(List(List("Michael", 1), List("Andy", 30), List("Justin", 19)), List("name", "age"))
    frame.show()

    val emp = Seq(Seq(1,"Smith",-1,"2018","10","M",3000),
                  Seq(2,"Rose",1,"2010","20","M",4000),
                  Seq(3,"Williams",1,"2010","10","M",1000),
                  Seq(4,"Jones",2,"2005","10","F",2000),
                  Seq(5,"Brown",2,"2010","40","",-1),
                  Seq(6,"Brown",2,"2010","50","",-1)
                  )
    val empColumns = Seq("emp_id","name","superior_emp_id","year_joined","dept_id","gender","salary")
    val empDF = emp.toDF(empColumns:_*)
    empDF.show()

    val dept  = Seq(("Finance",10),
                   ("Marketing",20),
                   ("Sales",30),
                   ("IT",40)
                   )

    val deptColumns = Seq("dept_name","dept_id")
    val deptDF = dept.toDF(deptColumns:_*)
    deptDF.show()

    val empDFJoinDeptDF = empDF.join(deptDF,Seq("dept_id"))
    empDFJoinDeptDF.show()
    empDFJoinDeptDF.select("emp_id","name","year_joined", "dept_id", "dept_name").show()

  }

}
