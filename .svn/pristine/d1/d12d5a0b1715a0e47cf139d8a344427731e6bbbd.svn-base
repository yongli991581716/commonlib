
package com.common.lib.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期控件
 * 
 * @author liyong
 */
@SuppressLint("SimpleDateFormat")
public class DateTools {
    /**
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm
     */
    public static String getStrTime_ymd_hm(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;

    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getStrTime_ymd_hms(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;

    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy.MM.dd
     */
    public static String getStrTime_ymd(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy
     */
    public static String getStrTime_y(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：MM
     */
    public static String getStrTime_m(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：MM-dd
     */
    public static String getStrTime_md(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：MM月dd日
     */
    public static String getStrTime_MD(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：HH
     */
    public static String getStrTime_h(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：HH:mm
     */
    public static String getStrTime_hm(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：HH:mm:ss
     */
    public static String getStrTime_hms(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：MM-dd HH:mm:ss
     */
    public static String getNewsDetailsDate(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将字符串转为时间戳
     */
    public static String getTime() {
        String re_time = null;
        long currentTime = System.currentTimeMillis();
        @SuppressWarnings("unused")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date d;
        d = new Date(currentTime);
        long l = d.getTime();
        String str = String.valueOf(l);
        re_time = str.substring(0, 10);
        return re_time;
    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy.MM.dd 星期几
     */
    public static String getSection(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  EEEE");
        // 对于创建SimpleDateFormat传入的参数：EEEE代表星期，如“星期四”；MMMM代表中文月份，如“十一月”；MM代表月份，如“11”；
        // yyyy代表年份，如“2010”；dd代表天，如“25”
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;

    }

    /**
     * 将日期转化为字符串
     * 
     * @param date
     * @return
     */
    public static String getStrByDate(Date date) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        re_StrTime = sdf.format(date);
        return re_StrTime;
    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getStrTimeBy16(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 例如：cc_time=1291778220
        BigInteger bInteger = new BigInteger(cc_time, 16);
        re_StrTime = sdf.format(new Date(bInteger.longValue()));
        return re_StrTime;

    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getStrTimeYMDHM(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;

    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd
     */
    public static String getStrTimeYMD(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;

    }

    /**
     * @Title: getCurrentDate
     * @Description: 获得当前时间
     * @author:liyong
     * @param: @return
     * @return: String
     * @throws
     */
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    /**
     * @Title: getCurrentDate
     * @Description: 获得当前时间
     * @author:liyong
     * @param: @return
     * @return: String
     * @throws
     */
    public static String getCurrentYMDDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    /**
     * Date转String
     * 
     * @param count
     * @return
     */
    public static String dateToString(String StrToDate) {

        return DateToStr(StrToDate(StrToDate));
    }

    /**
     * 日期转换成Java字符串
     * 
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换成Java字符串
     * 
     * @param date
     * @return str
     */
    public static String DateToYMDStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * 
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 字符串转换成MM-dd
     * 
     * @param str
     * @return date
     */
    public static String StrToMonthDay(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String monthDay = "";
        Date date = null;
        try {
            date = format.parse(str);
            SimpleDateFormat format2 = new SimpleDateFormat("MM月dd日");
            monthDay = format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return monthDay;
    }

    /*
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd
     */
    public static String getDateYMD(long distanceDays) {
        String re_StrTime = "";
        if (distanceDays < 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 例如：cc_time=1291778220
        long lcc_time = System.currentTimeMillis() - (distanceDays * 24 * 60 * 60 * 1000);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;

    }

    /**
     * 判断time对应的日期是否是今日
     * 
     * @param context
     * @param time
     * @return
     */
    public static boolean isCurrentDay(String time) {
        String currentTime = getCurrentYMDDate();
        // String currentTime = DateFormat
        // .getDateFormat(context).format(new Date());

        time = getStrTimeYMD(time);
        boolean isCurrentDay = false;
        if (currentTime.equals(time))
            isCurrentDay = true;
        return isCurrentDay;
    }
}
