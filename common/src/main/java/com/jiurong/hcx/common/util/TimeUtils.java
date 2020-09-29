package com.jiurong.hcx.common.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class TimeUtils {
    public static String getTimeYMDHmsS(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sf.format(time);
    }

    public static String getTimeYMDHms(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(time);
    }

    public static String getTimeYMD(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(time);
    }

    public static String getTimeYM(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.format(time);
    }

    public static String getTimeY(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(time);
    }

    @SneakyThrows
    public static Date getDateYMDHmsS(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sf.parse(sf.format(time));
    }

    @SneakyThrows
    public static Date getDateYMDHms(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.parse(sf.format(time));
    }

    @SneakyThrows
    public static Date getDateYMD(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.parse(sf.format(time));
    }

    @SneakyThrows
    public static Date getDateYM(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.parse(sf.format(time));
    }

    @SneakyThrows
    public static Date getDateY(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.parse(sf.format(time));
    }

    @SneakyThrows
    public static Date getDateByYMDHmsS(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sf.parse(time);
    }

    @SneakyThrows
    public static Date getDateByYMDHms(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.parse(time);
    }

    @SneakyThrows
    public static Date getDateByYMD(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.parse(time);
    }

    @SneakyThrows
    public static Date getDateByYM(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.parse(time);
    }

    @SneakyThrows
    public static Date getDateByY(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.parse(time);
    }


    public static String date2Str(Date date, SimpleDateFormat sdf) {
        if (date == null || sdf == null) {
            return null;
        }
        return sdf.format(date);
    }
}
