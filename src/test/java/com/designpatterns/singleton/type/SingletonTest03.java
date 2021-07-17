package com.designpatterns.singleton.type;

/**
 * @author: ZL
 * @Date: 2020/8/24 14:44
 * @Description:
 */
public class SingletonTest03 {
    public static void main(String[] args) {
        System.out.println("使用静态内部类完成单例模式");
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();

    }
}

class Singleton{
    private static volatile Singleton instance;

    private Singleton() {
    }

    //提供一个静态的公有方法，，当使用d到该方法时，才去创建instance,,不安全
    //即懒汉式
//    public static Singleton getInstance(){
//        if (null==instance){
//            instance = new Singleton();
//        }
//        return instance;
//    }

    //解决了线程安全问题  但是方法中加锁效率太低了
//        public static synchronized Singleton getInstance(){
//        if (null==instance){
//            instance = new Singleton();
//        }
//        return instance;
//    }


    //双重检查应用实例   解决懒加载中线程安全问题同时保证了效率
     public static Singleton getInstance(){
        if (null==instance){
            synchronized (Singleton.class){
                if (null==instance){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    //使用静态内部类
    private static class SingleTonHoler{
        private static Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance1(){
        return SingleTonHoler.INSTANCE;
    }
}
