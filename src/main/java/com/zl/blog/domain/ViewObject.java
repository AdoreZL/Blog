package com.zl.blog.domain;


import java.util.HashMap;
import java.util.Map;

/*
 * 功能描述: <br>
 * 〈〉
 * @Param:
 * @Return:
 * @Author: ZL
 * @Date: 2020/1/29 11:04
 */
public class ViewObject {
    private Map<String,Object> objects = new HashMap<>();

    public void set(String key, Object value){
        objects.put(key,value);
    }

    public Object get(String key){
        return objects.get(key);
    }
}
