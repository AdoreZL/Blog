package com.example.lock;

import java.util.concurrent.TimeUnit;

class Phone{
    public synchronized void sendEmail() throws Exception{
        System.out.println("*****sendEmail*******");
    }

    public synchronized void sendEMS() throws Exception{
        System.out.println("*****sendEMS******");
    }
}

/**
 * @author: ZL
 * @Date: 2020/5/7 21:26
 * @Description: java的8锁
 */
public class lockEightDemo {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(4);
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);
        new Thread(() ->{
            try {
                phone.sendEMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
