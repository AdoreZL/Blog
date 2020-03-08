package com.example.producerconsumer;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/6 18:15
 */
public class ProduceThread implements Runnable {

    private Producer p;

    public ProduceThread(Producer p) {
        this.p = p;
    }

    @Override
    public void run() {
        while(true){
            p.product();
        }
    }
}
