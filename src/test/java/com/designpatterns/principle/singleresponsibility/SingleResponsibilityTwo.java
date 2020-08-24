package com.designpatterns.principle.singleresponsibility;

/**
 * @author: ZL
 * @Date: 2020/8/20 16:27
 * @Description:
 */
public class SingleResponsibilityTwo {
    public static void main(String[] args) {

    }
}

//方案2的分析
//1.遵守单一职责原则
//2.改进：直接修改Vehicle,改动的代码会比较少
//3.
class RoadVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"公路运行");
    }
}

class AirVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"天空运行");
    }
}

class WaterVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"水中运行");
    }
}
