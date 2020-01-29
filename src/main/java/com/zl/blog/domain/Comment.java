package com.zl.blog.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by tuzhenyu on 17-7-23.
 * @author tuzhenyu
 */
@Data
@Component
public class Comment {
    private int id;
    private int userId;
    private int articleId;
    private String content;
    private Date createdDate;
    private int status;
}
