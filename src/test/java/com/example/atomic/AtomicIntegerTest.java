package com.example.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ZL
 * @Date: 2020/6/9 18:32
 * @Description:
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int temvalue = 0;
        AtomicInteger i = new AtomicInteger(0);
        //getAndSet 获取当前得值并且设置新的值
        temvalue = i.getAndSet(3);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:0;  i:3
        temvalue = i.getAndIncrement();
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:3;  i:4
        temvalue = i.getAndAdd(5);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:4;  i:9
    }
}

class Test{
    private volatile int count = 0;
    //若要线程安全执行执行count++，需要加锁
    public synchronized void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}

class Test2{
    private AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }
    //使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
    public int getCount() {
        return count.get();
    }
}
