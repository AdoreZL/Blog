package com.designpatterns.principle.singleresponsibility;

/**
 * @author: ZL
 * @Date: 2020/8/20 16:18
 * @Description:
 */
public class SingleResponsibilityOne {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        AirVehicle airVehicle = new AirVehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        airVehicle.run("飞机");
    }
}

//交通工具类
//1.在方式1的run方法中违反了单一职责原则
//2.解决的方案非常简单，根据交通工具运行方法不同，分解成不同的类即可
class Vehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"在公路上运行。。。");
    }
}