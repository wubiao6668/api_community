package com.community.common.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    /**
     * 1分钟内显示为 “x秒前“
     * 1小时内的显示为“x分钟前”
     * 今天的显示为“x小时前”
     * “昨天 11:12、前天 11:12“
     * 当年显示具体年月日信息“月-日  11:12”</p>
     * 超过一年天的显示具体年月日信息“年-月-日  11:12”</p>
     *
     * @param localDateTime
     * @return
     */
    public static String dateShow(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return "";
        }
        LocalDateTime nowDateTime = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, nowDateTime);
        //今天
        if (isToday(localDateTime)) {
            long diffSecond = duration.getSeconds();
            if (0 == diffSecond) {
                return "刚刚";
            }
            long minuteUnit = 60;
            if (minuteUnit > diffSecond) {
                return diffSecond + "秒前";
            }
            long hourUnit = 60 * 60;
            if (hourUnit > diffSecond) {
                return (diffSecond / minuteUnit) + "分前";
            }
            return (diffSecond / hourUnit) + "小时前";

        }
        //昨天
        if (isYesterday(localDateTime)) {
            return "昨天 " + localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        //前天
        if (isBeforeYesterday(localDateTime)) {
            return "前天 " + localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        //今年
        if (isThisYear(localDateTime)) {
            return localDateTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
        }
        //去年
        String dateFormat = localDateTime.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
        return dateFormat;
    }

    /**
     * 判断是否为今天（年月日相等）
     *
     * @param localDateTime
     * @return
     */
    public static boolean isToday(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return false;
        }
        LocalDateTime todayDateTime = LocalDateTime.now();
        boolean isToday = localDateTime.getYear() == todayDateTime.getYear() && localDateTime.getMonthValue() == todayDateTime.getMonthValue() && localDateTime.getDayOfMonth() == todayDateTime.getDayOfMonth();
        return isToday;
    }

    /**
     * 判断是否为昨天
     *
     * @param localDateTime
     * @return
     */
    public static boolean isYesterday(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return false;
        }
        return isToday(localDateTime.plusDays(1));
    }

    /**
     * 判断是否为前天
     *
     * @param localDateTime
     * @return
     */
    public static boolean isBeforeYesterday(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return false;
        }
        return isToday(localDateTime.plusDays(2));
    }

    /**
     * 是否同当月
     *
     * @param localDateTime
     * @return
     */
    public static boolean isThisMonth(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return false;
        }
        LocalDateTime todayDateTime = LocalDateTime.now();
        boolean isThisMonth = localDateTime.getYear() == todayDateTime.getYear() && localDateTime.getMonthValue() == todayDateTime.getMonthValue();
        return isThisMonth;
    }

    public static boolean isThisYear(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return false;
        }
        LocalDateTime todayDateTime = LocalDateTime.now();
        boolean isThisYear = localDateTime.getYear() == todayDateTime.getYear();
        return isThisYear;
    }


    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();

//        localDateTime = localDateTime.plusYears(-9);
//        LocalDateTime localDateTime = LocalDateTime.of(2017, 12, 12, 12, 12, 12);
//        System.out.println(localDateTime.);
//        System.out.print(localDateTime.format(DateTimeFormatter.ofPattern("yy年")));
//        System.out.print(localDateTime.format(DateTimeFormatter.ofPattern("MM月")));
//        System.out.print(localDateTime.format(DateTimeFormatter.ofPattern("M月")));
//        System.out.print(localDateTime.format(DateTimeFormatter.ofPattern("dd月")));
//        System.out.print(localDateTime.format(DateTimeFormatter.ofPattern("d月")));
        System.out.println(localDateTime);
//       System.out.println(LocalDate.now().getSecond());
//       System.out.println(LocalTime.now().);
    }


}
