package com.example.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: ZL
 * @Date: 2020/5/7 08:51
 * @Description:
 */
public class NoSaftDemo03 {
    public static void main(String[] args) {
        listNoSafe();
    }

    public static void listNoSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
