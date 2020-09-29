package com.jiurong.hcx.common.util;

import java.net.Inet4Address;
import java.util.Calendar;
import java.util.Date;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class BytesUtils {

    /**
     * IP V4 地址转为字节数组
     *
     * @param ipAddressV4 正规的IP V4 地址，如 192.168.100.78
     * @return 长度为4的字节数组
     */
    public static byte[] ipAddressV4ToBytesL4(String ipAddressV4) {
        if (ipAddressV4 == null || ipAddressV4.trim().length() == 0) {
            return null;
        }
        try {
            return Inet4Address.getByName(ipAddressV4).getAddress();
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddressV4 + " is invalid IP");
        }
    }

    /**
     * 字节数组转化为IPV4地址字符串
     *
     * @param bytesL4 字节数组,长度为4
     * @return IPV4地址字符串，如：192.168.100.78
     */
    public static String bytesL4ToIpAddressV4(byte[] bytesL4) {
        StringBuilder sb = new StringBuilder();
        sb.append(bytesL4[0] & 0xFF).append('.');
        sb.append(bytesL4[1] & 0xFF).append('.');
        sb.append(bytesL4[2] & 0xFF).append('.');
        sb.append(bytesL4[3] & 0xFF);
        return sb.toString();
    }

    /**
     * 8字节的数字转为8字节的数组
     *
     * @param num long
     * @return 字节数组
     */
    public static byte[] numToBytesL8(long num) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (num >>> 56);
        bytes[1] = (byte) (num >>> 48);
        bytes[2] = (byte) (num >>> 40);
        bytes[3] = (byte) (num >>> 32);
        bytes[4] = (byte) (num >>> 24);
        bytes[5] = (byte) (num >>> 16);
        bytes[6] = (byte) (num >>> 8);
        bytes[7] = (byte) (num);
        return bytes;
    }

    /**
     * 7字节的数字转为7字节的数组
     *
     * @param num int
     * @return 字节数组
     */
    public static byte[] numToBytesL7(long num) {
        byte[] bytes = new byte[7];
        bytes[0] = (byte) (num >>> 48);
        bytes[1] = (byte) (num >>> 40);
        bytes[2] = (byte) (num >>> 32);
        bytes[3] = (byte) (num >>> 24);
        bytes[4] = (byte) (num >>> 16);
        bytes[5] = (byte) (num >>> 8);
        bytes[6] = (byte) (num);
        return bytes;
    }

    /**
     * 6字节的数字转为6字节的数组
     *
     * @param num int
     * @return 字节数组
     */
    public static byte[] numToBytesL6(long num) {
        byte[] bytes = new byte[6];
        bytes[0] = (byte) (num >>> 40);
        bytes[1] = (byte) (num >>> 32);
        bytes[2] = (byte) (num >>> 24);
        bytes[3] = (byte) (num >>> 16);
        bytes[4] = (byte) (num >>> 8);
        bytes[5] = (byte) (num);
        return bytes;
    }

    /**
     * 32位的Int转为字节数组，占4字节
     *
     * @param num int
     * @return 字节数组
     */
    public static byte[] numToBytesL4(long num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (num >>> 24);
        bytes[1] = (byte) (num >>> 16);
        bytes[2] = (byte) (num >>> 8);
        bytes[3] = (byte) (num);
        return bytes;
    }

    /**
     * 32位的Int转为字节数组，实际占2字节
     *
     * @param num int
     * @return 2字节数组
     */
    public static byte[] numToBytesL2(long num) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (num >>> 8);
        bytes[1] = (byte) (num);
        return bytes;
    }

    /**
     * 从一个字节数组源中读取指定位置的指定字节数量转为int数字
     *
     * @param srcBytes 字节数组源
     * @param offset   字节数组源当前偏移值
     * @param len      指定转为数字的字节数量，不要超过4
     * @return int数
     */
    public static int bytesToInt(byte[] srcBytes, int offset, int len) {
        byte[] bytesInt = new byte[len];
        System.arraycopy(srcBytes, offset, bytesInt, 0, len);
        return bytesL4ToInt(bytesInt);
    }

    /**
     * 从一个字节数组源中读取指定位置的指定字节数量转为Long数字
     *
     * @param srcBytes 字节数组源
     * @param offset   字节数组源当前偏移值
     * @param len      指定转为数字的字节数量，不要超过8
     * @return long数
     */
    public static long bytesToLong(byte[] srcBytes, int offset, int len) {
        byte[] bytes = new byte[len];
        System.arraycopy(srcBytes, offset, bytes, 0, len);
        return bytesL8ToLong(bytes);
    }

    /**
     * 将最多4字节的byte数组转成一个int值
     *
     * @param bytes 4字节的byte数组,可小于4字节
     * @return int数
     */
    public static int bytesL4ToInt(byte[] bytes) {
        byte[] a = new byte[4];
        int i = a.length - 1;
        int j = bytes.length - 1;
        for (; i >= 0; i--, j--) {//从b的尾部(即int值的低位)开始copy数据
            if (j >= 0) {
                a[i] = bytes[j];
            } else {
                a[i] = 0;//如果b.length不足4,则将高位补0
            }
        }
        int v0 = (a[0] & 0xff) << 24;//&0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        int v1 = (a[1] & 0xff) << 16;
        int v2 = (a[2] & 0xff) << 8;
        int v3 = (a[3] & 0xff);
        return v0 + v1 + v2 + v3;
    }

    /**
     * 将最多8字节的byte数组转成一个long值
     *
     * @param bytes 8字节的byte数组，可小于8字节
     * @return int数
     */
    public static long bytesL8ToLong(byte[] bytes) {
        long[] a = new long[8];
        int i = a.length - 1;
        int j = bytes.length - 1;
        for (; i >= 0; i--, j--) {//从b的尾部(即int值的低位)开始copy数据
            if (j >= 0) {
                a[i] = bytes[j];
            } else {
                a[i] = 0;//如果b.length不足8,则将高位补0
            }
        }
        //&0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        long v0 = (a[0] & 0xff) << 56;
        long v1 = (a[1] & 0xff) << 48;
        long v2 = (a[2] & 0xff) << 40;
        long v3 = (a[3] & 0xff) << 32;
        long v4 = (a[4] & 0xff) << 24;
        long v5 = (a[5] & 0xff) << 16;
        long v6 = (a[6] & 0xff) << 8;
        long v7 = (a[7] & 0xff);
        return v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7;
    }

    /**
     * char 字符转为byte
     *
     * @param c 字符
     * @return byte，1字节
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 比较两个字节数字是否相等
     *
     * @param bytes1 字节数组1
     * @param bytes2 字节数组2
     * @return 是否相等
     */
    public static boolean bytesEquals(byte[] bytes1, byte[] bytes2) {
        if (bytes1 == bytes2) {
            return true;
        }
        if (bytes1 == null) {
            return false;
        }
        if (bytes2 == null) {
            return false;
        }
        if (bytes1.length != bytes2.length) {
            return false;
        }
        for (int i = 0; i < bytes1.length; i++) {
            if (bytes1[i] != bytes2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将数字转为2字节数组并存入指令位置
     *
     * @param bytes  数组
     * @param num    数字
     * @param offset 存放数组偏移量
     */
    public static void putNumberToBytesL2(long num, byte[] bytes, int offset) {
        byte[] bytesL2 = numToBytesL2(num);
        System.arraycopy(bytesL2, 0, bytes, offset, 2);
    }


    /**
     * 将数字转为4字节数组并存入指令位置
     *
     * @param bytes  数组
     * @param num    数字
     * @param offset 存放数组偏移量
     */
    public static void putNumberToBytesL4(long num, byte[] bytes, int offset) {
        byte[] bytesL4 = numToBytesL4(num);
        System.arraycopy(bytesL4, 0, bytes, offset, 4);
    }

    /**
     * 十进制string转BCD编码
     *
     * @param asc
     * @return
     */
    public static byte[] strToBcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    /**
     * 数字转十六进制字符串保留2位
     *
     * @param num
     * @return
     */
    public static String numToHexStringL2(long num) {
        return bytesToHexString(numToBytesL2(num));
    }

    /**
     * 从参数字节数组中获取指定长度的字节数组
     *
     * @param src    字节数组
     * @param offset 偏移量
     * @param len    指定的长度
     * @return 获取到的字节数组
     */
    public static byte[] getBytesForLen(byte[] src, int offset, int len) {
        byte[] bytes = new byte[len];
        System.arraycopy(src, offset, bytes, 0, len);
        return bytes;
    }

    /**
     * 16进制字符串转为byte数组,字符串长度应该为2的倍数否则会丢失最后一个
     *
     * @param hexString 16进制字符串
     * @return byte数组，大小为字符串长度的一半
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return bytes;
    }

    /**
     * byte数组转为16进制字符串
     *
     * @param bytes byte数组
     * @return 16进制字符串，长度为数组大小的两倍
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        for (byte b : bytes) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 从参数字节数组中获取指定长度的字节数组转成16进制字符串
     *
     * @param src    字节数组
     * @param offset 偏移量
     * @param len    指定的长度
     * @return 16进制字符串
     */
    public static String bytesToHexString(byte[] src, int offset, int len) {
        byte[] bytes = getBytesForLen(src, offset, len);
        return bytesToHexString(bytes);
    }

    /**
     * byte数组转为2进制字符串
     *
     * @param bytes byte数组
     * @return 2进制字符串
     */
    public static String bytesToBinaryString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        for (byte b : bytes) {
            int v = b & 0xFF;
            String hv = Integer.toBinaryString(v);
            for (int i = hv.length(); i < 8; i++) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 从参数字节数组中获取指定长度的字节数组转成2进制字符串
     *
     * @param src    字节数组
     * @param offset 偏移量
     * @param len    指定的长度
     * @return 2进制字符串
     */
    public static String bytesToBinaryString(byte[] src, int offset, int len) {
        byte[] bytes = getBytesForLen(src, offset, len);
        return bytesToBinaryString(bytes);
    }

    public static void main(String... args) {
        byte[] bytes = ipAddressV4ToBytesL4("192.168.100.78");
        for (byte b : bytes) {
            System.out.println(b);
        }
        System.out.println(bytesL4ToIpAddressV4(bytes));

        byte[] intBytes = numToBytesL4(Integer.MIN_VALUE);
        for (byte b : intBytes) {
            System.out.println(b);
        }
        System.out.println(bytesL4ToInt(intBytes));
        System.out.println("#######################");
        String str = "0abcffff";
        byte[] strBytes = hexStringToBytes(str);
        for (byte b : strBytes) {
            System.out.println(b);
        }
        System.out.println(bytesToHexString(strBytes));
    }

    /**
     * 从一个字节数组源中读取指定位置的指定字节数量转为字符
     *
     * @param srcBytes 字节数组源
     * @param offset   字节数组源当前偏移值
     * @param len      指定转为数字的字节数量，不要超过4
     * @return int数
     */
    public static String bytesToString(byte[] srcBytes, int offset, int len) {
        byte[] bytesInt = new byte[len];
        System.arraycopy(srcBytes, offset, bytesInt, 0, len);
        return new String(bytesInt);
    }


    /**
     * 将流水号字节数组转化成字符串
     * 规则：将单个字节转成2位的INT不足则补0，最后拼接
     * 最后的消费流水号为年月日时分秒
     *
     * @param srcBytes 字节数组源
     * @param offset   字节数组源当前偏移值
     * @param len      指定转为数字的字节数量
     * @return 流水号字符串
     */
    public static String serialNoByteToString(byte[] srcBytes, int offset, int len) {
        byte[] snByte = getBytesForLen(srcBytes, offset, len);
        StringBuilder sb = new StringBuilder();
        for (byte b : snByte) {
            int i = b & 0xff;
            sb.append(String.format("%02d", i));
        }
        return sb.toString();
    }

    /**
     * 将6个字节的日期字节数组转化为date
     * 6个字节分别对应年月日时分秒，年偏移2000
     *
     * @param dateBytes 日期字节数组
     * @return 流水号字符串
     */
    public static Date bytesL6ToDate(byte[] dateBytes) {
        if (dateBytes.length != 6) {
            return null;
        }
        int offset = 0;
        //年，偏移2000
        Integer year = BytesUtils.bytesToInt(dateBytes, offset++, 1) + 2000;
        //月
        Integer month = BytesUtils.bytesToInt(dateBytes, offset++, 1);
        //日
        Integer day = BytesUtils.bytesToInt(dateBytes, offset++, 1);
        //时
        Integer hour = BytesUtils.bytesToInt(dateBytes, offset++, 1);
        //分
        Integer minute = BytesUtils.bytesToInt(dateBytes, offset++, 1);
        //秒
        Integer second = BytesUtils.bytesToInt(dateBytes, offset, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        return calendar.getTime();
    }

}