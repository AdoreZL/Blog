package com.zl.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/8 10:38
 * @Description
 */
@Configuration
public class GlobalConfig {
/*
 * 默认线程池线程池
 */

    public ThreadPoolTaskExecutor defaultThreadPool(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数目
        taskExecutor.setCorePoolSize(8);
        //指定最大线程数
        taskExecutor.setMaxPoolSize(32);
        //队列中最大的数目
        taskExecutor.setQueueCapacity(8);
        //线程名称前缀
        taskExecutor.setThreadNamePrefix("defaultThreadPool_");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后的最大存活时间
        taskExecutor.setKeepAliveSeconds(60);
        //加载
        taskExecutor.initialize();
        return  taskExecutor;

    }
}
