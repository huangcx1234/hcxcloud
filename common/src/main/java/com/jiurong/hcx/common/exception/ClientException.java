package com.jiurong.hcx.common.exception;


import java.util.HashMap;
import java.util.Map;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description
 */
public class ClientException extends RuntimeException {
    private int code;
    private String message;

    public ClientException(String message) {
        this(-1, message);
    }

    public ClientException(int code, String message) {
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
