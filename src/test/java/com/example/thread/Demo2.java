package com.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/15 16:37
 * @Description
 */
public class Demo2 {
    public static void main(String[] args) {
        // 创建一个可重用固定个数的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(() -> {
                try {
                    // 打印正在执行的缓存线程信息
                    System.out.println(Thread.currentThread().getName()
                            + "正在被执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
