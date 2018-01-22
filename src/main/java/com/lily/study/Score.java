package cn.com.conversant.swiftsight.core;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

/**
 * Created by hlxin on 2018/1/19.
 */
public class Score implements Comparable<Score>,java.io.Serializable {
    private String name;
    private String course;
    private double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static Score buildScore(String s){
        if(StringUtils.isBlank(s)) return null;
        String[] arrays=s.split(",");
        Score score=new Score();
        score.setName(arrays[0]);
        score.setCourse(arrays[1]);
        score.setScore(Double.valueOf(arrays[2]));
        return score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Score score) {
        return (this.getScore()>=score.getScore())?-1:1;
    }


}
