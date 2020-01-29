package com.zl.blog.domain;

import lombok.Data;

import java.util.Date;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param:
 * @Return:
 * @Author: ZL
 * @Date: 2020/1/29 11:05
 */
@Data
public class LoginTicket {
    private int id;
    private int userId;
    private Date expired;
    private int status;
    private String ticket;
}
