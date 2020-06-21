package com.meandi.Tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] array = new int[]{13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(array);
        preOrder(huffmanTree);
    }

    //前序遍历
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("空树");
            return;
        }
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(int[] array) {
        //1.遍历数组
        //2.将每一个元素构建成一个Node
        //3.将Node放入ArrayList
        List<Node> nodes = new ArrayList<Node>();
        for (int value : array) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);

            //System.out.println(nodes);//1,3,6,7,8,13,29

            //取出权值最小的两个节点(二叉树)
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList中删掉处理过的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新生成的parent加入
            nodes.add(parent);
        }
        //返回赫夫曼树的根节点
        return nodes.get(0);
    }
}

//创建节点类
//为了Node类可以排序，要让其实现comparable接口
class Node implements Comparable<Node> {
    int value;//节点权值
    Node left;
    Node right;

    //写一个前序遍历，用于测试赫夫曼树是否构建成功
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        //表示从小到大排序（回头去学下comparable接口）
        return this.value - node.value;
    }
}