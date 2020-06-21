package com.meandi.Recursion;

//未完待续
public class Queue8 {

    //定义一个max表示有多少皇后
    int max=8;
    //定义数组存放结果
    int[] array=new int[max];


    public static void main(String[] args) {

    }

    //查看当我们放置第n个皇后时，就去检测该皇后是否和前面已摆放的皇后冲突
    //n表示第n个皇后
    public boolean judge(int n){
        for (int i = 0; i < n; i++) {

        }
        return false;
    }

    //写一个方法，打印结果
    public void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
