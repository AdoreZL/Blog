package com.zl.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: ZL
 * @Date: 2020/4/20 17:01
 * @Description: 2.1 阻塞队列有没有好的一面
 *  2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args){
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"\t put b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName()+"\t put c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t put a");
                blockingQueue.take();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t put a");
                blockingQueue.take();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t put a");
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
