package com.example.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ZL
 * @Date: 2020/5/18 20:05
 * @Description:
 */
/*
 *  题目  一家生产奶酪的厂家，每天需要生产100000份奶酪卖给超市，通过一辆送货车发货，送货车辆每次送100份
 *
 *  厂家有一个容量为1000份的冷库，用于奶酪保鲜，生产的奶酪需要先存放在冷库，运输车辆从冷库取货。
 *
 *  厂家有三条生产线，分别是牛奶供应生产线，发酵剂制作生产线，奶酪生产线。生产每份奶酪需要两份牛奶和一份发酵剂
 * */


//资源类
class  Cheese{
    private volatile int number =0;
    private Lock lock = new ReentrantLock();
    //奶酪
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    //货车
    private Condition c3 = lock.newCondition();
    //生产奶酪
    public void produce(){
        lock.lock();
        try {
            //判断(当奶酪数量等于0或者大于1000的时候不生产)
            while(number!=0&&number>1000){
                c1.await();
            }
            //生产奶酪,当奶酪的数目为100的整数倍时候，让货车送货
            number++;
            System.out.println(Thread.currentThread().getName()+"\t");
            if (number%100==0){
                //3.通知货车送货
                c3.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //货车拉奶酪
    public void  consumer(){
        lock.lock();
        //货车拉奶酪，，一次100个奶酪酪
        try {
            while (number%100!=0){
                c3.await();
            }
            number= number - 100;
            System.out.println(Thread.currentThread().getName()+"\t");
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class WindTest {
    public static void main(String[] args) {
        Cheese cheese = new Cheese();
        new Thread(() ->{
            for (int i = 0; i < 10000; i++) {
                cheese.produce();
            }
        },"A").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                cheese.consumer();
            }
        },"B").start();
    }
}
