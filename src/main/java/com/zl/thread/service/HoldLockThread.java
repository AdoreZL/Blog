package com.zl.thread.service;

import java.util.concurrent.TimeUnit;

/**
 * @author: ZL
 * @Date: 2020/4/21 15:32
 * @Description:
 */
public class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"\t 尝试获得："+lockB);
            //暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"\t 尝试获得："+lockA);
                //暂停一会儿线程
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}


class DeadLockDemo{
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";

        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadAAA").start();
    }
}