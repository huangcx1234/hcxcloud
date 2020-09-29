package com.jiurong.hcx.common.mybatis;

import com.jiurong.hcx.common.mybatis.annotation.CreateTime;
import com.jiurong.hcx.common.mybatis.annotation.UpdateTime;
import com.jiurong.hcx.common.mybatis.annotation.UUID;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description Mybatis插入更新拦截器
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) objects[0];
        String commandType = mappedStatement.getSqlCommandType().name();
        Object object = objects[1];
        Field[] fields = object.getClass().getDeclaredFields();
        if ("INSERT".equals(commandType)) {
            for (Field field : fields) {
                //如果字段不为空，则不进行相应设置
                field.setAccessible(true);
                if (field.get(object) != null) {
                    continue;
                }
                UUID uuid = field.getAnnotation(UUID.class);
                CreateTime createTime = field.getAnnotation(CreateTime.class);
                UpdateTime updateTime = field.getAnnotation(UpdateTime.class);
                if (uuid != null) {
                    field.set(object, java.util.UUID.randomUUID().toString().replaceAll("-", ""));
                } else if (createTime != null) {
                    field.set(object, Calendar.getInstance().getTime());
                } else if (updateTime != null) {
                    field.set(object, Calendar.getInstance().getTime());
                }
            }
        } else if ("UPDATE".equals(commandType)) {
            for (Field field : fields) {
                UpdateTime updateTime = field.getAnnotation(UpdateTime.class);
                if (updateTime != null) {
                    field.setAccessible(true);
                    field.set(object, Calendar.getInstance().getTime());
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
