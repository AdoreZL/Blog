package com.designpatterns.observer.improve;

/**
 * @author: ZL
 * @Date: 2020/8/25 16:54
 * @Description:
 */
//接口，，让WeatherData来表现
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver( );
}
