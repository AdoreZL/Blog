package com.designpatterns.proxy;

/**
 * @author: ZL
 * @Date: 2020/5/27 16:31
 * @Description:
 */
public class ProgrammerBigV implements Programmer {
    // 指定程序员大V要让谁发文章(先发文章、后点赞)
    private Java3y java3y ;

    public ProgrammerBigV() {
        this.java3y = new Java3y();
    }

    // 程序员大V点赞评论收藏转发
    public void upvote() {
        System.out.println("程序员大V点赞评论收藏转发！");
    }

    //加价了
    public  void  addMoney(){
        System.out.println("这次我要加100块");
    }

    @Override
    public void coding() {
        // 让Java3y发文章
        java3y.coding();

        // 程序员大V点赞评论收藏转发！
        upvote();

        //加价
        addMoney();
    }
}
