package com.meandi.KMP;


//结合灯神的视频和https://www.cnblogs.com/Yintianhao/p/9996524.html这篇文章
public class KMP {
    public static void main(String[] args) {
        int k=KMP("aababababca","abababca",getNext("abababca"));
        System.out.println(k);
    }
    public static int KMP(String t,String p,int[] next){
        int i=0;
        int j=0;

        while (i<t.length()&&j<p.length()){
            //如果相同就向后滑动
            if(j==-1||t.charAt(i)==p.charAt(j)){
                i++;
                j++;
            }else {
                //不同就让j向前滑动到next[j],也就是这一位的最长前缀，然后继续比较
                j=next[j];
            }
        }
        if(j==p.length()){
            return i-j;
        }else {
            return -1;
        }
    }
    public static int[] getNext(String p){
        int[] next=new int[p.length()+1];
        next[0]=-1;
        int i=0,j=-1;

        while(i<p.length()){
            if(j==-1||p.charAt(i)==p.charAt(j)){
                i++;
                j++;
                next[i]=j;
            }else {
                j=next[j];
            }
        }
        return next;
    }
}
