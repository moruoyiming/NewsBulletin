package com.mrym.newsbulletion.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * author : nan
 * time : 2016/7/1.
 * 日期工具类
 */
public class DateUtils {
    /**
     * 格式化
     *
     * @param time 时间戳
     *
     * @return 转换成 HH:mm
     */
    public static String formatTimeSimple(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * 格式化时间
     *
     * @param time 时间戳
     *
     * @return 转换成 HH:mm:ss
     */
    public static String formatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * 格式化时间
     *
     * @param time 时间戳
     *
     * @return 转换成YYYY-MM-DD
     */
    public static String formatDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * 格式化时间
     *
     * @param time 时间戳
     * @Param template
     * @return 转换成template
     */
    public static String formatDate(long time, String template) {
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * 得到年份
     *
     * @param lefttime
     *
     * @return 得到年份
     */
    public static int formatYear(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        int i = Integer.parseInt(sDateTime);
        return i;
    }

    /**
     * 得到月份
     *
     * @param lefttime
     *
     * @return 得到月份
     */
    public static int formatMonth(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        int i = Integer.parseInt(sDateTime);
        return i;
    }

    /**
     * 字符串转为long
     *
     * @param time     字符串时间,注意:格式要与template定义的一样
     * @param template 要格式化的格式:如time为09:21:12那么template为"HH:mm:ss"
     *
     * @return long
     */
    public static long formatToLong(String time, String template) {
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        try {
            Date d = sdf.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            long l = c.getTimeInMillis();
            return l;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
