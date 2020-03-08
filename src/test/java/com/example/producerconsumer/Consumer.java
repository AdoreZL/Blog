package com.example.producerconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/6 14:34
 */
@Slf4j
public class Consumer {
    //用list存放生产之后的数据，最大容量为1
    public ArrayList<Integer> list;
    public Object object;


    public Consumer(Object object,ArrayList<Integer> list ){
        this.object = object;
        this.list = list;
    }

    public  void consumer(){
        synchronized (object){
            try{
                /*
                * 只有list不为空时才会进行消费操作
                * */
                while (list.isEmpty()){
                    log.debug("消费者"+Thread.currentThread().getName()+" waiting");
                    object.wait();
                }
                    list.clear();
                    log.debug("消费者"+Thread.currentThread().getName()+" Runnable");
                    object.notifyAll();
            }catch (InterruptedException e){

            }
        }
    }
}
