package com.example.thread.bank;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/9 17:27
 */
public class Customer implements Runnable{
    private Bank b = new Bank();
    @Override
    public void run() {
        for (int i = 0; i <3; i++) {
            b.add(100);
        }
    }
}
