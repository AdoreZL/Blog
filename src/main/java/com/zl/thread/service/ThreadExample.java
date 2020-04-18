package com.zl.thread.service;

import java.util.concurrent.*;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/15 10:25
 * @Description
 */
public class ThreadExample {
    /*
    * 构造一个固定线程数目的线程池，配置的corePoolSize与maximumPoolSize大小相同，
    * 同时使用了一个无界LinkedBlockingQueue存放阻塞任务，因此多余的任务将存在再阻塞队列，不会由RejectedExecutionHandler处理
    * */
    public static ExecutorService newFixedThreadPool(int nThreads){
        return new ThreadPoolExecutor(nThreads,nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    /*
    * 构造一个缓冲功能的线程池，配置corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE，keepAliveTime=60s,
    * 以及一个无容量的阻塞队列 SynchronousQueue，因此任务提交之后，将会创建新的线程执行；线程空闲超过60s将会销毁
     * */
    public static ExecutorService newCachedThreadPool(){
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    /*
    * 构造有定时功能的线程池，配置corePoolSize，无界延迟阻塞队列DelayedWorkQueue；
    * 有意思的是：maximumPoolSize=Integer.MAX_VALUE，由于DelayedWorkQueue是无界队列，所以这个值是没有意义的
     * */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    public static ScheduledExecutorService newScheduledThreadPool(
            int corePoolSize, ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
    }

//    public ScheduledThreadPoolExecutor(int corePoolSize,
//                                       ThreadFactory threadFactory) {
//        super(corePoolSize, Integer.MAX_VALUE, 0, TimeUnit.NANOSECONDS,
//                new DelayedWorkQueue(), threadFactory);
//    }
}
