package com.designpatterns.observer.improve;

/**
 * @author: ZL
 * @Date: 2020/8/25 19:28
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        //创建观察者
        CurrentConditions currentConditions = new CurrentConditions();

        //注册到weatherData
        weatherData.registerObserver(currentConditions);

        //测试
        System.out.println("通知各个注册的观察者");
        weatherData.setData(19f,100f,30f);

    }
}
