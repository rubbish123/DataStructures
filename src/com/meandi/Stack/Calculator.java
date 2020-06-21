package com.meandi.Stack;

public class Calculator {
    public static void main(String[] args) {
        String expression="3000+2*6-2";
        //创建两个栈 数栈 符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关需要的变量
        int index=0;//用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';//保存每次扫描得到的char
        String keepNum="";//用于拼接多位数
        //循环扫描表达式
        while(true){
            //依次得到表达式中的每个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是数字还是符号，然后做相应处理
            if(operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    //有操作符，比较优先级
                    if(operStack.priority(ch)<=operStack.priority(operStack.getStackTop())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //结果压入数栈
                        numStack.push(res);
                        //当前操作符入符号栈
                        operStack.push(ch);
                    }
                    else {
                        operStack.push(ch);
                    }
                }else{
                    //为空直接入栈
                    operStack.push(ch);
                }
            }else {
                //数字就压入数栈，但是要先转为int
                //numStack.push(ch-'0');
                //思路分析
                //1.在处理数时，不能发显示一个数就入栈，因为可能是多位数
                //2.所以我么们要向后一直扫描，直到遇见符号
                //3.因此我们需要一个字符串变量用于拼接扫描到的数

                //处理多位数
                keepNum+=ch;
                //如果index已经到了表达式末尾，就直接入栈
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //如果是字符的话，就将keepNum入栈
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        //重要部分  记得把keepNum清空，否则还会在原基础上拼接
                        keepNum="";
                    }
                }
            }
            index++;
            if(index==expression.length()){
                break;
            }
        }
        //扫描完毕后就各自弹出然后运算
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字，就是结果
            if(operStack.isEmpty()){
                break;
            }
            //各自弹出运算
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            //结果压入数栈
            numStack.push(res);
        }
        System.out.println("3000+2*6-2="+numStack.pop());
    }
}


//数组栈，在原有的基础上增加了一些方法
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top=-1;

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }
    //返回当前栈顶值，但不弹出
    public int getStackTop(){
        return stack[top];
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
    //返回运算符的优先级，优先级是程序员定的
    //优先级使用数字表示，数字越大优先级越高
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1;//假定目前表达式中只有+ - * /
        }
    }
    //判断是否是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;//用于存放计算结果
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;//注意顺序问题，2在前
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;//顺序问题，同上
                break;
            default:
                break;
        }
        return res;
    }
}