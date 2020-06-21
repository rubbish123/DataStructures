package com.meandi.Search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize=20;
    public static void main(String[] args) {
        int[] array=new int[]{1,8,10,89,1000,1234};
        System.out.println(fibonacciSearch(array,999));
    }
    //因为要用到斐波那契数列，所以我们先求一个斐波那契数列，用非递归
    public static int[] fib(){
        int[] fib=new int[maxSize];
        fib[0]=1;
        fib[1]=1;
        for (int i = 2; i < maxSize; i++) {
            fib[i]=fib[i-1]+fib[i-2];
        }
        return fib;
    }
    //斐波那契查找算法,使用非递归方式
    //array为数组，key为待查找值
    public static int fibonacciSearch(int[] array,int key){
        int low=0;
        int high=array.length-1;
        int k=0;//表示斐波那契分割数值的下标
        int mid=0;//存放mid值
        int[] f=fib();//获取斐波那契数列
        //获取斐波那契分割数值的下标
        while(high>f[k]-1){
            k++;
        }
        //因为f[k]的值可能大于a的长度，所以要构造一个新数组并指向a[]
        //不足的地方会用0填充
        int[] temp= Arrays.copyOf(array,f[k]);
        //因为数组需要有序，所以我们要用a数组最后的数来填充temp
        for (int i = high+1; i < temp.length; i++) {
            temp[i]=array[high];
        }
        //使用while来处理循环,找到我们的key
        while(low<=high){
            mid=low+f[k-1]-1;
            if(key<temp[mid]){
                //继续向左查找
                high=mid-1;
                k--;
            }else if(key>temp[mid]){
                low=mid+1;
                k=k-2;
            }else {
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
