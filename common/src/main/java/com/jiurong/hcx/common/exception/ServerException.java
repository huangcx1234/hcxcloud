package com.jiurong.hcx.common.exception;


import java.util.HashMap;
import java.util.Map;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description
 */
public class ServerException extends RuntimeException {
    private int code;
    private String message;

    public ServerException(String message) {
        this(-1, message);
    }

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object toResponseBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }
}
