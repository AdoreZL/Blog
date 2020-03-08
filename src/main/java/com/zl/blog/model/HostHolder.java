package com.zl.blog.model;

import org.springframework.stereotype.Component;

/**
 * Created by zl on 17-7-20.
 * @author zl
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
