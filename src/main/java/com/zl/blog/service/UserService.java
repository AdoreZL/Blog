package com.zl.blog.service;

import com.zl.blog.dao.LoginTicketDao;
import com.zl.blog.dao.UserDao;
import com.zl.blog.domain.LoginTicket;
import com.zl.blog.domain.User;
import com.zl.blog.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author zl
 * @version 1.0
 * @date 2020/1/29 11:20
 */
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginTicketDao loginTicketDao;

    public User getUser(int userId){
        return userDao.seletById(userId);
    }

    public User getUserByName(String name){
        return userDao.seletByName(name);
    }

    public void addUser(User user){
        userDao.insertUser(user);
    }

    public Map<String,String> register(String username,String password){
        Map<String,String> map= new HashMap<>();
        Random random = new Random();
        if (StringUtils.isBlank(username)){
            map.put("msg","用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }

        User u = userDao.seletByName(username);
        if (u!=null){
            map.put("msg","用户名已经被占用");
            return map;
        }
        User user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHeadUrl(String.format("https://images.nowcoder.com/head/%dm.png",random.nextInt(1000)));
        user.setPassword(Util.MD5(password+user.getSalt()));
        user.setRole("user");
        userDao.insertUser(user);
        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);

        return map;
    }


    public String addLoginTicket(int userId){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime()+1000*3600*30);
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));

        loginTicketDao.insertLoginTicket(loginTicket);

        return loginTicket.getTicket();
    }
}
