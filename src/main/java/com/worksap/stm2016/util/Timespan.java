package com.worksap.stm2016.util;

/**
 * 时间的间隙类。(两个时间之间的间隔。)<br/>
 * 主要用来得到这个时间间隙换算为分钟、小时等。
 *
 * @author 朱志杰 QQ：695520848
 *         May 29, 2013 2:32:28 PM
 */
public class Timespan {

    private long milliseconds = 0;

    /**
     * 功能：构造函数。
     *
     * @param milliseconds 毫秒。
     * @author 朱志杰 QQ：695520848
     * May 29, 2013 2:37:23 PM
     */
    public Timespan(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    /**
     * 功能：时间间隙的总天数。（会强制取整，即求模）
     *
     * @return int
     * @author 朱志杰 QQ：695520848
     * May 29, 2013 2:43:04 PM
     */
    public int totalDays() {
        return totalHours() / 24;
    }

    /**
     * 功能：时间间隙的总小时数。（会强制取整，即求模）
     *
     * @return int
     * @author 朱志杰 QQ：695520848
     * May 29, 2013 2:43:04 PM
     */
    public int totalHours() {
        return totalMinutes() / 60;
    }

    /**
     * 功能：时间间隙的总分钟数。（会强制取整，即求模）
     *
     * @return int
     * @author 朱志杰 QQ：695520848
     * May 29, 2013 2:43:04 PM
     */
    public int totalMinutes() {
        return totalSeconds() / 60;
    }

    /**
     * 功能：时间间隙的总秒数。（会强制取整，即求模）
     *
     * @return int
     * @author 朱志杰 QQ：695520848
     * May 29, 2013 2:43:04 PM
     */
    public int totalSeconds() {
        return new Long(milliseconds / 1000).intValue();
    }

    /**
     * 功能：时间间隙的总毫秒数。
     *
     * @return long
     * @author 朱志杰 QQ：695520848
     * May 29, 2013 2:43:04 PM
     */
    public long totalMilliseconds() {
        return milliseconds;
    }

}
