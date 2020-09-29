package com.jiurong.hcx.common.config;

import com.jiurong.hcx.common.config.annotation.OperateLog;
import com.jiurong.hcx.common.consts.LoginConst;
import com.jiurong.hcx.common.request.center.platLog.SavePlatLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author hcx
 * @date 2019/3/20
 * explain:
 */
@Slf4j
public abstract class BaseOperateLogInterceptor {

    @AfterReturning(pointcut = "@annotation(com.jiurong.pile.common.config.annotation.OperateLog)")
    void afterReturningMethod(JoinPoint joinPoint) {
        try {
            //获取方法
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            //获取模块名、操作名
            OperateLog operateLog = method.getAnnotation(OperateLog.class);
            String module = operateLog.module();
            String content = operateLog.operate();
            //获取内容
            Object[] args = joinPoint.getArgs();
            if (args.length != 0) {
                Object arg = args[0];
                content += " " + arg;
            }
            //获取登录用户
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String loginInfo = request.getHeader(LoginConst.LOGIN_INFO);
            save(new SavePlatLog(loginInfo, module, content));
        } catch (Exception e) {
            log.error("操作日志记录出错", e);
        }
    }

    public abstract void save(SavePlatLog savePlatLog);
}
