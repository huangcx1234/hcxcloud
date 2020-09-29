package com.jiurong.hcx.common.config;

import com.jiurong.hcx.common.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hcx
 * @date 2019/3/26
 * explain:
 */
@Slf4j
public class BaseHttpLogInterceptor {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.jiurong.pile..*Controller.*(..))")
    public void httpLog() {
    }

    @Before("httpLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!HttpMethod.GET.toString().equals(request.getMethod())) {
            log.info("HTTP " + request.getMethod() + " " + request.getRequestURL().toString() + " {IP:" + IpUtils.getClientIpAddr(request) + "}");
        }
    }

    @AfterReturning(returning = "ret", pointcut = "httpLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!HttpMethod.GET.toString().equals(request.getMethod())) {
            log.info("HTTP EXECUTE TIME:" + (System.currentTimeMillis() - startTime.get()) + ", RESPONSE:" + ret);
        }
        startTime.remove();
    }
}
