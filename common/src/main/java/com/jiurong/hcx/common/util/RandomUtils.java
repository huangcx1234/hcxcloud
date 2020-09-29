package com.jiurong.hcx.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class RandomUtils {

    /**
     * 使用当前时间产生随机数,本方法使用线程锁
     *
     * @return 时间串，精确到时间的毫秒级
     */
    public synchronized static String createRandomStringFromDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取指定位数的随机字符串
     *
     * @param length 位数
     * @return 随机字符串
     */
    public synchronized static String createRandomString(int length) {
        if (length < 1) {
            return "";
        }
        Random random = new Random();
        char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz0123456789QWERTYUIOPASDFGHJKLZXCVBNM").toCharArray();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[random.nextInt(72)];
        }
        return new String(randBuffer);
    }

    /**
     * 获取指定位数的随机字符串(只有数字)
     *
     * @param length 位数
     * @return 随机字符串
     */
    public synchronized static String createRandomStringOnlyNum(int length) {
        if (length < 1) {
            return "";
        }
        Random random = new Random();
        char[] numbersAndLetters = ("0123456789").toCharArray();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[random.nextInt(10)];
        }
        return new String(randBuffer);
    }

    /**
     * 创建包含时间字符串的随机字符串
     *
     * @param length 随机字符串长度
     * @return 时间串+随机串
     */
    public synchronized static String createRandomStringIncludeDate(int length) {
        return createRandomStringFromDate() + createRandomString(length);
    }

    /**
     * 创建包含时间字符串的随机字符串(只有数字)
     *
     * @param length 随机字符串长度
     * @return 时间串+随机串
     */
    public synchronized static String createRandomStringIncludeDateOnlyNum(int length) {
        return createRandomStringFromDate() + createRandomStringOnlyNum(length);
    }

}
