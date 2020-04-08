package com.example.future;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/8 11:17
 * @Description
 */
public class BumThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000*3);
            System.out.println("包子准备完毕");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
