package com.lily.study;

import com.lily.study.entity.Forecast;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SparkSession;

public class TopN {

	public static void main(String[] args) {

		SparkSession spark = SparkSession
		
             SparkSession spark = SparkSession
                .builder()
                .appName("TopN").master("local[*]").config("spark.testing.memory", "2147480000")
                .getOrCreate();

        Integer num=5;

        JavaSparkContext javaSparkContext=JavaSparkContext.fromSparkContext(spark.sparkContext());

        topNum =javaSparkContext.broadcast(num);



        JavaRDD<String> lines = javaSparkContext.textFile("D:/Work/BigData/Sample/groupTop.csv");

        JavaRDD<Score> scoreJavaRDD = lines.map(new Function<String, Score>() {
            @Override
            public Score call(String s) throws Exception {
                return Score.buildScore(s);
            }
        }).filter(new Function<Score, Boolean>() {
            @Override
            public Boolean call(Score score) throws Exception {
                return score != null;
            }
        });


       JavaPairRDD<String,Iterable<Score>> pairRDD= scoreJavaRDD.mapToPair(new PairFunction<Score,String, Score>() {

            @Override
            public Tuple2<String, Score> call(Score score) throws Exception {
                return new Tuple2<String, Score>(score.getCourse(),score);
            }
        }).groupByKey();

       JavaRDD<Tuple2<String, java.util.ArrayList<Score>>> result= pairRDD.map((Function<Tuple2<String, Iterable<Score>>, Tuple2<String, java.util.ArrayList<Score>>>) v1 -> {

            String key= v1._1();
            ArrayList list= com.google.common.collect.Lists.newArrayList(v1._2());
            Collections.sort(list);
            int index=Math.min(topNum.getValue(),list.size());

           ArrayList list2=new ArrayList();

           for(int i=0;i<index;i++){
               list2.add(list.get(i));
           }

            return new Tuple2<>(key, list2);
        });

        System.out.println(result.collect());
    }
>>>>>>> 59d52e4f8e3ce92759a6d86f0d0f912035ad33cf
}
