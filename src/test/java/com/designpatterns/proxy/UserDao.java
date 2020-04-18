package com.designpatterns.proxy;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/16 21:09
 * @Description
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
