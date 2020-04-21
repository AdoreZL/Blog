package com.zl.thread.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{
    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("come in Callable");
        return 1024;
    }
}

/**
 * @author: ZL
 * @Date: 2020/4/20 21:40
 * @Description: 多线程中第三种获得多线程的方法
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        Thread thread = new Thread(futureTask,"AAA");
        thread.start();
    }
}
