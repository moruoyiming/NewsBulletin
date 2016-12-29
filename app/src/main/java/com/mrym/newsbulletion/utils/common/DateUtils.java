package com.mrym.newsbulletion.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 * Log日志的工具类
 */
public class DateUtils {

    private static final long MILLIS_PER_DAY = 86400000L;
    private static final long INTERVAL_IN_MILLISECONDS = 30000L;

    public static String getTimestampString(Date paramDate) {
        String str = null;
        long l1 = paramDate.getTime();
        long l2 = System.currentTimeMillis();
        if (isSameDay(l1, l2)) {
            Calendar localCalendar = GregorianCalendar.getInstance();
            localCalendar.setTime(paramDate);
            int i = localCalendar.get(11);
            if (i > 17)
                str = "晚上 hh:mm";
            else if ((i >= 0) && (i <= 6))
                str = "凌晨 hh:mm";
            else if ((i > 11) && (i <= 17))
                str = "下午 hh:mm";
            else
                str = "上午 hh:mm";
        } else if (isYesterday(l1, l2)) {
            str = "昨天 HH:mm";
        } else {
            str = "M月d日 HH:mm";
        }
        return new SimpleDateFormat(str, Locale.CHINA).format(paramDate);
    }

    public static boolean isCloseEnough(long paramLong1, long paramLong2) {
        long l = paramLong1 - paramLong2;
        if (l < 0L)
            l = -l;
        return l < 300000L;
    }

    public static boolean isCloseEnough(String params1, String params2) throws ParseException {
        long paramLong1 = stringToLong(params1, "yyyy-MM-dd");
        long paramLong2 = stringToLong(params2, "yyyy-MM-dd");
        long l = paramLong1 - paramLong2;
        if (l < 0L)
            l = -l;
        return l < 300000L;
    }

    /**
     * @param strTime    要转换的String类型的时间
     * @param formatType 时间格式
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * @param date
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    private static boolean isSameDay(long paramLong1, long paramLong2) {
        long l1 = paramLong1 / 86400000L;
        long l2 = paramLong2 / 86400000L;
        return l1 == l2;
    }

    private static boolean isYesterday(long paramLong1, long paramLong2) {
        long l1 = paramLong1 / 86400000L;
        long l2 = paramLong2 / 86400000L;
        return l1 + 1L == l2;
    }

    public static Date StringToDate(String paramString1, String paramString2) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
        Date localDate = null;
        try {
            localDate = localSimpleDateFormat.parse(paramString1);
        } catch (ParseException localParseException) {
            localParseException.printStackTrace();
        }
        return localDate;
    }

    public static String toTime(int paramInt) {
        paramInt /= 1000;
        int i = paramInt / 60;
        int j = 0;
        if (i >= 60) {
            j = i / 60;
            i %= 60;
        }
        int k = paramInt % 60;
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(k)});
    }

    public static TimeInfo getYesterdayStartAndEndTime() {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.add(5, -1);
        localCalendar1.set(11, 0);
        localCalendar1.set(12, 0);
        localCalendar1.set(13, 0);
        localCalendar1.set(14, 0);
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.add(5, -1);
        localCalendar2.set(11, 23);
        localCalendar2.set(12, 59);
        localCalendar2.set(13, 59);
        localCalendar2.set(14, 999);
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();
        TimeInfo localTimeInfo = new TimeInfo();
        localTimeInfo.setStartTime(l1);
        localTimeInfo.setEndTime(l2);
        return localTimeInfo;
    }

    public static TimeInfo getTodayStartAndEndTime() {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.set(11, 0);
        localCalendar1.set(12, 0);
        localCalendar1.set(13, 0);
        localCalendar1.set(14, 0);
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.set(11, 23);
        localCalendar2.set(12, 59);
        localCalendar2.set(13, 59);
        localCalendar2.set(14, 999);
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();
        TimeInfo localTimeInfo = new TimeInfo();
        localTimeInfo.setStartTime(l1);
        localTimeInfo.setEndTime(l2);
        return localTimeInfo;
    }

    public static TimeInfo getBeforeYesterdayStartAndEndTime() {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.add(5, -2);
        localCalendar1.set(11, 0);
        localCalendar1.set(12, 0);
        localCalendar1.set(13, 0);
        localCalendar1.set(14, 0);
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.add(5, -2);
        localCalendar2.set(11, 23);
        localCalendar2.set(12, 59);
        localCalendar2.set(13, 59);
        localCalendar2.set(14, 999);
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();
        TimeInfo localTimeInfo = new TimeInfo();
        localTimeInfo.setStartTime(l1);
        localTimeInfo.setEndTime(l2);
        return localTimeInfo;
    }

    public static TimeInfo getCurrentMonthStartAndEndTime() {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.set(5, 1);
        localCalendar1.set(11, 0);
        localCalendar1.set(12, 0);
        localCalendar1.set(13, 0);
        localCalendar1.set(14, 0);
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();
        Calendar localCalendar2 = Calendar.getInstance();
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();
        TimeInfo localTimeInfo = new TimeInfo();
        localTimeInfo.setStartTime(l1);
        localTimeInfo.setEndTime(l2);
        return localTimeInfo;
    }

    public static TimeInfo getLastMonthStartAndEndTime() {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.add(2, -1);
        localCalendar1.set(5, 1);
        localCalendar1.set(11, 0);
        localCalendar1.set(12, 0);
        localCalendar1.set(13, 0);
        localCalendar1.set(14, 0);
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.add(2, -1);
        localCalendar2.set(5, 1);
        localCalendar2.set(11, 23);
        localCalendar2.set(12, 59);
        localCalendar2.set(13, 59);
        localCalendar2.set(14, 999);
        localCalendar2.roll(5, -1);
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();
        TimeInfo localTimeInfo = new TimeInfo();
        localTimeInfo.setStartTime(l1);
        localTimeInfo.setEndTime(l2);
        return localTimeInfo;
    }

    public static String getTimestampStr() {
        return Long.toString(System.currentTimeMillis());
    }
}
