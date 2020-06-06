package com.zl.blog.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: ZL
 * @Date: 2020/5/16 17:17
 * @Description:
 */
public class DateUtil {
    /**
     * 获取起止日期
     * @param sdf 需要显示的日期格式
     * @param date 需要参照的日期
     * @param n 最近n周
     * @param option 0 开始日期；1 结束日期
     * @param k 0 包含本周 1 不包含本周
     * @return
     */
    public static String getFromToDate(SimpleDateFormat sdf, Date date, int n, int option, int k) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 0 == option ? 1 - dayOfWeek : 7 - dayOfWeek;
        int amount = 0 == option ? offset - (n - 1  + k) * 7 : offset - k * 7;
        calendar.add(Calendar.DATE, amount);
        return sdf.format(calendar.getTime());
    }

    /**
     * 根据当前日期获得最近n周的日期区间（包含本周）
     * @param n
     * @param sdf
     * @return
     */
    public static String getNWeekTimeInterval(int n, SimpleDateFormat sdf) {
        String beginDate = getFromToDate(sdf, new Date(), n, 0, 0);
        String endDate = getFromToDate(sdf, new Date(), n, 1, 0);
        return beginDate + "," + endDate;
    }

    /**
     * 根据当前日期获得最近n周的日期区间（不包含本周）
     * @param n
     * @param sdf
     * @return
     */
    public static String getNWeekTimeIntervalTwo(int n, SimpleDateFormat sdf) {
        String beginDate = getFromToDate(sdf, new Date(), n, 0, 1);
        String endDate = getFromToDate(sdf, new Date(), n, 1, 1);
        return beginDate + "," + endDate;
    }

    /**
     * 根据当前日期获得本周的日期区间（本周周一和周日日期）
     * @param sdf
     * @return
     */
    public static String getThisWeekTimeInterval(SimpleDateFormat sdf) {
        return getNWeekTimeInterval(1, sdf);
    }

    /**
     * 根据当前日期获得上周的日期区间（上周周一和周日日期）
     * @param sdf
     * @return
     */
    public static String getLastWeekTimeInterval(SimpleDateFormat sdf) {
        return getNWeekTimeIntervalTwo(1, sdf);
    }

    public static void main(String[] args) {
        System.out.println(getThisWeekTimeInterval(new SimpleDateFormat("yyyy-MM-dd"))); // 获得本周的日期区间
        System.out.println(getLastWeekTimeInterval(new SimpleDateFormat("yyyy-MM-dd"))); // 获得上周的日期区间
    }
}