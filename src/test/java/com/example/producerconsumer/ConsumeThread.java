package com.example.producerconsumer;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/6 18:17
 */
class ConsumeThread implements Runnable{
    private Consumer c;

    public ConsumeThread(Consumer c) {
        this.c = c;
    }


    @Override
    public void run() {
        while (true){
            c.consumer();
        }
    }
}
