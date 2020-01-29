package com.zl.blog.domain;

import lombok.Data;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param:
 * @Return:
 * @Author: ZL
 * @Date: 2020/1/29 11:08
 */
@Data
public class Archive {
    private int id;
    private int articleId;
    private String articleTitle;
    private int year;
    private int month;
}
