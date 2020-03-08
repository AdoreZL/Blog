package com.zl.blog.aync;

/**
 * Created by zl on 17-7-26.
 * @author zl
 */
public enum EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2);

    private int value;
    EventType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
