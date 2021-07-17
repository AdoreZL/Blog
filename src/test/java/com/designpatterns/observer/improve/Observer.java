package com.designpatterns.observer.improve;

/**
 * @author: ZL
 * @Date: 2020/8/25 16:57
 * @Description:
 */
//观察者模式，，有观察者来实现
public interface Observer {
    public void update(float temperature,float pressure,float humidity);
}
