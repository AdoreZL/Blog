package com.zl.blockingqueue;

import io.swagger.models.auth.In;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    //默认开启，进行生产+消费
    private volatile boolean Flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data =null;
        boolean retValue;
        while (Flag){
            data=atomicInteger.incrementAndGet()+"";
            retValue=blockingQueue.offer(data,2L,TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停，表示flag等于false,生产动作结束");
    }

    public void myConsumer() throws Exception{
        String result = null;
        while (Flag){
            //拿数据
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null==result||result.equalsIgnoreCase("")){
                Flag=false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有q取出蛋糕，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列蛋糕"+result+"成功");
        }
    }

    public void stop() throws Exception{
        this.Flag=false;
    }
}

/**
 * @author: ZL
 * @Date: 2020/4/20 20:54
 * @Description: volatile/CAS/atomicInteger/BlockingQueue/线程交互
 */
public class ProdConsumer_BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProd();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"pord").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResource.myConsumer();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Consumer").start();


        //暂停一会儿线程
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("5秒钟时间到，，main线程结束");
        myResource.stop();
    }
}
