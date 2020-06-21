package com.meandi.Search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] array=new int[]{5,7,2,3,1,8,9,6,4};
        System.out.println(seqSearch(array,9));
    }
    public static int seqSearch(int[] array,int value){
        //线性查找就是逐一比对，如果相等返回下标，没有的话就返回-1
        for (int i = 0; i < array.length; i++) {
            if(array[i]==value){
                return i;
            }
        }
        return -1;
    }
}
