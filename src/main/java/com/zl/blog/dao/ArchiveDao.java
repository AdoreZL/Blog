package com.zl.blog.dao;

import com.zl.blog.domain.Archive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param:
 * @Return: 
 * @Author: ZL
 * @Date: 2020/1/29 15:25
 */
@Mapper
public interface ArchiveDao {

    @Select("SELECT article.id AS article_id,article.title AS article_title, YEAR(article.created_date) AS year,MONTH(article.created_date) AS month " +
            "FROM article GROUP BY YEAR(article.created_date), MONTH(article.created_date),article.id,article.title order by article_id desc")
    public List<Archive> seletArticleGroupByTime();

}
