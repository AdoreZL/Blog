package com.example.future;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/8 11:18
 * @Description
 */
public class ColdDishThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("凉菜准备完毕");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
