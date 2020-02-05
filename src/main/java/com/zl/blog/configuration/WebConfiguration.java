package com.zl.blog.configuration;

import com.zl.blog.interceptor.LoginRequestInterceptor;
import com.zl.blog.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zl
 * @version 1.0
 * @date 2020/1/30 10:35
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private PassportInterceptor passportInterceptor;

    @Autowired
    private LoginRequestInterceptor loginRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequestInterceptor);
    }
}
