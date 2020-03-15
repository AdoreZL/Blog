package com.zl.blog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by zl on 17-8-13.
 * @author zl
 */

@ApiModel("文章请求实体")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    private int id;
    @ApiModelProperty(value = "标题",dataType ="String" ,name = "title",example = "怎么学好Java")
    private String title;
    @ApiModelProperty(value = "介绍",dataType ="String" ,name = "describes",example = "如何能学好Java")
    private String describes;
    @ApiModelProperty(value = "内容",dataType ="String" ,name = "content",example = "请阅读Java编程思想")
    private String content;
    private Date createdDate;
    private int commentCount;
    @ApiModelProperty(value = "种类",dataType ="String" ,name = "category",example = "Java")
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
