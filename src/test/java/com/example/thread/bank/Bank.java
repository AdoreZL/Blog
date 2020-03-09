package com.example.thread.bank;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/9 17:22
 */


/*
需求:储户，两个，每个都到银行存钱每次存100，，共存三次。
*/
public class Bank{
    private int sum;

    public void add(int num){
        sum = sum+num;
        System.out.println("sum="+sum);
    }
}
