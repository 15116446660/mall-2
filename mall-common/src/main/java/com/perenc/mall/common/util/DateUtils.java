package com.perenc.mall.common.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName: DateUtils
 * @Description: 日期相关工具类
 *
 * @Author: GR
 * @Date: 2019-7-15 11:25 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-7-15     GR      		
 */
@Slf4j
public class DateUtils {
    public static final long SECOND = 1000L;
    public static final long MINUTE = 60000L;
    public static final long HOUR = 3600000L;
    public static final long DAY = 86400000L;
    public static final String TIME_BEGIN = " 00:00:00";
    public static final String TIME_END = " 23:59:59";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    public static final String FULL_PATTERN = "yyyyMMddHHmmss";
    public static final String FULL_STANDARD_PATTERN = "yyyyMMdd HH:mm:ss";
    public static final String TRADITION_PATTERN = "yyyy-MM-dd";
    public static final String FULL_TRADITION_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * @description: //TODO 获取当前年月日，默认格式yyyy-MM-dd
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 11:50
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static String getCurrentDate() {
        return getCurrentTimeByFormat(DEFAULT_PATTERN);
    }

    /**
     * @description: //TODO 获取当前具体时间，默认格式H:mm
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 11:56
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getCurrentSpecificTime() {
        return getCurrentTimeByFormat(TIME_PATTERN);
    }

    /**
     * @description: //TODO 获取当前月份
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 11:58
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getMonth() {
        return getCurrentTimeByFormat("MM");
    }

    /**
     * @description: //TODO 获取当天日期
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 11:58
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getDay() {
        return getCurrentTimeByFormat("dd");
    }

    /**
     * @description: //TODO 格式化日期 默认格式：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 11:58
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static String formatData(Date date) {
        return formatDate(date, FULL_TRADITION_PATTERN);
    }

    /**
     * @description: //TODO 格式化日期
     * @param date      当前日期
     * @param format    日期格式
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 11:52
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String formatDate(Date date, String format) {
        if ((null == date) || (StringUtils.isBlank(format))) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * @description: //TODO 获取当前日期，并设置格式
     * @param format
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 11:59
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static String getCurrentTimeByFormat(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * @description: //TODO 字符串转Data
     * @param date
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date parseDate(String date) {
        return parseDate(date, DEFAULT_PATTERN, null);
    }

    /**
     * @description: //TODO 字符串转Data
     * @param date
     * @param df
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date parseDate(String date, String df) {
        return parseDate(date, df, null);
    }

    /**
     * @description: //TODO 将时间字符串转为Data对象
     * @param date  当前传入时间字符串
     * @param df    传入时间格式
     * @param defaultValue
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 12:01
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date parseDate(String date, String df, Date defaultValue) {
        if ((date == null) || (StringUtils.isBlank(df))) {
            return defaultValue;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(df);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            log.error("", e);
        }
        return defaultValue;
    }

    /**
     * @description: //TODO 获取当天开始时间
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:41
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getToDayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat ft = new SimpleDateFormat(FULL_TRADITION_PATTERN);
        return ft.format(todayStart.getTime());
    }

    /**
     * @description: //TODO 获取当天开始时间
     * @param date    具体当天日期
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:46
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date getToDayStartTimeByData(Date date) {
        if (date == null) {
            return null;
        }
        Calendar todayStart = GregorianCalendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return new Date(todayStart.getTime().getTime());
    }

    /**
     * @description: //TODO 获取上一周时间
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:03
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date getPreviousMonday() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        Date date;

        if (dayOfWeek == 1) {
            date = addDays(cd.getTime(), -7);
        } else {
            date = addDays(cd.getTime(), -6 - dayOfWeek);
        }
        return getToDayStartTimeByData(date);
    }

    public static Date getMondayBefore4Week() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7) - 1;
        Date date;

        if (dayOfWeek == 1) {
            date = addDays(cd.getTime(), -28);
        } else {
            date = addDays(cd.getTime(), -27 - dayOfWeek);
        }
        return getToDayStartTimeByData(date);
    }

    public static Date getCurrentMonday() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7) - 1;
        Date date;
        if (dayOfWeek == 1) {
            date = cd.getTime();
        } else {
            date = addDays(cd.getTime(), 1 - dayOfWeek);
        }
        return getToDayStartTimeByData(date);
    }

    /**
     * @description: //TODO 当前日期是否位于指定日期之前
     * @param currenDta     当前日期
     * @param targetData    制定日期
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:05
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean beforeDay(Date currenDta, Date targetData) {
        if (currenDta == null) {
            return true;
        }
        return getToDayStartTimeByData(currenDta).before(getToDayStartTimeByData(targetData));
    }

    /**
     * @description: //TODO 当前日期是否位于指定日期之后
     * @param currenDta     当前日期
     * @param targetData    制定日期
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:05
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean afterDay(Date currenDta, Date targetData) {
        if (currenDta == null) {
            return false;
        }
        return getToDayStartTimeByData(currenDta).after(getToDayStartTimeByData(targetData));
    }

    /**
     * @description: //TODO 添加月份
     * @param date
     * @param months
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:51
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date addMonths(Date date, int months) {
        if (months == 0) {
            return date;
        }
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * @description: //TODO 添加天数
     * @param date
     * @param days
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:07
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date addDays(Date date, int days) {
        if (days == 0) {
            return date;
        }
        if (date == null) {
            return null;
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);

        return new Date(cal.getTime().getTime());
    }

    /**
     * @description: //TODO 添加分钟数
     * @param date
     * @param mins
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:07
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static Date addMins(Date date, int mins) {
        if (mins == 0) {
            return date;
        }
        if (date == null) {
            return null;
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);

        return new Date(cal.getTime().getTime());
    }

    /**
     * @description: //TODO 添加秒数
     * @param date
     * @param secs
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:07
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date addSeconds(Date date, int secs) {
        if (secs == 0) {
            return date;
        }
        if (date == null) {
            return null;
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, secs);
        return new Date(cal.getTime().getTime());
    }

    /**
     * @description: //TODO 是否为相同的月份
     * @param date1
     * @param date2
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:08
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        if ((date1 == null) && (date2 == null)) {
            return true;
        }
        if ((date1 == null) || (date2 == null)) {
            return false;
        }
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(date2);
        return isSameMonth(cal1, cal2);
    }

    /**
     * @description: //TODO 是否位于同一天
     * @param date1
     * @param date2
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:08
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if ((date1 == null) && (date2 == null)) {
            return true;
        }
        if ((date1 == null) || (date2 == null)) {
            return false;
        }
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(date2);
        return (cal1.get(1) == cal2.get(1)) && (cal1.get(2) == cal2.get(2)) && (cal1.get(5) == cal2.get(5));
    }

    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        if ((cal1 == null) && (cal2 == null)) {
            return true;
        }
        if ((cal1 == null) || (cal2 == null)) {
            return false;
        }
        return (cal1.get(1) == cal2.get(1)) && (cal1.get(2) == cal2.get(2));
    }

    public static Date getEndOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, calendar.get(2) + 1);
        calendar.set(5, 0);
        calendar.set(11, 12);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getFirstOfMonth(Date date) {
        Date lastMonth = addMonths(date, -1);
        lastMonth = getEndOfMonth(lastMonth);
        return addDays(lastMonth, 1);
    }

    public static boolean inFormat(String sourceDate, String format) {
        if ((sourceDate == null) || (StringUtils.isBlank(format))) {
            return false;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            dateFormat.parse(sourceDate);
            return true;
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * @description: //TODO 获取制定时间内相隔的月份
     * @param before
     * @param end
     * @return int
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:10
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static int getNumberOfMonthsBetween(Date before, Date end) {
        if ((before == null) || (end == null)) {
            return -1;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(before);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        return (cal2.get(1) - cal1.get(1)) * 12 + (cal2.get(2) - cal1.get(2));
    }

    /**
     * @description: //TODO 获取制定时间内相隔的分钟数
     * @param before
     * @param end
     * @return long
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:11
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static long getNumberOfMinuteBetween(Date before, Date end) {
        if ((before == null) || (end == null)) {
            return -1L;
        }
        long millisec = end.getTime() - before.getTime();
        return millisec / 60000L;
    }

    /**
     * @description: //TODO 获取制定时间内相隔的小时数
     * @param before
     * @param end
     * @return long
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:11
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static long getNumberOfHoursBetween(Date before, Date end) {
        if ((before == null) || (end == null)) {
            return -1L;
        }
        long millisec = end.getTime() - before.getTime() + 1L;
        return millisec / 3600000L;
    }


    /**
     * @description: //TODO 获取制定时间内相隔的天数
     * @param before
     * @param end
     * @return long
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:11
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static long getNumberOfDaysBetween(Date before, Date end) {
        if ((before == null) || (end == null)) {
            return -1L;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(before);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        return getNumberOfDaysBetween(cal, endCal);
    }

    public static long getNumberOfDaysBetween(Calendar cal1, Calendar cal2) {
        if ((cal1 == null) || (cal2 == null)) {
            return -1L;
        }
        cal1.set(Calendar.MILLISECOND, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.HOUR_OF_DAY, 0);

        cal2.set(Calendar.MILLISECOND, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        long elapsed = cal2.getTime().getTime() - cal1.getTime().getTime();
        return elapsed / 86400000L;
    }

    /**
     * @description: //TODO 当月开始时间
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:56
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getMonthStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.DAY_OF_MONTH, 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(todayStart.getTime());
    }

    /**
     * @description: //TODO 当天结束时间
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:57
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(todayEnd.getTime());
    }

    /**
     * @description: //TODO 当天结束时间
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:57
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Date getEndTimeWithStatDate() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.add(Calendar.DAY_OF_MONTH, 1);
        todayEnd.set(Calendar.HOUR_OF_DAY, 1);
        todayEnd.set(Calendar.MINUTE, 30);
        todayEnd.set(Calendar.SECOND, 0);
        todayEnd.set(Calendar.MILLISECOND, 0);
        return todayEnd.getTime();
    }

    /**
     * @description: //TODO 当前时间加多少秒
     * @param second
     * @return java.util.Date
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:58
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static Date getCurrentTimeAddMiDate(int second) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.add(Calendar.SECOND, second);
        return todayEnd.getTime();
    }


    /**
     * @description: //TODO 把没有时分秒的时间转换为当天的结束时间
     * @param date
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:59
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String buildDayEndWithDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(formatter.parse(date));
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(calendar.getTime());
    }

    /**
     * @description: //TODO 获取昨天日期
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 15:01
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR      		
     */
    public static String getDayWithYesterDay() {

        Calendar calendar = Calendar.getInstance();


        calendar.add(Calendar.DAY_OF_MONTH, -1);  //减1天

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }


    /**
     * @description: //TODO 获取当前时间戳
     * @return java.sql.Timestamp
     * @throws
     * @author: GR
     * @date: 2019-7-15 14:56
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }


    public static void main(String[] args) {
        String time = getToDayStartTime();
        System.out.println(time);

    }

}
