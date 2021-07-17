package com.designpatterns.prototype.deepclone;

import java.io.Serializable;

/**
 * @author: ZL
 * @Date: 2020/8/25 11:04
 * @Description:
 */
public class DeepCloneableTarget implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private String cloneName;
    private String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    //该类的熟悉都是String,因此我们这里使用默认的clone完成即可
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
