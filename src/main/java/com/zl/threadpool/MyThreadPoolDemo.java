package com.zl.threadpool;

import java.util.concurrent.*;

/**
 * @author: ZL
 * @Date: 2020/4/20 22:12
 * @Description:
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();//一池一线程
//        ExecutorService executorService = Executors.newCachedThreadPool(); //一池N线程
        //服务器核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() ->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}