package com.meandi.Queue;

public class ArrayQueue {
    private int maxSize;
    private int front;//队头
    private int rear;//队尾
    private int[] arr;

    public ArrayQueue(int maxSize, int front, int rear, int[] arr) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.arr = new int[maxSize];
    }
    //判满
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //判空
    public boolean isEmpty(){
        return rear==front;
    }
    //入队
    public void addQueue(int n){
        //首先判断是否满
        if(isFull()){
            System.out.println("队满");
            return;
        }
        arr[++rear]=n;
    }
    //出队
    public int getQueue(){
        //首先判断是否空
        if(isEmpty()){
            throw new RuntimeException("队空，无法取数据");
        }
        return arr[++front];
    }
}
