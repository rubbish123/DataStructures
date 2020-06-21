package com.meandi.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        int[] array=new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i]= (int) (Math.random()*800000000);
        }
        System.out.println("排序前时间是："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        insertSort(array);
        System.out.println("排序后时间是："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    public static void insertSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int temp=array[i];//当前要插入的数
            int j=i-1;//有序数组最后一个元素的下标
            //有序数组从后向前扫描
            while(j>=0&&temp<array[j]){
                //若可以插入就先把大于的数字整体后移
                array[j+1]=array[j];
                j--;
            }
            //插入
            array[j+1]=temp;
        }
    }
}
