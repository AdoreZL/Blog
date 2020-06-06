package com.example.thread;

import com.zl.blog.WebApplication;
import com.zl.threadpool.service.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/7 23:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
@Slf4j
public class ThreadTest {
//    @Autowired
//    private AsyncTask asyncTask;
//
//    @Test
//    public void AsyncTaskTest() throws InterruptedException {
//        for (int i = 0; i < 100; i++) {
//            asyncTask.doTask(i);
//        }
//        log.info("All tasks finished...");
//    }



    @Test
    public void test(){
//        Demo d1 = new Demo("磊磊");
//        Demo d2 = new Demo("leilei");
//        d1.start();
//        d2.start();
        int number = 102;
        System.out.println(number%100);
    }


    /*
    * 同步中必须多个线程并且使用同一把锁
    * */

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
