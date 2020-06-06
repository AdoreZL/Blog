package com.example.date;

import java.text.SimpleDateFormat;

import static com.zl.blog.util.DateUtil.getNWeekTimeInterval;

/**
 * @author: ZL
 * @Date: 2020/5/16 17:20
 * @Description:
 */
public class DateTest {
    public static void main(String[] args) {
        System.out.println(getNWeekTimeInterval(4,new SimpleDateFormat("yyyy-MM-dd")));
    }
}
