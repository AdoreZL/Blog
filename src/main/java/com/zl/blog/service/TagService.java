package com.zl.blog.service;

import com.zl.blog.dao.ArticleTagDao;
import com.zl.blog.dao.TagDao;
import com.zl.blog.domain.ArticleTag;
import com.zl.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zl
 * @version 1.0
 * @date 2020/2/1 22:23
 */
@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    public Tag selectByName(String name){
        return tagDao.selectByName(name);
    }

    public List<Tag> getAllTag(){
        return tagDao.selectAll();
    }

    public List<Tag> getTagByArticleId(int articleId){
        return articleTagDao.selectByArticleId(articleId);
    }

    public int addTag(Tag tag){
        return tagDao.insertTag(tag)>0?tag.getId():0;
    }

    public int addArticleTag(ArticleTag articleTag){
        return articleTagDao.insertArticleTag(articleTag);
    }

    public void updateCount(int tagId,int count){
        tagDao.updateCount(tagId,count);
    }
}

