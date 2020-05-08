package com.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ZL
 * @Date: 2020/5/5 14:29
 * @Description:
 */
class Tickets{   // 资源类=示例变量+实例方法
    private int number = 30;

    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t 还剩下："+number );
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
* @Description:    三个售票员   卖出   30张票
 *                 线程  操作  资源类
 *                 1.1先创建一个资源类
* @Author:         zl
* @CreateDate:     2020/5/5 14:29
* @UpdateUser:     zl
* @Version:        1.0
*/
public class SaleTicketDemo1 {

    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <40; i++) {
                    tickets.sale();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <40; i++) {
                    tickets.sale();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <40; i++) {
                    tickets.sale();
                }
            }
        },"C").start();
    }
}
