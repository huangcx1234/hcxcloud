package com.jiurong.hcx.common.util;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class CrcUtils {
    /**
     * crc16校验
     * CRC-16/MODBUS  x16+x15+x2+1
     *
     * @param data byte数组
     * @return
     */
    public static int crc16(byte[] data) {
        // 存储需要产生校验码的数据
        byte[] buf = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            buf[i] = data[i];
        }
        int len = buf.length;
        int crc = 0xFFFF;//16位
        for (int pos = 0; pos < len; pos++) {
            if (buf[pos] < 0) {
                crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of
                // crc
            } else {
                crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc
            }
            for (int i = 8; i != 0; i--) { // Loop over each bit
                if ((crc & 0x0001) != 0) { // If the LSB is set
                    crc >>= 1; // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                } else {
                    // Else LSB is not set
                    crc >>= 1; // Just shift right
                }
            }
        }
        return crc;
    }
}
