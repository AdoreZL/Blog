package com.designpatterns.proxy;

import org.junit.Test;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/16 21:14
 * @Description
 */
public class TestUserProxy {
    @Test
    public void testUserProxy(){
        //目标对象
        UserDao userDao = new UserDao();
        //代理对象
        UserDapProxy userDapProxy = new UserDapProxy(userDao);
        userDapProxy.save();
    }
}
