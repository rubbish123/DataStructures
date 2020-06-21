package com.meandi.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        int[] array=new int[80000000];
        for (int i = 0; i < array.length; i++) {
            array[i]= (int) (Math.random()*800000000);
        }
        System.out.println("排序前时间是："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        quickkSort(array,0,79999999);
        System.out.println("排序后时间是："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    public static void quickkSort(int[] array,int left,int right){
        //递归终止条件，必写，不然会堆栈溢出
        if(left>right){
            return;
        }
        int l=left;
        int r=right;
        int temp=array[left];//基准数
        while(l<r){
            //从右边开始找到一个小于基准数的
            while(array[r]>=temp&&r>l){
                r--;
            }
            //从左边开始找到一个大于基准数的
            while(array[l]<=temp&&r>l){
                l++;
            }
            //满足条件就交换
            if(l<r){
                int t=array[l];
                array[l]=array[r];
                array[r]=t;
            }
        }
        //l和r相遇后，把基准数归位
        array[left]=array[l];
        array[l]=temp;

        //左半部分排序
        quickkSort(array,left,r-1);
        //右半部分排序
        quickkSort(array,r+1,right);
    }
}
