package com.meandi.Search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(insertValueSearch(array,0,99,32));
    }

    //插值查找算法,需要数组是有序的
    public static int insertValueSearch(int[] array, int left, int right, int value) {
        //这三个条件不可缺少，否则我们得到的mid可能越界
        if (left > right || value < array[0] || value > array[array.length - 1]) {
            return -1;
        }
        //求出mid
        int mid = left + (right - left) * (value - array[left]) / (array[right] - array[left]);
        int midVal = array[mid];
        if (value > midVal) {
            return insertValueSearch(array, mid + 1, right, value);
        } else if (value < midVal) {
            return insertValueSearch(array, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
