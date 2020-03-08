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
}
