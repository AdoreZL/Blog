package com.example.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/8 11:20
 * @Description
 */
public class MainTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        //等凉菜
        Callable ca1 = () -> {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "凉菜准备完毕";
        };
        FutureTask<String> ft1 = new FutureTask<String>(ca1);
        new Thread(ft1).start();

        //等包子
        Callable ca2 = () -> {
            try {
                Thread.sleep(1000*3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "包子准备完毕";
        };
        FutureTask<String> ft2 = new FutureTask<String>(ca2);
        new Thread(ft2).start();

        System.out.println(ft1.get());
        System.out.println(ft2.get());

        long end = System.currentTimeMillis();
        System.out.println("准备时间完成"+(end-start));
    }
}
