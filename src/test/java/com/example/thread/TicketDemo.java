package com.example.thread;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/8 23:32
 */
public class TicketDemo implements Runnable{
    /*
    * 需求：卖票
    * */
    private int num = 100;
    Object obj = new Object();

    private void sale() {
            while (true){
                synchronized(obj){
                    if(num>0){
                        System.out.println(Thread.currentThread().getName()+"票票"+num--);
                    }
                }
            }
        }


    @Override
    public void run() {
        sale();
    }
}
