package com.zl.threadpool.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: ZL
 * @Date: 2020/5/12 12:03
 * @Description: 异步线程池配置类
 */
@Configuration
@EnableAsync
public class ThreadExecutorConfig {
    /** 核心线程数 */
    @Value("${corePoolSize}")
    private int corePoolSize;
    /** 最大线程数 */
    @Value("${maxPoolSize}")
    private int maxPoolSize;
    /** 队列数 */
    @Value("${queueCapacity}")
    private int queueCapacity;

    private Executor getExecutor(String name) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(name);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor myTaskAsyncPool() {
        return getExecutor("batchSavePerson-");
    }
}
