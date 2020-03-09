package com.example.thread;

import com.zl.blog.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/7 23:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
public class ThreadTest {
    @Test
    public void test(){
        Demo d1 = new Demo("磊磊");
        Demo d2 = new Demo("leilei");
        d1.start();
        d2.start();
    }


    @Test
    public void ticketTest(){
        TicketDemo ticketDemo = new TicketDemo();
//        TicketDemo ticketDemo1 = new TicketDemo();
//        TicketDemo ticketDemo2 = new Ti   cketDemo();
//        TicketDemo ticketDemo3 = new TicketDemo();
        Thread thread = new Thread(ticketDemo);
        Thread thread1 = new Thread(ticketDemo);
        Thread thread2 = new Thread(ticketDemo);
        Thread thread3 = new Thread(ticketDemo);
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
