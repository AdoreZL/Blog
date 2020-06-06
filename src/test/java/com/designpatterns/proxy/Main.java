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
        public static void main(String[] args1) {
                //Java3y请水军
            Java3y java3y = new Java3y();
            Programmer programmerWaterArmy = (Programmer) Proxy.newProxyInstance(java3y.getClass().getClassLoader(), java3y.getClass().getInterfaces(), (proxy, method, args) -> {

                // 如果是调用coding方法，那么水军就要点赞了
                if (method.getName().equals("coding")) {
                    method.invoke(java3y, args);
                    System.out.println("我是水军，我来点赞了！");
                } else {
                    // 如果不是调用coding方法，那么调用原对象的方法
                    return method.invoke(java3y, args);
                }

                return null;
            });

            // 每当Java3y写完文章，水军都会点赞
            programmerWaterArmy.coding();

        }
}
