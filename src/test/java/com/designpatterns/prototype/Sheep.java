package com.designpatterns.prototype;

import lombok.Data;

/**
 * @author: ZL
 * @Date: 2020/8/25 10:17
 * @Description:
 */
@Data
public class Sheep implements Cloneable {
    private String name;
    private int age;
    private String color;
    private String address ="蒙古羊";
    private Sheep friend;   //是对象，克隆时候如何处理。

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    //克隆该实例，，使用默认的克隆方法完成
    @Override
    protected Object clone(){
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//        return super.clone();
        return sheep;
    }
}
