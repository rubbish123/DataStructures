package com.meandi.DAC;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    //汉诺塔的移动方法，使用分治算法
    public static void hanoiTower(int num,char a,char b,char c){
        if(num==1){
            System.out.println(a+"->"+c);
        }else {
            //把num-1个盘从a通过c移动到b
            hanoiTower(num-1,a,c,b);
            //把最底下的盘从a移动到c
            System.out.println(a+"->"+c);
            //把num-1个盘从b通过a移动到c
            hanoiTower(num-1,b,a,c);
        }
    }
}
