package com.zl.blog.controller;

import com.zl.blog.domain.*;
import com.zl.blog.service.ArticleService;
import com.zl.blog.service.JedisService;
import com.zl.blog.service.TagService;
import com.zl.blog.service.UserService;
import com.zl.blog.util.RedisKeyUntil;
import com.zl.blog.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author zl
 * @version 1.0
 * @date 2020/1/29 21:34
 */
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private JedisService jedisService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping("/register")
    public String register(Model model, HttpServletResponse httpResponse,
                           @RequestParam String username, @RequestParam String password
            ,@RequestParam(value = "next",required = false)String next){
        Map<String,String> map = userService.register(username,password);
        if(map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket",map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);
            if(StringUtils.isNotBlank(next)){
                return "redirect:"+next;
            }
            return "redirect:/";
        }else {
            model.addAttribute("msg", map.get("msg"));
            return "login";
        }
    }

    @RequestMapping("/login")
    public String login(Model model, HttpServletResponse httpResponse, @RequestParam String username,@RequestParam String password,@RequestParam(value = "next",required = false)String next){
        Map<String,String> map = userService.login(username,password);
        if(map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);
            if(StringUtils.isNotBlank(next)){
                return "redirect:"+next;
            }
            return "redirect:/";
        }else {
            model.addAttribute("msg", map.get("msg"));
            return "login";
        }
    }

    @RequestMapping(path = {"/","/index"})
    public String index(Model model){
        List<ViewObject> vos = new ArrayList<>();
        List<Article> articles = articleService.getLatestArticles(0, 4);
        for (Article article:articles
             ) {
            ViewObject vo = new ViewObject();
            List<Tag> tags = tagService.getTagByArticleId(article.getId());
            String clickCount = jedisService.get(RedisKeyUntil.getClickCountKey("/article/" + article.getId()));
            if(null==clickCount){
                clickCount="0";
            }
            vo.set("clickCount",clickCount);
            vo.set("article",article);
            vo.set("tags",tags);
            vos.add(vo);
        }
        model.addAttribute("vos",vos);

        List<Tag> allTag = tagService.getAllTag();
        model.addAttribute("tags",allTag);

        ViewObject pagination = new ViewObject();
        int articleCount = articleService.getArticleCount();
        User user = hostHolder.getUser();
        if(null==user||"admin".equals(user.getRole())){
            model.addAttribute("create",1);
        }else {
            model.addAttribute("create",0);
        }
        pagination.set("current",1);
        pagination.set("nextPage",2);
        pagination.set("lastPage",articleCount/4+1);
        model.addAttribute("pagination",pagination);

        ViewObject categoryCount = new ViewObject();
        for (String category: Util.categorys
             ) {
            String categoryKey = RedisKeyUntil.getCategoryKey(category);
            String num = jedisService.get(categoryKey);
            if (num!=null) {
                categoryCount.set(Util.categoryMap.get(category),num);
            } else {
                categoryCount.set(Util.categoryMap.get(category),0);
            }
        }
        model.addAttribute("categoryCount",categoryCount);

        ViewObject clickCount = new ViewObject();
        String countStr1 = jedisService.get(RedisKeyUntil.getClickCountKey("/"));
        String countStr2 = jedisService.get(RedisKeyUntil.getClickCountKey("/index"));
        String countStr3 = jedisService.get(RedisKeyUntil.getClickCountKey("/page/1"));
        String currentPage = String.valueOf(Integer.parseInt(countStr1==null?"0":countStr1)
                + Integer.parseInt(countStr2==null?"0":countStr2)+ Integer.parseInt(countStr3==null?"0":countStr3));
        String sumPage = jedisService.get(RedisKeyUntil.getClickCountKey("SUM"));
        clickCount.set("currentPage",currentPage);
        clickCount.set("sumPage",sumPage);
        model.addAttribute("clickCount",clickCount);

        List<Article> hotArticles = new ArrayList<>();
        Set<String> set = jedisService.zrevrange("hotArticles",0,6);
        for (String str : set){
            int articleId = Integer.parseInt(str.split(":")[1]);
            Article article = articleService.getArticleById(articleId);
            hotArticles.add(article);
        }
        model.addAttribute("hotArticles",hotArticles);

        return "index";
        }

    @RequestMapping("/articleAdd")
    public String addArticle(@RequestParam("title")String title,@RequestParam("category")String category,
                             @RequestParam("tag")String tag,@RequestParam("describe")String describe,
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

    @RequestMapping("/article/{articleId}")
    public String singleArticle(Model model, @PathVariable("articleId")int articleId){
        Article article = articleService.getArticleById(articleId);
        List<Tag> tags = tagService.getTagByArticleId(article.getId());
        model.addAttribute("article",article);
        model.addAttribute("tags",tags);

        ViewObject clickCount = new ViewObject();
        String currentPage = jedisService.get(RedisKeyUntil.getClickCountKey("/article/"+articleId));
        String sumPage = jedisService.get(RedisKeyUntil.getClickCountKey("SUM"));
        clickCount.set("currentPage",currentPage);
        clickCount.set("sumPage",sumPage);
        model.addAttribute("clickCount",clickCount);

        if (hostHolder.getUser()==null)
            model.addAttribute("liked",0);
        else
            model.addAttribute("liked",likeService.getLikeStatus(hostHolder.getUser().getId(),articleId));
        model.addAttribute("likeCount",likeService.getLikeCount(articleId));
        model.addAttribute("dislikeCount",likeService.getDislikeCount(articleId));

        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        List<ViewObject> vos = new ArrayList<>();
        for (Comment comment: comments){
            ViewObject vo = new ViewObject();
            vo.set("comment",comment);
            vo.set("user",userService.getUser(comment.getUserId()));
            vos.add(vo);
        }
        model.addAttribute("vos",vos);
        model.addAttribute("commentsCount",comments.size());

        String articleClickCount = jedisService.get(RedisKeyUntil.getClickCountKey("/article/"+article.getId()));
        if (articleClickCount==null)
            articleClickCount = "0";
        model.addAttribute("articleClickCount",articleClickCount);

        return "article";
    }
}
