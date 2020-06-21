package com.meandi.Sort;

import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
        int[] array=new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i]= (int) (Math.random()*800000000);
        }
        System.out.println("排序前时间是:"+new Date().getTime());
        radixSort(array);
        System.out.println("排序后时间是:"+new Date().getTime());
    }
    public static void radixSort(int[] array){
        //定义一个二维数组表示十个桶
        //为了防止数据溢出，二维数组的列数只能定义成待排序数组的长度
        //基数排序是空间换时间的经典算法
        int[][] bucket=new int[10][array.length];
        //定义一个一维数组来记录每个桶存放了多少数据,下标表示哪个桶
        int[] bucketElementCounts=new int[10];
        //首先要确定最大数有几位
        int max=array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i]>max){
                max=array[i];
            }
        }
        //得到最大数的位数
        int maxLength=(max+"").length();

        //n表示第几位，同时用来取每位的值
        for (int i = 0,n=1; i < maxLength; i++,n=n*10) {
            //第i轮，针对元素的个位数进行桶排序
            for (int j = 0; j < array.length; j++) {
                int digitOfElement=array[j]/n%10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]]=array[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序依次取出数据放入原数组
            int index=0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if(bucketElementCounts[k]!=0){
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        array[index]=bucket[k][l];
                        index++;
                    }
                }
                //第一轮之后要将记录每个桶中多少数据的数组清零
                bucketElementCounts[k]=0;
            }
        }
    }
}
