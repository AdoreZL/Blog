package com.zl.blog.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/15 10:08
 * @Description
 */
@Component
public class AsyncTask {
    @Async("taskExecutor")
    public void tesTask(int i){
        System.out.println(Thread.currentThread().getName()+"-----"+i);
    }

    @Async("taskExecutor")
    public  void stringTask(String str){

        System.out.println(Thread.currentThread().getName()+str);
    }


}
