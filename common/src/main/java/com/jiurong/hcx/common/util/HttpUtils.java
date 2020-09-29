package com.jiurong.hcx.common.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
    /**
     * 从header中获取用户权限域id
     * @param request
     * @return 权限域的id
     */
    public static String getAuthGroupId(HttpServletRequest request) {
        return request.getHeader("groupId");
    }
}
