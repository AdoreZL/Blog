package com.designpatterns.prototype.deepclone;

/**
 * @author: ZL
 * @Date: 2020/8/25 15:39
 * @Description:
 */
public class Client {
    public static void main(String[] args) throws Exception {
        DeepProtoType p = new DeepProtoType();
        p.name = "宋江";
        p.deepCloneableTarget= new DeepCloneableTarget("大牛", "大牛的类");

        //方式1 完成深拷贝
        DeepProtoType p2 = (DeepProtoType) p.clone();
        System.out.println("p.name"+p.name+"p.deepCloneableTarget="+p.deepCloneableTarget.hashCode());
        System.out.println("p2.name"+p2.name+"p2.deepCloneableTarget="+p2.deepCloneableTarget.hashCode());
    }
}
