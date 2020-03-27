package com.example.designpattern;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/25 23:00
 */
public class Single {

    /*
    * 双重校验锁
    * */
    private volatile static Single uniqueInstance;

    public static Single getUniqueInstance(){
        if(uniqueInstance==null){
            synchronized (Single.class){
                if(uniqueInstance ==null){
                    uniqueInstance = new Single();
                }
            }
        }
        return uniqueInstance;
    }


    /*
    * 懒汉式
    * */
    static class  Singles{
        private static Singles uniqueInstance;

        public static Singles getUniqueInstance(){
            if (uniqueInstance == null){
                uniqueInstance = new Singles();
            }
            return uniqueInstance;
        }
    }

    /*
    * 饿汉式
    * */
    private static Singles uniqueInstance1 = new Singles();
}
