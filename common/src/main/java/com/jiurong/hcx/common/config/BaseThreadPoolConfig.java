package com.jiurong.hcx.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hcx
 * @date 2019/3/1
 * explain: 线程池
 */
public class BaseThreadPoolConfig {
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//线程池维护线程的最少数量
        executor.setMaxPoolSize(50);//线程池维护线程的最大数量
        executor.setQueueCapacity(8); //缓存队列
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
        executor.setKeepAliveSeconds(60);//允许的空闲时间
        executor.initialize();
        return executor;
    }
}