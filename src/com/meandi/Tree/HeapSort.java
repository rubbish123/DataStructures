package com.meandi.Tree;


import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] array=new int[]{2,5,3,1,10,4};
        int n=array.length;
        heapSort(array,n);

        System.out.println(Arrays.toString(array));

    }
    public static void swap(int[] array,int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    //n为数组长度 i为要heapify的节点的下标
    public static void heapify(int[] array,int n,int i){
        //递归出口
        if(i>=n){
            return;
        }
        int c1=2*i+1;
        int c2=2*i+2;
        int max=i;
        if(c1<n&&array[c1]>array[max]){
            max=c1;
        }
        if(c2<n&&array[c2]>array[max]){
            max=c2;
        }
        if(max!=i){
            swap(array,max,i);
            heapify(array,n,max);
        }
    }
    //n为数组长度
    public static void buildHeap(int[] array,int n){
        //求出最后一个非叶子节点，也就是最后一个结点的父节点，从这里开始heapify
        int lastNode=n-1;
        int parent=(lastNode-1)/2;

        for (int i = parent; i >= 0; i--) {
            heapify(array,n,i);
        }
    }
    //n为数组长度
    public static void heapSort(int[] array,int n){
        buildHeap(array,n);
        for (int i = n-1; i >= 0; i--) {
            //不断将最后一个节点和第一个节点交换，再heapify
            swap(array,i,0);
            heapify(array,i,0);
        }
    }
}
