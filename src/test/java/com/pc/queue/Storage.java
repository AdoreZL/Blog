package com.pc.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: ZL
 * @Date: 2020/9/2 15:00
 * @Description:
 */
public class Storage {

    // 仓库存储的载体  阻塞队列，，默认不传大小为Integer.MAX_VALUE
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(10);

    public void produce(){
        try {
            list.put(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName()
                    + "】生产一个产品，现库存" + list.size());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        }

    public void consume(){
        try {
            list.take();
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】消费了一个产品，现库存" + list.size());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    }
