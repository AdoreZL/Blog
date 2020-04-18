package com.designpatterns.proxy;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/16 21:10
 * @Description
 */
public class UserDapProxy implements IUserDao{
    private IUserDao target;

    public UserDapProxy(IUserDao target) {
        this.target = target;
    }


    @Override
    public void save() {
        //扩展了额外功能
        System.out.println("开启事务");
        target.save();
        System.out.println("提交事务");
    }
}
