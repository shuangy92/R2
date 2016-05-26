package com.worksap.stm2016.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DATE 继承于 java.util.Date，多实现了很多方法。
 *
 * @author 朱志杰 QQ：695520848
 *         May 29, 2013 9:52:51 AM
 */
public class DateUtil extends java.util.Date {

    /**
     *
     */
    private static final long serialVersionUID = 2155545266875552658L;


    /**
     * 功能：得到当月有多少天。
     *
     * @return int
     * @author 朱志杰 QQ：695520848
     * Jul 2, 2013 4:59:41 PM
     */
    public int daysNumOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this);
        return cal.getActualMaximum(Calendar.DATE);
    }


    /**
     * 将yyyy-MM-dd HH:mm:ss字符串转换成日期(net.maxt.util.Date)<br/>
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss字符串
     * @return net.maxt.util.Date 日期 ,转换异常时返回null。
     */

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date addDays(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }
}
