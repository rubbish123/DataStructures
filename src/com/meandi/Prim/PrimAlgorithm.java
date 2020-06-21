package com.meandi.Prim;

public class PrimAlgorithm {
    public static void main(String[] args) {
        
    }
}

//创建最小生成树
class MinTree{

}

class MGraph{
    int vertex;//保存节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边的权值

    public MGraph(int vertex){
        this.vertex=vertex;
        data=new char[vertex];
        weight=new int[vertex][vertex];
    }
}
