package com.zl.blog.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/27 11:57
 * 线程池的简单实现
 */
@Slf4j
public class ThreadPool {

    private final int poolSize;

    private final LinkedBlockingQueue queue;

    private final PoolWorker[] runable;

    public ThreadPool(int poolSize) {
        this.poolSize = poolSize;
        queue = new LinkedBlockingQueue();
        runable = new PoolWorker[poolSize];
        for (int i = 0; i < poolSize; i++) {
            runable[i] = new PoolWorker();
            new Thread(runable[i], "pool-" + i).start();
        }
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class PoolWorker implements Runnable {
        @Override
        public void run() {
            Runnable task;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (Exception e) {
                            log.info("exception in queue waiting :{}", e.getMessage());
                        }
                    }
                    task = (Runnable) queue.poll();
                }
                try {
                    task.run();
                } catch (RuntimeException e) {
                    log.info("run exception : {}", e.getMessage());
                }

            }
        }
    }

    static class ThreadPoolMain {
        public static void main(String[] args) {
            ThreadPool pool = new ThreadPool(5);
            int MaxSize = 100;
            for (int i = 0; i < MaxSize; i++) {
                pool.execute(() -> System.out.println(Thread.currentThread().getName()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
