package com.zl.blog.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zl
 * @version 1.0
 * @date 2020/1/29 15:25
 */
@Slf4j
public class Util {
    public static Map<String,String> categoryMap;
    static {
        categoryMap = new HashMap<>();
        categoryMap.put("Java","Java");
        categoryMap.put("Web","Web");
        categoryMap.put("Linux","Linux");
        categoryMap.put("分布式系统","Distributed");
        categoryMap.put("数据库","Database");
        categoryMap.put("算法","Algorithm");
        categoryMap.put("其它","Other");
    }
    public static String[] categorys = {"Java","Web","Linux","分布式系统","数据库","算法","其它"};
    public static int ANONYMOUS_USER_ID = 3;

    public static String getJSONString(int code,String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code){
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }

    public static String MD5(String key){
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("生成MD5失败", e);
            return null;
        }
    }
}
