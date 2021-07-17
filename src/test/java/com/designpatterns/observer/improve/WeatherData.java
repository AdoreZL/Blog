package com.designpatterns.observer.improve;

import java.util.ArrayList;

/**
 * @author: ZL
 * @Date: 2020/8/25 19:11
 * @Description:
 */
public class WeatherData implements Subject{
    //温度，气压，，湿度
    private float temperature;
    private float pressure;
    private float humidity;
//    private CurrentConditions currentConditions;
    private ArrayList<Observer> observers;
    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }



    public void dataChange(){
        //调用接口的update方法
//        currentConditions.update(getHumidity(),getPressure(),getTemperature());
        notifyObserver();
    }

    //当数据有更新时，，就调用setData
    public void setData(float temperature,float pressure,float humidity){
        this.humidity=humidity;
        this.pressure=pressure;
        this.temperature=temperature;
        dataChange();
    }

    //注册一个观察者
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    //销毁一个观察者
    @Override
    public void removeObserver(Observer o) {
        if(observers.contains(o)){
            observers.remove(o);
        }
    }

    //遍历所有的观察者，，并通知
    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this.temperature,this.pressure,this.humidity);
        }

    }
}
