package com.meandi.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
        int[] array=new int[80000000];
        for (int i = 0; i < array.length; i++) {
            array[i]= (int) (Math.random()*800000000);
        }
        System.out.println("排序前时间是："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        mergeSort(array,0,79999999);
        System.out.println("排序后时间是："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    public static void mergeSort(int[] array,int L,int R){
        if(L==R){
            return;
        }
        else {
            int M=(L+R)/2;
            //System.out.println("L:"+L+" "+"M:"+M+" "+"R:"+R);
            mergeSort(array,L,M);
            mergeSort(array,M+1,R);
            //System.out.println(L+" "+M+" "+R);
            merge(array,L,M+1,R);
        }
    }
    public static void merge(int[] array,int L,int M,int R){
        int LEFT_SIZE=M-L;
        int RIGHT_SIZE=R-M+1;
        int[] left=new int[LEFT_SIZE];
        int[] right=new int[RIGHT_SIZE];
        int i,j,k;
        //System.out.println(LEFT_SIZE+" "+RIGHT_SIZE+" "+L+" "+M+" "+R);
        for(i=L;i<M;i++){
            left[i-L]=array[i];
        }
        for (i=M;i<=R;i++){
            right[i-M]=array[i];
        }

        i=0; j=0; k=L;
        while(i<LEFT_SIZE&&j<RIGHT_SIZE){
            if(left[i]<right[j]){
                array[k]=left[i];
                i++;
                k++;
            }else {
                array[k]=right[j];
                j++;
                k++;
            }
        }
        while(i<LEFT_SIZE){
            array[k]=left[i];
            i++;
            k++;
        }
        while (j<RIGHT_SIZE){
            array[k]=right[j];
            j++;
            k++;
        }
    }
}
