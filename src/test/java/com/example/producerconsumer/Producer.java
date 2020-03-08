package com.example.producerconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/6 11:47
 */

/*
*  题目  一家生产奶酪的厂家，每天需要生产100000份奶酪卖给超市，通过一辆送货车发货，送货车辆每次送100份
*
*  厂家有一个容量为1000份的冷库，用于奶酪保鲜，生产的奶酪需要先存放在冷库，运输车辆从冷库取货。
*
*  厂家有三条生产线，分别是牛奶供应生产线，发酵剂制作生产线，奶酪生产线。生产每份奶酪需要两份牛奶和一份发酵剂
* */
@Slf4j
public class Producer {
    /*
    * 用list存放生产之后的数据，，，最大容量为1.
    * */
    private ArrayList<Integer> list;
    private   Object object;

    public Producer(Object object,ArrayList<Integer> list ){
        this.object = object;
        this.list = list;
    }

    public void product(){
        synchronized (object){
          try{
              //只有list为空时才进行生产操作
              while (!list.isEmpty()){
                log.debug("生产者"+Thread.currentThread().getName()+"waiting");
                  object.wait();
              }
              int value =9999;
              list.add(value);
              log.debug("生产者"+Thread.currentThread().getName()+" Runnable");
              object.notifyAll();
          }catch (InterruptedException e){
              e.printStackTrace();
          }
        }
    }


    public static void main(String[] args) {
        Object object = new Object();
        ArrayList<Integer> list = new ArrayList<Integer>();

        Producer p = new Producer(object, list);
        Consumer c = new Consumer(object, list);

        ProduceThread[] pt = new ProduceThread[2];
        ConsumeThread[] ct = new ConsumeThread[2];

//        for(int i=0;i<2;i++){
//            pt[i] = new ProduceThread(p);
//            pt[i].setName("生产者 "+(i+1));
//            ct[i] = new ConsumeThread(c);
//            ct[i].setName("消费者"+(i+1));
//            pt[i].
//            ct[i].start();
//        }


    }
}
