package com.meandi.Stack;

public class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top=-1;

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        //首先判断栈满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        stack[++top]=value;
    }
    //出栈
    public int pop(){
        //首先判断栈空
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value=stack[top--];
        return value;
    }
    //遍历栈（遍历时要从栈顶显示数据）
    public void printAll(){
        //首先判断栈空
        if(isEmpty()){
            System.out.println("栈空");
        }
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d",i,stack[i]);
        }
    }
}
