package com.zl.blog.controller;

import com.zl.blog.domain.Article;
import com.zl.blog.domain.ArticleTag;
import com.zl.blog.domain.Tag;
import com.zl.blog.service.ArticleService;
import com.zl.blog.service.JedisService;
import com.zl.blog.service.TagService;
import com.zl.blog.util.RedisKeyUntil;
import com.zl.blog.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author zl
 * @version 1.0
 * @date 2020/2/1 22:08
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private JedisService jedisService;

    @RequestMapping("/articleAdd")
    public String addArticle(@RequestParam("title")String title, @RequestParam("category")String category,
                             @RequestParam("tag")String tag, @RequestParam("describe")String describe,
                             @RequestParam("content")String content){
        Article article = new Article();
        article.setTitle(title);
        article.setDescribes(describe);
        article.setCreatedDate(new Date());
        article.setCommentCount(0);
        article.setContent(Util.tranfer(content));
        article.setCategory(category);
        int articleId = articleService.addArticle(article);
        String[] tags = tag.split(",");
        for (String t : tags){
            Tag tag1 = tagService.selectByName(t);
            if (tag1==null){
                Tag tag2 = new Tag();
                tag2.setName(t);
                tag2.setCount(1);
                int tagId = tagService.addTag(tag2);

                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(tagId);
                articleTag.setArticleId(articleId);
                tagService.addArticleTag(articleTag);
            }else {
                tagService.updateCount(tag1.getId(),tag1.getCount()+1);

                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(tag1.getId());
                articleTag.setArticleId(articleId);
                tagService.addArticleTag(articleTag);
            }
        }
        String categoryKey = RedisKeyUntil.getCategoryKey(category);
        jedisService.incr(categoryKey);

        return "redirect:/";
    }
}
