package com.zl.blog.aync;

import com.zl.blog.service.JedisService;
import com.zl.blog.util.RedisKeyUntil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zl on 17-7-26.
 * @author zl
 */
@Service
public class EventProducer {
    @Autowired
    private JedisService jedisService;

    public void fireEvent(EventModel eventModel){
        String json = JSONObject.toJSONString(eventModel);
        String key = RedisKeyUntil.getEventQueue();
        jedisService.lpush(key,json);
    }
}
