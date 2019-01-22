package com.waimai.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    /**
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取今天的日期
     */

    public static String getToday(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
    /**
     * 获取今天的日期
     */

    public static String getday(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getTime(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }

    //获取前7天的日期list
    public static List getbeforedate(String str){
        Calendar begin = Calendar.getInstance();// 得到一个Calendar的实例
        begin.setTime(new Date()); // 设置时间为当前时间
        begin.add(Calendar.DATE, -6);// 日期减6 包括今天一共7天
        Date result = begin.getTime();
        Calendar end = Calendar.getInstance();
        Long startTime = begin.getTimeInMillis();
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;// 一天的时间转化为ms
        List<String> dates = new ArrayList<>();
        Long time = startTime;
        int i = 0;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dates.add(i, df.format(d));
            i++;
            time += oneDay;
        }
//        for (int j = 0; j < dates.size(); j++) {
////            System.err.println(dates.get(j));
////        }
        return dates;
    }


    //字符串转换日期
    public static Date StringToDate(String dateStr,String formatStr){
        DateFormat dd=new SimpleDateFormat(formatStr);
        Date date=null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
