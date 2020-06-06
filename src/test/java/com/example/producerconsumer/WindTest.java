package com.example.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.zl.blog.util.Methods.*;
/**
 * @author: ZL
 * @Date: 2020/5/18 20:05
 * @Description:
 */
////资源类
//class  Cheese{
//    private volatile int number =0;
//    private Lock lock = new ReentrantLock();
//    //奶酪
//    private Condition c1 = lock.newCondition();
//    private Condition c2 = lock.newCondition();
//    //货车
//    private Condition c3 = lock.newCondition();
//    //生产奶酪
//    public void produce(){
//        lock.lock();
//        try {
//            //判断(当奶酪数量等于0或者大于1000的时候不生产)
//            while(number!=0&&number>1000){
//                c1.await();
//            }
//            //生产奶酪,当奶酪的数目为100的整数倍时候，让货车送货
//            number++;
//            System.out.println(Thread.currentThread().getName()+"\t");
//            if (number%100==0){
//                //3.通知货车送货
//                c3.signal();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
//
//    //货车拉奶酪
//    public void  consumer(){
//        lock.lock();
//        //货车拉奶酪，，一次100个奶酪酪
//        try {
//            while (number%100!=0){
//                c3.await();
//            }
//            number= number - 100;
//            System.out.println(Thread.currentThread().getName()+"\t");
//            c1.signal();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
//}

/*
 *  题目  一家生产奶酪的厂家，每天需要生产10000份奶酪卖给超市，通过一辆送货车发货，送货车辆每次送100份
 *  厂家有一个容量为1000份的冷库，用于奶酪保鲜，生产的奶酪需要先存放在冷库，运输车辆从冷库取货。
 *  厂家有三条生产线，分别是牛奶供应生产线，发酵剂制作生产线，奶酪生产线。生产每份奶酪需要两份牛奶和一份发酵剂
 * */
public class WindTest {
    public static void main(String[] args) {
        Queue queue = new Queue();
        //牛奶供应生产线
        new Thread(new Producer(queue)).start();
        //发酵剂制作生产线
        new Thread(new Producer(queue)).start();
        //奶酪生产线
        new Thread(new Producer(queue)).start();
        //消费生产线，，，生产每份奶酪需要两份牛奶和一份发酵剂
        new Thread(new Consumer(queue)).start();
    }

    static class Producer implements Runnable {

        Queue queue;

        Producer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10000; i++) {
                    doingLongTime();
                    queue.putEle(random(10000));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class Consumer implements Runnable {

        Queue queue;

        Consumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10000; i++) {
                    doingLongTime();
                    queue.takeEle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class Queue {
        Lock lock = new ReentrantLock();
        Condition prodCond  = lock.newCondition();
        Condition consCond = lock.newCondition();

        //设置队列长度为1000
        final int CAPACITY = 1000;
        Object[] container = new Object[CAPACITY];
        int count = 0;
        int putIndex = 0;
        int takeIndex = 0;

        //生产者生产
        public void putEle(Object ele) throws InterruptedException {
            try {
                lock.lock();
                while (count == CAPACITY) {
                    println("队列已满：%d，生产者开始睡大觉。。。", count);
                    prodCond.await();
                }
                container[putIndex] = ele;
                println("生产元素：%d", ele);
                putIndex++;
                if (putIndex >= CAPACITY) {
                    putIndex = 0;
                }
                count++;
                println("通知消费者去消费。。。");
                consCond.signalAll();
            } finally {
                lock.unlock();
            }
        }


        //消费者消费
        public Object takeEle() throws InterruptedException {
            try {
                lock.lock();
                while (count == 0) {
                    println("队列已空：%d，消费者开始睡大觉。。。", count);
                    consCond.await();
                }
                Object ele = container[takeIndex];
                println("消费元素：%d", ele);
                takeIndex++;
                if (takeIndex >= CAPACITY) {
                    takeIndex = 0;
                }
                count--;
                println("通知生产者去生产。。。");
                prodCond.signalAll();
                return ele;
            } finally {
                lock.unlock();
            }
        }
    }
}
