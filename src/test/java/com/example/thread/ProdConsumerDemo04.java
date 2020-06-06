//package com.example.thread;
//
//
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//
///*
//* 生产者，，，消费者
//* */
//class Aircondition{
//    private int number = 0;
//    private Lock lock =new ReentrantLock();
//    private Condition condition =lock.newCondition();
//
//    public synchronized void increment() throws Exception{
//        //判断
//        while(number!=0){
//            this.wait();
//        }
//        //干活
//        number++;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //通知
//        this.notifyAll();
//    }
//
//    public synchronized void decrement() throws Exception{
//        //判断
//        while(number==0){
//            x   .wait();
//        }
//        //干活
//        number--;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //通知
//        this.notifyAll();
//    }
//
//
////    public synchronized void increment() throws Exception{
////        lock.lock();
////        try {
////            //判断
////            while(number!=0){
////                condition.await();
////            }
////            //干活
////            number++;
////            System.out.println(Thread.currentThread().getName()+"\t"+number);
////            //通知
//////            this.notifyAll();
////                condition.signalAll();
////        }catch (Exception e){
////            e.printStackTrace();
////        }finally {
////            lock.unlock();
////        }
////    }
////
////    public synchronized void decrement() throws Exception{
////        lock.lock();
////        try {
////            //判断
////            while(number==0){
//////                this.wait();
////                condition.await();
////            }
////            //干活
////            number--;
////            System.out.println(Thread.currentThread().getName()+"\t"+number);
////            //通知
//////            this.notifyAll();
////            condition.signalAll();
////        }catch (Exception e){
////            e.printStackTrace();
////        }finally {
////            lock.unlock();
////        }
////    }
//}
//
///**
// * @author: ZL
// * @Date: 2020/5/9 11:15
// * @Description:
// * 题目：现在两个线程，可以操作初始值为0的一个变量
// * 实现一个线程对该变量+1，一个线程对该变量-1
// * 实现交替，，来10轮，变量初始值为0
// *
// * 1.高聚低合前提下，，线程操作资源类
// * 2.判断/干活/通知
// * 3.防止虚假唤醒
// */
//public class ProdConsumerDemo04 {
//    public static void main(String[] args) {
//        Aircondition aircondition = new Aircondition();
//
//        new Thread(() ->{
//            for (int i = 0; i <10; i++) {
//                try {
//                    aircondition.increment();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"A").start();
//        new Thread(() ->{
//            for (int i = 0; i <10; i++) {
//                try {
//                    aircondition.decrement();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"B").start();
//        new Thread(() ->{
//            for (int i = 0; i <10; i++) {
//                try {
//                    aircondition.increment();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"C").start();
//        new Thread(() ->{
//            for (int i = 0; i <10; i++) {
//                try {
//                    aircondition.decrement();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"D").start();
//    }
//}
