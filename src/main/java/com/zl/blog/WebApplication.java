package com.zl.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by tuzhenyu on 17-8-13.
 * @author tuzhenyu
 */
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    // Tomcat需要主类有一个无参构造器
    public WebApplication() {
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
