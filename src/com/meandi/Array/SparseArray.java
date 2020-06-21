package com.meandi.Array;


//稀疏数组
public class SparseArray {
    public static void main(String[] args) {
        int[][] array1=new int[11][11];
        array1[1][2]=1;
        array1[2][3]=2;

        for(int[] row:array1){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.printf("\n");
        }

        //二维转稀疏
        int sum=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(array1[i][j]!=0){
                    sum++;
                }
            }
        }
        int[][] sparseArray=new int[sum+1][3];
        sparseArray[0][0]=11;
        sparseArray[0][1]=11;
        sparseArray[0][2]=sum;

        int k=1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(array1[i][j]!=0){
                    sparseArray[k][0]=i;
                    sparseArray[k][1]=j;
                    sparseArray[k][2]=array1[i][j];
                    k++;
                }
            }
        }

        for(int[] row:sparseArray){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.printf("\n");
        }

    }
}
