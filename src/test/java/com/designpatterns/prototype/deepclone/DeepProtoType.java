package com.designpatterns.prototype.deepclone;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author: ZL
 * @Date: 2020/8/25 11:13
 * @Description:
 */
public class DeepProtoType implements Serializable,Cloneable {
    public String name; //String属性
    public DeepCloneableTarget  deepCloneableTarget;//引用类型

    //完成深拷贝 --方式1，，，使用clone方法

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        //这里完成对基本数据类型和String的克隆
        deep = super.clone();
        //对引用类型的属性，进行单独处理
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
         return deepCloneableTarget;
    }
}
