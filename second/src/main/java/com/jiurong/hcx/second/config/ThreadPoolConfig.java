package com.jiurong.hcx.second.config;

import com.jiurong.hcx.common.config.BaseThreadPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * @author hcx
 * @date 2019/3/1
 * explain: 线程池
 */

@Configuration
@EnableAsync
public class ThreadPoolConfig extends BaseThreadPoolConfig {
    @Bean(name = "taskExecutor")
    @Override
    public Executor getExecutor() {
        return super.getExecutor();
    }
}