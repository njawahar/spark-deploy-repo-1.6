package org.arpit.spark.examples

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object SparkMysql {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("SparkMysqlCommand")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val df_mysql = sqlContext.read.format("jdbc").option("url", "jdbc:mysql://localhost/mysql")
      .option("driver", "com.mysql.jdbc.Driver").option("dbtable", "user").option("user", "root").option("password", "sathya").load()
    df_mysql.show
    df_mysql.registerTempTable("names")
    df_mysql.sqlContext.sql("select * from names limit 10").collect.foreach(println)
  }
}