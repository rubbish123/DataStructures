package com.meandi.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] array=new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i]= (int) (Math.random()*800000000);
        }
        System.out.println("排序前时间是"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //shellSort(array);
        shellSort2(array);
        System.out.println("排序后时间是"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    /*public static void shellSort(int[] array){
        int temp=0;
        for (int gap = array.length/2; gap > 0; gap/=2) {
            for (int i = gap; i < array.length; i++) {
                //遍历组中所有元素，共gap组
                for (int j = i-gap; j >=0 ; j -= gap) {
                    //若当前元素大于加上步长之后的元素，就交换
                    if(array[j]>array[j+gap]){
                        temp=array[j];
                        array[j]=array[j+gap];
                        array[j+gap]=temp;
                    }
                }
            }
        }
    }*/
    public static void shellSort2(int[] array){
        //对希尔排序交换法的优化->移位法
        for (int gap = array.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j=i;
                int temp=array[j];
                if(array[j]<array[j-gap]){
                    while (j-gap>=0&&temp<array[j-gap]){
                        array[j]=array[j-gap];
                        j-=gap;
                    }
                    array[j]=temp;
                }

            }
        }
    }
}
