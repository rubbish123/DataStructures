package com.meandi.Recursion;

public class MiGong {
    public static void main(String[] args) {
        //首先用一个二维数组模拟迷宫
        int [][] map=new int[8][7];
        //使用1表示墙

        //上下全部置为1
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部置为1
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板，1表示
        map[3][1]=1;
        map[3][2]=1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        findWay(map,1,1);

        System.out.printf("\n\n\n\n");

        //输出新的地图，查看路径
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    //使用递归回溯给小球找路
    //传入地图以及起始点坐标
    //小球能到map[6][5]则说明找到通路
    //约定：当map[i][j]为零表示没走过，1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
    //方向选择优先级：下->右->上->左
    //找到返回true，否则false
    public static boolean findWay(int[][] map,int i,int j){
        if(map[6][5]==2){
            //通路找到
            return true;
        }else {
            if(map[i][j]==0){
                //若当前点没走过,按照优先级 下->右->上->左 走
                map[i][j]=2;//假定该点可以走通
                if(findWay(map,i+1,j)){
                    //向下走,走通就返回true
                    return true;
                }else if (findWay(map,i,j+1)){
                    //向右走,走通就返回true
                    return true;
                }else if (findWay(map,i-1,j)){
                    //向上走,走通就返回true
                    return true;
                }else if (findWay(map,i,j-1)){
                    //向左走,走通就返回true
                    return true;
                }else {
                    //说明该点死路走不通，标为3
                    map[i][j]=3;
                    return false;
                }
            }else {
                //如果map[i][j]!=0,可能是1,2,3
                //而既然进到了这个else，就表明就算这里是2也走不通
                // 因为上面的if条件就是等于2，如果可以走通就不可能进这个else
                //所以直接返回false
                return false;
            }
        }
    }
}
