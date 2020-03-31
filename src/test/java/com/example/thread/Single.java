package com.example.thread;

/**
 * @author zl
 * @version 1.0
 * @date 2020/3/31 9:49
 */
public class Single {
    //饿汉式
    static class Single1{
        private static final Single s = new Single();
        private Single1(){

        }
        public static Single getInstance(){
            return s;
        }
    }

    //懒汉式
    static class Single2{
        private static Single2 s = null;
        public Single2() {
        }
        public static Single2 getInstance(){
            if(s==null){
                synchronized (Single2.class){
                    if(s==null){
                        s = new Single2();
                    }
                }
            }
            return s;
        }
    }
}
