package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/6 10:53
 */
/*
*  生产者，，消费者   单个生产者，，，单个消费者
* */

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resources r = new Resources();
        Producer producer = new Producer(r);
        Consumer consumer = new Consumer(r);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        Thread t3 = new Thread(producer);
        Thread t4 = new Thread(consumer);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Producer implements Runnable{
    private Resources r;
    public Producer(Resources r) {
        this.r = r;
    }
    @Override
    public void run() {
        while(true)
        {
            r.set("烤鸭");
        }
    }
}

class Consumer implements Runnable{
    private Resources r;
    public Consumer(Resources r) {
        this.r = r;
    }
    @Override
    public void run() {
        while (true){
            r.out();
        }
    }
}

class Resources{
    private String name;
    private int count =1;
    private Boolean flag = false;

    //	创建一个锁对象。
    Lock lock = new ReentrantLock();
    //通过已有的锁获取该锁上的监视器对象。
//	Condition con = lock.newCondition();

    //通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();
    public void set(String name)
        {
            lock.lock();
            try
            {
                while(flag)
//			try{lock.wait();}catch(InterruptedException e){}//   t1    t0
                    try{producer_con.await();}catch(InterruptedException e){}//   t1    t0

                this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
                count++;//2 3 4
                System.out.println(Thread.currentThread().getName()+"...生产者5.0..."+this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
                flag = true;
//			notifyAll();
//			con.signalAll();
                consumer_con.signal();
            }
            finally
            {
                lock.unlock();
            }

        }

    public  void out()
            {
                lock.lock();
                try
                {
                    while(!flag)
//			try{this.wait();}catch(InterruptedException e){}	//t2  t3
                        try{consumer_con.await();}catch(InterruptedException e){}	//t2  t3
                    System.out.println(Thread.currentThread().getName()+"...消费者.5.0......."+this.name);//消费烤鸭1
                    flag = false;
//			notifyAll();
//			con.signalAll();
                    producer_con.signal();
                }
                finally
                {
                    lock.unlock();
                }

            }
}
