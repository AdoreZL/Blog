package com.example.thread.bank;

import com.zl.blog.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/9 17:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
public class BankTest{
/*
需求:储户，两个，每个都到银行存钱每次存100，，共存三次。
*/
    @Test
    public  void bank(){
        Customer c = new Customer();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }

    @Test
    public void SString(){
        String s1 = "abc";
        String s2 = "abc";
        if(s1.equals(s2)){
            System.out.println("可以使用equals来比较");
        }else {
            System.out.println("不可以使用equals来比较");
        }
        if(s1==s2){
            System.out.println("可以使用==来比较");
        }else {
            System.out.println("不可以使用==来比较");
        }
    }
}
