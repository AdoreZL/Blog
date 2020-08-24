package com.designpatterns.proxy;

import java.lang.reflect.Proxy;

/**
 * @author: ZL
 * @Date: 2020/5/27 16:34
 * @Description:
 */
public class Main {
//    public static void main(String[] args) {
//        // 受委托程序员大V
//        Programmer programmer = new ProgrammerBigV();
//
//        // 受委托程序员大V让Java3y发文章，大V(自己)来点赞
//        programmer.coding();
//
//
//    }

    public static void main(String[] args) {
        double a = 5000.44;
        double b = 100.12;

        double v = a / b;
        System.out.println(v);
        int i = new Double(b).intValue();
        System.out.println(i);

    }
}

