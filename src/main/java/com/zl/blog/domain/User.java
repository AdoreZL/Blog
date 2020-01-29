package com.zl.blog.domain;

import lombok.Data;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param:
 * @Return:
 * @Author: ZL
 * @Date: 2020/1/29 11:04
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String salt;
    private String headUrl;
    private String role;

    public User(){}

    public User(String name){
        this.name = name;
        this.password = "";
        this.salt = "";
        this.headUrl = "";
        this.role = "user";
    }
}
