package com.jiurong.hcx.common.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hcx
 * @date 2019/3/20
 * explain:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface OperateLog {
    /**
     * 模块名称
     */
    String module() default "";

    /**
     * 操作名称
     */
    String operate() default "";
}
