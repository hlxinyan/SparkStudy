package com.lily.study;

import com.lily.study.entity.Forecast;
import com.lily.study.entity.Score;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Collections;

public class TopNForeCast {


	public static void main(String[] args) {


             SparkSession spark = SparkSession
                .builder()
                .appName("TopN").config("spark.testing.memory", "2147480000")//.master("local[*]")
                .getOrCreate();




        Integer num=5;

        JavaSparkContext javaSparkContext= JavaSparkContext.fromSparkContext(spark.sparkContext());

        Broadcast<Integer> topNum =javaSparkContext.broadcast(num);

        //ip-10-211-221-7.ap-northeast-2.compute.internal:8020
     //hdfs://ip-10-211-221-7.ap-northeast-2.compute.internal:8020/user/mercury/scm-output/scm_forecast_demand_forecast/20180202/forecast_national.csv

        JavaRDD<String> lines = javaSparkContext.textFile(args[0]);

        JavaRDD<Forecast> scoreJavaRDD = lines.map(new Function<String, Forecast>() {
            @Override
            public Forecast call(String s) throws Exception {
                return Forecast.parseForecast(s);
            }
        }).filter(new Function<Forecast, Boolean>() {
            @Override
            public Boolean call(Forecast forecast) throws Exception {
                return forecast != null;
            }
        });


       JavaPairRDD<String,Iterable<Forecast>> pairRDD= scoreJavaRDD.mapToPair(new PairFunction<Forecast,String, Forecast>() {

            @Override
            public Tuple2<String, Forecast> call(Forecast forecast) throws Exception {
                return new Tuple2<String, Forecast>(forecast.getUnit1(),forecast);
            }
        }).groupByKey();


       JavaRDD<Tuple2<String, ArrayList<Forecast>>> result= pairRDD.map((Function<Tuple2<String, Iterable<Forecast>>, Tuple2<String, ArrayList<Forecast>>>) v1 -> {

            String key= v1._1();
            ArrayList list= com.google.common.collect.Lists.newArrayList(v1._2());
             if(list ==null){
                  return null;
             }
            Collections.sort(list);
            int index=Math.min(topNum.getValue(),list.size());

           ArrayList list2=new ArrayList();

           for(int i=0;i<index;i++){
               list2.add(list.get(i));
           }

            return new Tuple2<>(key, list2);
        }).filter(new Function<Tuple2<String, ArrayList<Forecast>>, Boolean>() {
           @Override
           public Boolean call(Tuple2<String, ArrayList<Forecast>> v1) throws Exception {
               return v1 !=null;
           }
       });

        System.out.println(result.collect());

        javaSparkContext.stop();
    }

}
