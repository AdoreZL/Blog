package com.io;

import org.junit.Test;
import java.nio.ByteBuffer;


/**
 * @author: ZL
 * @Date: 2020/9/18 12:02
 * @Description: 一：缓冲区，，在Java NIO中负责数据的存储。缓冲区j就是数组。用于存储不同数据类型的数据
 * 根据数据类型不同（Boolean），提供相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，，通过allocate（）获取缓冲区
 * 缓冲区存储数据的两个核心方法：
 * put():存入数据到缓冲区中
 * get():获取缓冲区中的数据
 *
 *
 *  * 缓冲区中的四个核心属性：
 *  * capacity:容量：缓冲区中最大存储数据的容量
 *  * limit：界限，表示缓冲区中可以操作数据的大小（limit后面的数据我们是不能进行读写的）
 *  * position：位置，表示缓冲区正在操作数据的位置
 *  *
 * position 《=limit 《= capacity
 */
public class TestBuffer {
    @Test
    public void test1(){
        //1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
    }
}

