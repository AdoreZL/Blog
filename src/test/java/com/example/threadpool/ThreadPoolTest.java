package com.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/7 9:34
 * 线程池练习
 */


/*
newFixedThreadPool： 创建一个指定线程数量的线程池，如果线程的个数超过线程池允许的最大数量，超出的线程会被存到队列中等待。

newCachedThreadPool： 创建一个可缓存线程池，线程的数量没有限制。如果工作线程空闲了指定时间，则该线程自动终止。

newScheduledThreadPool： 创建一个定长线程池，支持定时以及周期性的任务执行，类似Timer。

newSingleThreadExecutor： 创建一个单线程化的线程池。只创建一个线程来执行任务，并保证顺序地执行任务。如果它异常结束，也会有其他线程替代它。
* */
public class ThreadPoolTest {
    public static void main(String[] args) {
        /**
        *TODO
        *测试线程
        *
        **/
        fixedThreadPoolTest();
        cachedThreadPoolTest();
        scheduledThreadPool();
        singleThreadExecutor();
    }


    public static void fixedThreadPoolTest(){
        //// 线程池的数量规定为3
        int nThreads = 3;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void cachedThreadPoolTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <10; i++) {
            final int index =i;
            try {
                //确保每个线程都能执行完任务
                Thread.sleep(index*1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public static void scheduledThreadPool(){
        int corePoolSize = 3; // 线程池大小为3
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(corePoolSize);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },1,3, TimeUnit.SECONDS);
    }

    //创建唯一的线程
    public static void singleThreadExecutor(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i <10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
