package com.meandi.Tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder();//1,2,4,5,3,6,7
        System.out.println();
        arrayBinaryTree.inOrder();//4,2,5,1,6,3,7
        System.out.println();
        arrayBinaryTree.postOrder();//4,5,2,6,7,3,1

    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    //重载preOrder方法
    public void preOrder() {
        this.preOrder(0);
    }

    //重载inOrder方法
    public void inOrder() {
        this.inOrder(0);
    }

    //重载postOrder方法
    public void postOrder() {
        this.postOrder(0);
    }

    //编写方法，完成顺序存储二叉树的前序遍历
    //index是数组下标，意思就是从哪个节点开始遍历
    public void preOrder(int index) {
        //首先判空
        if (array == null || array.length == 0) {
            System.out.println("数组为空，无法遍历");
            return;
        }
        //输出当前这个元素
        System.out.println(array[index]);
        //向左递归遍历
        if ((index * 2 + 1) < array.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < array.length) {
            preOrder(index * 2 + 2);
        }
    }

    //中序遍历
    public void inOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空，无法遍历");
            return;
        }
        if ((index * 2 + 1) < array.length) {
            inOrder(index * 2 + 1);
        }
        System.out.println(array[index]);
        if ((index * 2 + 2) < array.length) {
            inOrder(index * 2 + 2);
        }
    }

    //后序遍历
    public void postOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空，无法遍历");
            return;
        }
        if ((index * 2 + 1) < array.length) {
            postOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < array.length) {
            postOrder(index * 2 + 2);
        }
        System.out.println(array[index]);
    }
}
