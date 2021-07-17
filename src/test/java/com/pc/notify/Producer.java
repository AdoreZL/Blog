package com.pc.notify;

/**
 * @author: ZL
 * @Date: 2020/9/2 14:14
 * @Description:
 */
public class Producer implements Runnable {
    private Storage storage;

    public Producer(){}

    public Producer(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
                storage.produce();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
