package com.funddata;

/**
 * @author: ZL
 * @Date: 2020/7/8 09:53
 * @Description:
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1==i2);
        //true
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3==i4);
        //false
        //对于 Integer var = ? 在 -128 至 127 之间的赋值，Integer 对象是在 IntegerCache.cache 产生，
        //会复用已有对象，这个区间内的 Integer 值可以直接使用 == 进行判断，但是这个区间之外的所有数据，都
        //会在堆上产生，并不会复用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。
    }
}
