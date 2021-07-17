package com.test;

/**
 * @author: ZL
 * @Date: 2020/8/28 14:27
 * @Description:
 */
public class LangTest {
    public static void main(String[] args) {
//        Set<String> strings = new LinkedHashSet<>();
//        strings.add(null);
//        strings.add("");
//        System.out.println(strings);
        for(int i=0; i<10; i++){
            if(i==5){
                System.out.println(i);
                continue;
            }
            System.out.print(i+" ");
        }
    }
}
