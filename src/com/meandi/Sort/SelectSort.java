package com.meandi.Sort;

public class SelectSort {
    public static void main(String[] args) {
        int[] array=new int[]{2,4,1,3,6,5,9,7,8};
        selectSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int min=array[i];
            int minIndex=i;
            for (int j = i+1; j < array.length; j++) {
                if(min>array[j]){
                    min=array[j];
                    minIndex=j;
                }
            }
            int temp=array[i];
            array[i]=array[minIndex];
            array[minIndex]=temp;
        }
    }
}
