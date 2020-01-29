package com.zl.blog.domain;

import lombok.Data;

import java.util.Date;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param:
 * @Return:
 * @Author: ZL
 * @Date: 2020/1/29 11:07
 */
@Data
public class Article {
    private int id;
    private String title;
    private String describes;
    private String content;
    private Date createdDate;
    private int commentCount;
    private String category;
}
