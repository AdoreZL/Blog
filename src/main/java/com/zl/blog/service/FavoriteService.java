package com.zl.blog.service;

import com.zl.blog.util.RedisKeyUntil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zl
 * @version 1.0
 * @date 2020/2/2 22:36
 */
public class FavoriteService {
    @Autowired
    private JedisService jedisService;

    public int getFavoriteStatus(int userId,int articleId){
        String favoriteKey = RedisKeyUntil.getLikeKey(articleId);
        if (jedisService.sismember(favoriteKey,String.valueOf(userId))){
            return 1;
        }
        String disLikeKey = RedisKeyUntil.getDisLikeKey(articleId);
        return jedisService.sismember(disLikeKey,String.valueOf(userId))?-1:0;
    }

    public long favorite(int userId,int articleId){
        String disLikeKey = RedisKeyUntil.getDisLikeKey(articleId);
        jedisService.sadd(disLikeKey,String.valueOf(userId));

        String likeKey = RedisKeyUntil.getLikeKey(articleId);
        jedisService.srem(likeKey,String.valueOf(userId));

        return jedisService.scard(likeKey);
    }

    public long disfavorite(int userId,int articleId){
        String disLikeKey = RedisKeyUntil.getDisLikeKey(articleId);
        jedisService.sadd(disLikeKey,String.valueOf(userId));

        String likeKey = RedisKeyUntil.getLikeKey(articleId);
        jedisService.srem(likeKey,String.valueOf(userId));

        return jedisService.scard(likeKey);
    }

    public long getLikeCount(int articleId){
        String likeKey = RedisKeyUntil.getLikeKey(articleId);
        return jedisService.scard(likeKey);
    }

    public long getDislikeCount(int articleId){
        String dislikeKey = RedisKeyUntil.getDisLikeKey(articleId);
        return jedisService.scard(dislikeKey);
    }
}
