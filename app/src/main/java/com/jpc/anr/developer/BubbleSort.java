package com.jpc.anr.developer;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    private static String TAG = "BubbleSort";
    private static int[] data = null;

    private static void initData(float rat) {
//        int num = (int) (10000*1.05);
        int num = (int) (10000*rat);
        data = new int[num];
        Random random = new Random(num);
        for (int i = 0; i < num; i++) {
            data[i] = random.nextInt();
        }
    }

    private static void sortData() {

        Log.d(TAG, "排序前"+Thread.currentThread().getName() );
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {//-1为了防止溢出
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        Log.d(TAG, "排序后" +Thread.currentThread().getName() );
    }
    //同样数据量，主线程里做这操作比子线程更耗时
    public static void sort(float rat) {
            initData(rat);
            sortData();

    }
}
