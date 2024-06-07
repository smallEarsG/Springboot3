package org.example.h5.util.date;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class ShunShunDateUtil {
    /**
     * 1、获取当前时间
     * @return
     */
    public static Date getCurrentDate(){
        return DateUtil.date();
    }

    /**
     * 2、获取当前时间的时间戳（毫秒级）
     * @return 时间
     */
    public static long getCurrentTimestampMS() {
        return DateUtil.current();
    }

    /**
     * 3、获取当前时间的时间戳（秒级）
     * @return 当前时间秒数
     * @since 4.0.0
     */
    public static long getCurrentTimestampS() {
        return DateUtil.currentSeconds();
    }

    /**
     * 4、当前时间格式化，格式 yyyy-MM-dd HH:mm:ss
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return DateUtil.now();
    }

    /**
     * 5、当前日期格式化，格式 yyyy-MM-dd
     * @return 当前日期的标准形式字符串
     */
    public static String today() {
        return DateUtil.today();
    }
}
