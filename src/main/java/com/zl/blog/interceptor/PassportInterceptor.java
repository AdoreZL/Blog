package com.zl.blog.interceptor;

import com.zl.blog.dao.LoginTicketDao;
import com.zl.blog.dao.UserDao;
import com.zl.blog.domain.HostHolder;
import com.zl.blog.domain.LoginTicket;
import com.zl.blog.domain.User;
import com.zl.blog.service.JedisService;
import com.zl.blog.util.RedisKeyUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author zl
 * @version 1.0
 * @date 2020/1/29 22:06
 */
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginTicketDao loginTicketDao;

    @Autowired
    private JedisService jedisService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;
        if(request.getCookies()!=null){
            for (Cookie cookie:request.getCookies()) {
                 if("ticket".equals(cookie.getName())){
                     ticket = cookie.getValue();
                     break;
                 }
            }
        }
        if(null!=ticket){
            LoginTicket loginTicket = loginTicketDao.seletByTicket(ticket);
            if(null!=loginTicket ||loginTicket.getExpired().before(new Date())||loginTicket.getStatus()!=0){
                return true;
            }
            User user = userDao.seletById(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        String uri = request.getServletPath();
        String uriKey = RedisKeyUntil.getClickCountKey(uri);
        String sumKey = RedisKeyUntil.getClickCountKey("SUM");
        jedisService.incr(uriKey);
        jedisService.incr(sumKey);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView!=null){
            modelAndView.addObject("user",hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
