package com.meandi.Search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array=new int[]{1,2,3,4,5,6,7,7,7,8,9};
        System.out.println(binarySearch3(array,0,10,7).toString());
        System.out.println(binarySearch3(array,0,10,11).toString());
    }
    //递归写法
    public static int binarySearch(int[] array,int left,int right,int value){
        int mid=(left+right)/2;
        int midVal=array[mid];
        //递归出口之一：没找到
        if(left>right){
            return -1;
        }
        //如果要找的数大于中间的数，向右递归
        if(value>midVal){
            return binarySearch(array,mid+1,right,value);
        }else if (value<midVal){
            return binarySearch(array,left,mid-1,value);
        }
        //递归出口之二：找到了
        else {
            return mid;
        }
    }
    //非递归写法
    public static int binarySearch2(int[] array,int left,int right,int value){
        while(left<=right){
            int mid=(left+right)/2;
            int midVal=array[mid];
            if(value>midVal){
                left=mid+1;
            }
            if(value<midVal){
                right=mid-1;
            }
            if(midVal==value){
                return mid;
            }
        }
        return -1;
    }
    //拓展:若数组中存在多个相同的数值，请将他们全部找出
    /*
    * 1.在找到一个之后不马上返回
    * 2.向左扫描，找出所有满足的值
    * 3.向右扫描，找出所有满足的值
    * 4.将结果的ArrayList返回
    * */
    public static ArrayList<Integer> binarySearch3(int[] array,int left,int right,int value){
        int mid=(left+right)/2;
        int midVal=array[mid];
        //递归出口之一：没找到
        if(left>right){
            ArrayList<Integer> resultIndexList = new ArrayList<>();
            resultIndexList.add(-1);
            return resultIndexList;
        }
        //如果要找的数大于中间的数，向右递归
        if(value>midVal){
            return binarySearch3(array,mid+1,right,value);
        }else if (value<midVal){
            return binarySearch3(array,left,mid-1,value);
        }
        //递归出口之二：找到了
        else {
            //new一个ArrayList存放结果
            ArrayList<Integer> resultIndexList = new ArrayList<>();
            //向左扫描
            int temp=mid-1;
            while (true){
                if (temp<0||array[temp]!=value){
                    break;
                }
                resultIndexList.add(temp);
                temp--;
            }
            //保存中间结果
            resultIndexList.add(mid);
            //向右扫描
            temp=mid+1;
            while (true){
                if(temp>array.length||array[temp]!=value){
                    break;
                }
                resultIndexList.add(temp);
                temp++;
            }
            return resultIndexList;
        }
    }
}
