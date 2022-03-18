package edu.tongji.tjlms.util;

public class ScoreConvertUtil {
    public static String score2Grade(double score)
    {
        if(score>=90)
        {
            return "优";
        }
        else if(score>=80)
        {
            return "良";
        }
        else if(score>=70)
        {
            return "中";
        }
        else if(score>=60)
        {
            return "及格";
        }
        else
        {
            return "不及格";
        }
    }
}
