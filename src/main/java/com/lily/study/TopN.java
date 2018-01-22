package com.lily.study;

import com.lily.study.entity.Forecast;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SparkSession;

public class TopN {

	public static void main(String[] args) {

		SparkSession spark = SparkSession
			.builder()
			.appName("JavaWordCount")
			.getOrCreate();

		//SparkConf sparkConf=new SparkConf().setAppName("topN");

		//SparkContext sparkContext=new SparkContext(sparkConf);
		JavaRDD<String> lines = spark.read().textFile("file:///Users/lilyhuang/temp/spark/forecast_national.csv").javaRDD();

		JavaRDD<Forecast> objRDD = lines.map(new Function<String, Forecast>() {

			@Override
			public Forecast call(String v1) throws Exception {

				return Forecast.parseForecast(v1);
			}
		});


		//objRDD.mapToDouble()



	}
}
