package com.zl.threadpool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: ZL
 * @Date: 2020/5/12 12:13
 * @Description:
 */
@Service
@Slf4j
public class AsyncTask {
    //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Async("myTaskAsyncPool")
    public void doTask(int i)throws InterruptedException{
        log.info("Task"+i+" started.");
    }
}
