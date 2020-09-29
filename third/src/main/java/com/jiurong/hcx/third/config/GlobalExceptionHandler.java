package com.jiurong.hcx.third.config;

import com.jiurong.hcx.common.config.BaseGlobalExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description 全局异常拦截器
 */
@ControllerAdvice(basePackages = {"com.jiurong.hcx.third.controller", "com.jiurong.hcx.third.client"})
@ResponseBody
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {
}
