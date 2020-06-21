package com.meandi.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixExpressionParseSuffixExpression {
    public static void main(String[] args) {
        //完成中缀表达式转后缀表达式的功能
        //1.1+（（2+3）*4）-5 转成 1 2 3 + 4 * + 5 -
        //2.因为对字符串操作不方便，所以先将字符串存入arrayList
        //3.将得到的中缀表达式的list转为后缀表达式的list
        String expression="1+((2+3)*4)-5";
        System.out.println("中缀表达式对应的list:"+toInfixExpressionList(expression));
        System.out.println("后缀表达式对应的list:"+parseSuffixExpression(toInfixExpressionList(expression)));
    }
    //方法：将中缀表达式转为对应的List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List存放数据
        List<String> list = new ArrayList<String>();
        int i=0;//用于遍历字符串
        String str;//用于多位数拼接
        char c;//遍历的字符放入c
        while(i<s.length()){
            //如果c是非数字，直接加入List
            if((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                list.add(c+"");
                i++;
            }else {
                //是数字，需要拼接
                str="";
                while(i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str+=c;
                    i++;
                }
                list.add(str);
            }
        }
        return list;
    }
    //方法：将中缀表达式的list转为后缀表达式的list
    public static List<String> parseSuffixExpression(List<String> list){
        Stack<String> s1 = new Stack<String>();
        //由于s2只储存中间结果，无pop操作，所以可用list代替
        List<String> s2 = new ArrayList<String>();

        //遍历list
        for(String item:list){
            //如果是数，加入s2，利用正则表达式判断
            if(item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，弹出s1的运算符压入s2，直到遇到左括号
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//弹出左小括号
            }else {
                //当item优先级小于s1栈顶运算符，将s1栈顶运算符弹出并压入s2，再和s1新栈顶运算符比较
                while (s1.size()!=0&&Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item压入s1
                s1.push(item);
            }
        }
        //将s1剩余的运算符压入s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存在list中，按顺序输出就是对应的后缀表达
    }
}

//编写一个类 Operation 返回运算符对应的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //写一个方法返回对应的优先级数字
    public static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("运算符不存在");
                break;
        }
        return result;
    }
}