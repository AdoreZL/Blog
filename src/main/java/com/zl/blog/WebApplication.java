package com.zl.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by tuzhenyu on 17-8-13.
 * @author tuzhenyu
 */
@EnableAsync
@SpringBootApplication
public class WebApplication {
//     Tomcat需要主类有一个无参构造器
//    public WebApplication() {
//    }
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
