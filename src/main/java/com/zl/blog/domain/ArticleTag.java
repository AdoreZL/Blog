package com.zl.blog.domain;

import lombok.Data;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param:
 * @Return:
 * @Author: ZL
 * @Date: 2020/1/29 11:07
 */
@Data
public class ArticleTag {
    private int id;
    private int articleId;
    private int tagId;
}
