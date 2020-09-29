package com.jiurong.hcx.first.util;

import com.jiurong.hcx.common.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;

/**
 * @author soyeajr
 * @date 2020-3-2
 * @Description
 */
@Slf4j
public class UsernamePasswordUtil {

    public static String getStorePassword(String username, String password) {
        return new SimpleHash("MD5", password, username, 1).toString();
    }

    public static void main(String args[]) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String username = "wuguangsheng";
        String password = RandomUtils.createRandomString(8).toLowerCase();
        String storePassword = getStorePassword(username, password);
        log.info("uuid:{}, username:{}, password:{}, storePassword:{}", uuid, username, password, storePassword);
    }
}
