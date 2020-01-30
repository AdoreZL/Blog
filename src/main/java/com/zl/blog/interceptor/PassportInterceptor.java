package com.zl.blog.interceptor;

import com.zl.blog.dao.LoginTicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author zl
 * @version 1.0
 * @date 2020/1/29 22:06
 */
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginTicketDao loginTicketDao;

}
