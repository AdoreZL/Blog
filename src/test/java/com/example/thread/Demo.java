package com.example.thread;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/7 23:17
 */

public class Demo extends Thread{
    private String name;

    public Demo(String name) {
        super(name);
        this.name = name;
    }

    public void show(){
        for (int i = 0; i <100 ; i++) {
            System.out.println(".....i="+i+Thread.currentThread().getName());
        }
    }


    @Override
    public void run() {
        show();
    }
}
