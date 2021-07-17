package com.designpatterns.singleton.type;

/**
 * @author: ZL
 * @Date: 2020/8/24 15:56
 * @Description:
 */
public class SingletonTest04 {

}

//枚举实现单例模式
enum Singleton3{
    INSTANCE;
    public void sayOK(){
        System.out.println("OK");
    }
}