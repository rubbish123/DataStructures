package com.meandi.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array=new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i]= (int) (Math.random()*8000000);//生成一个0-8000000的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat1.format(date1);

        System.out.println("排序前时间是："+format1);
        bubbleSort(array);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间是："+format2);
    }
    public static void bubbleSort(int[] array){
        //判断在一趟排序中是否发生了变量交换
        //boolean flag=false;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[i]>array[j]){
                    //如果进到这里了，表示数组还不有序，发生了交换
                    //flag=true;
                    int temp=array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
            /*if(!flag){
                //如果flag为false，就说明在一趟排序中，一次交换都没发生
                //说明数组有序，可以提前结束排序了
                break;
            }else {
                flag=false;
            }*/
        }
    }
}
