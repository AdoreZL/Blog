package com.zl.blog.interceptor;

import com.zl.blog.dao.LoginTicketDao;
import com.zl.blog.dao.UserDao;
import com.zl.blog.model.HostHolder;
import com.zl.blog.model.LoginTicket;
import com.zl.blog.model.User;
import com.zl.blog.service.JedisService;
import com.zl.blog.util.RedisKeyUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by zl on 17-7-20.
 * @author zl
 */
@Component
public class PassportInterceptor implements HandlerInterceptor{
    @Autowired
    private LoginTicketDao loginTicketDao;

    @Autowired
    private JedisService jedisService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        if (httpServletRequest.getCookies()!=null){
            for (Cookie cookie : httpServletRequest.getCookies()){
                if ("ticket".equals(cookie.getName())){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket!=null){
            LoginTicket loginTicket = loginTicketDao.seletByTicket(ticket);
            if (loginTicket==null||loginTicket.getExpired().before(new Date())||loginTicket.getStatus()!=0){
                return true;
            }
            User user = userDao.seletById(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        String uri = httpServletRequest.getServletPath();
        String uriKey = RedisKeyUntil.getClickCountKey(uri);
        String sumKey = RedisKeyUntil.getClickCountKey("SUM");
        jedisService.incr(uriKey);
        jedisService.incr(sumKey);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView!=null){
            modelAndView.addObject("user",hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
