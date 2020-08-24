package com.designpatterns.principle.singleresponsibility;

/**
 * @author: ZL
 * @Date: 2020/8/20 16:42
 * @Description:
 */
public class SingleResponsibilityThree {
    public static void main(String[] args) {
        VehicleSign vehicleSign = new VehicleSign();
        vehicleSign.runAir("飞机");
        vehicleSign.runWater("轮船");
        vehicleSign.run("汽车");
    }
}

//方式三的分析
//1.这种修改方法没有对原来的类做大的修改，只是增加方法
//2.这里虽然没有在类这个级别上遵守单一职责原则，仍然遵守单一职责
class  VehicleSign{
    public void run(String vehicle){
        //处理
        System.out.println(vehicle+"在公路上运行");
    }

    public void runAir(String vehicle){
        System.out.println(vehicle+"在空中运行");
    }

    public void runWater(String vehicle){
        System.out.println(vehicle+"在水中运行");
    }
}
