package com.meandi.Graph;

import java.util.*;

public class Graph {
    private ArrayList<String> vertexList;//存储图的顶点
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//记录某个节点是否被访问
    private Stack<Integer> stack=new Stack<Integer>();//用于dfs
    private Queue<Integer> queue=new LinkedList<Integer>();//用于bfs

    public static void main(String[] args) {
        int n=5;//节点个数
        String[] vertex={"A","B","C","D","E","F"};
        //创建图对象
        Graph graph = new Graph(vertex.length);
        for(String value:vertex){
            graph.insertVertex(value);
        }
        //添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,0,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(2,0,1);
        graph.insertEdge(2,1,1);
        graph.insertEdge(2,3,1);
        graph.insertEdge(2,4,1);
        graph.insertEdge(3,1,1);
        graph.insertEdge(3,2,1);
        graph.insertEdge(3,4,1);
        graph.insertEdge(3,5,1);
        graph.insertEdge(4,2,1);
        graph.insertEdge(4,3,1);
        graph.insertEdge(5,3,1);

        graph.showGraph();
        graph.dfs("C");
        graph.bfs("C");
    }

    //构造器，初始化矩阵和vertexList
    public Graph(int n){
        edges=new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges=0;
        isVisited=new boolean[n];
    }
    //显示矩阵
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //得到第一个未被访问的邻接节点的下标w
    public int getFirstUnvisitedNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if(edges[index][j]==1&&isVisited[j]==false){
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    public void dfs(String start){
        //从第一个节点开始访问
        isVisited[vertexList.indexOf(start)]=true;
        //isVisited[0]=true;
        System.out.println(start);
        stack.push(vertexList.indexOf(start));

        //当栈不为空时
        while(!stack.isEmpty()){
            //找到与当前节点相邻且未被访问的节点
            int v=getFirstUnvisitedNeighbor(stack.peek());
            if(v==-1){
                stack.pop();
            }else {
                isVisited[v]=true;
                System.out.println(vertexList.get(v));
                stack.push(v);
            }
        }

        //访问完毕后，将所有标志位重置为false
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i]=false;
        }
    }
    public void bfs(String start){
        isVisited[vertexList.indexOf(start)]=true;
        System.out.println(start);
        queue.add(vertexList.indexOf(start));
        int v2;

        while(!queue.isEmpty()){
            int v1=queue.remove();
            while ((v2=getFirstUnvisitedNeighbor(v1))!=-1){
                isVisited[v2]=true;
                System.out.println(vertexList.get(v2));
                queue.add(v2);
            }
        }
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i]=false;
        }
    }
    //返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        if(v1==v2){
            throw new RuntimeException("输入的数据有误");
        }
        return edges[v1][v2];
    }
    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    //weight表示边的权值
    public void insertEdge(int v1,int v2,int weight){
        if(v1==v2){
            System.out.println("输入的数据有误");
            return;
        }
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }
}
