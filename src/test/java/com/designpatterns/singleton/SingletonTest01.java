package com.designpatterns.singleton;

/**
 * @author: ZL
 * @Date: 2020/8/24 14:16
 * @Description:
 */
public class SingletonTest01 {
    public static void main(String[] args) {

    }
}

//饿汉式（静态变量）
class Singleton{

    //1.构造器私有，外部不能new
    private Singleton() {
    }

    //2.本类内部创建对象实例
    private final static Singleton instance = new Singleton();

    //3.提供一个公有的静态方法，返回实例
    public static Singleton getInstance(){
        return instance;
    }
}
