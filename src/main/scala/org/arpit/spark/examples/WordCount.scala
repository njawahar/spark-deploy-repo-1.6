package org.arpit.spark.examples

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Spark word count")
    val sc = new SparkContext(conf)
    Utility.setupLogging()

    val textFile = sc.textFile("C:\\Users\\support\\Downloads\\spark-examples-master\\spark-examples-master\\src\\main\\resources\\scala_wiki.txt")
    System.setProperty("hadoop.home.dir", "C:\\sparkwindows");
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.foreach(println)
  }
}