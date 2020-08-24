package com.algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author: ZL
 * @Date: 2020/7/27 15:53
 * @Description:
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        // 只需要修改成对应的方法名就可以了
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));
    }

    /**
     * @author: ZL
     * @Date: 2020/7/27 15:53
     * @Description: 冒泡排序规则
     */
    private static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // 外层循环控制比较轮数i
        for (int i = 0; i < array.length; i++) {
            // 内层循环控制每一轮比较次数，每进行一轮排序都会找出一个较大值
            // (array.length - 1)防止索引越界，(array.length - 1 - i)减少比较次数
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 前面的数大于后面的数就进行交换
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
