package com.zl.blog.service;

import com.zl.blog.dao.ArticleTagDao;
import com.zl.blog.dao.TagDao;
import com.zl.blog.model.ArticleTag;
import com.zl.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tuzhenyu on 17-8-14.
 * @author tuzhenyu
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
