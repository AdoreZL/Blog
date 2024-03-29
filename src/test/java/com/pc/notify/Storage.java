package com.pc.notify;

import java.util.LinkedList;

/**
 * @author: ZL
 * @Date: 2020/9/1 19:42
 * @Description: 生产者消费者，，，使用wait/notify方法来实现
 */
public class Storage {
    //仓库容量
    private final int MAX_SIZE=10;
    //仓库的载体
    private LinkedList<Object> list = new LinkedList<>();

    public void produce(){
        synchronized (list){
            while (list.size()+1>MAX_SIZE){
                System.out.println("【生产者" + Thread.currentThread().getName()
                        + "】仓库已满");
                try {
                    list.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName()
                    + "】生产一个产品，现库存" + list.size());
            list.notifyAll();
        }
    }

    public void consume(){
        synchronized (list){
            while (list.size()==0){
                System.out.println("【消费者" + Thread.currentThread().getName()
                        + "】仓库已空");
                try{
                    list.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】消费一个产品，现库存" + list.size());
            list.notifyAll();
        }
    }
}
