package com.zl.blog.domain;

import org.springframework.stereotype.Component;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param: 
 * @Return: 
 * @Author: ZL
 * @Date: 2020/1/29 11:13
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<>();

    public User getUser(){
        return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
