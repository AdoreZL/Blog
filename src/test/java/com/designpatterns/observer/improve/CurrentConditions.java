package com.designpatterns.observer.improve;

/**
 * @author: ZL
 * @Date: 2020/8/25 19:02
 * @Description:
 */
public class CurrentConditions implements Observer{
    //温度，气压，，湿度
    private float temperature;
    private float pressure;
    private float humidity;

    //更新 天气情况，，有WeatherData 来调用，，推送模式
    public void update(float temperature,float pressure,float humidity){
            this.humidity=humidity;
            this.temperature=temperature;
            this.pressure=pressure;
            display();
    }

    //显示
    public void display(){
        System.out.println("****Today mTemperature:"+temperature+"***");
        System.out.println("****Today Pressure:"+pressure+"***");
        System.out.println("****Today Humidity:"+humidity+"***");
    }
}
