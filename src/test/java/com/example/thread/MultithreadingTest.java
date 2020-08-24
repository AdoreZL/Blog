package com.example.thread;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/7 8:10
 * 写两个线程，其中一个线程打印1-52，另一个线程打印A-Z，打印顺序应该是12A34B56C......5152Z。
 *
 *
 */
public class MultithreadingTest {
    public static void main(String[] args) {
        String s = "abc";
        String x = "123";
        System.out.println(s+x);
//        printNumber printNumber = new printNumber();
//        new PrintLetterThread(printNumber).start();
//        new PrintNumberThread(printNumber).start();
    }
}


class printNumber{
    private int flag = 0;
    private int beginIndex = 1;
    private int beginLetter = 65;

    private int nCount = 0;
    private int lCount = 0;

    public int getnCount(){
        return nCount;
    }

    public int getlCount(){
        return lCount;
    }

    public synchronized void printNumber(){
        try {
            if(flag==0){
                nCount++;
                System.out.println(beginIndex);
                System.out.println(beginIndex+1);
                beginIndex+=2;
                //改标志位
                flag++;
                //唤醒另一个线程
                notify();
            }else {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printLetter(){
        try {
            if(flag==1){
                lCount++;
                char letter= (char) beginLetter;
                System.out.println(letter);
                beginLetter++;
                flag--;
                notify();
            }else {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class PrintNumberThread extends Thread{
    private printNumber pN;
    public PrintNumberThread(printNumber pN){
        this.pN=pN;
    }

    @Override
    public void run() {
        while (pN.getnCount()<26){
            pN.printNumber();
        }
    }
}


class PrintLetterThread extends Thread{
    private printNumber pN;

    public PrintLetterThread(printNumber pN){
        this.pN=pN;
    }
    @Override
    public void run() {
        while (pN.getnCount()<26){
            pN.printLetter();
        }
    }
}