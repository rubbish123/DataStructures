package com.meandi.HashTable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        hashTable.add(new Emp(1,"张三"));
        hashTable.add(new Emp(4,"李四"));
        hashTable.add(new Emp(8,"老八"));
        hashTable.add(new Emp(15,"张无忌"));
        hashTable.add(new Emp(11,"十一"));
        hashTable.list();
        hashTable.findEmpById(6);
        hashTable.findEmpById(8);
        hashTable.deleteEmpById(8);
        hashTable.deleteEmpById(11);
        hashTable.list();
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建HashTable管理多条链表
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示有多少条链表

    //构造器
    public HashTable(int size) {
        //初始化
        this.size = size;
        this.empLinkedListArray = new EmpLinkedList[size];
        //上面只是把数组创建出来，还要再初始化数组里的每条链表，不然报空指针了，切记
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        //根据员工id得到员工应当加入的链表
        int empLinkedListNo=hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    //遍历HashTable
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    //根据输入的id查找雇员
    public void findEmpById(int id){
        //首先使用散列函数来确定要查找的雇员在那个链表
        int empLinkedListNo=hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNo].findEmpById(id);

        if(emp!=null){
            System.out.printf("在第%d条链表中找到该雇员  id=%d,name=%s\n",empLinkedListNo,emp.id,emp.name);
        }else {
            System.out.println("在哈希表中没找到该雇员");
        }
    }
    //根据输入的id删除雇员
    public void deleteEmpById(int id){
        //首先用散列函数确定待删除雇员在哪条链表
        int empDeleteLinkedListNo=hashFun(id);
        empLinkedListArray[empDeleteLinkedListNo].deleteEmpById(empDeleteLinkedListNo,id);
    }
    //编写一个散列函数,此处使用简单的取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//表示链表
class EmpLinkedList {
    //头指针，指向第一个雇员
    private Emp head;

    //添加雇员到链表
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第"+no+"条链表为空");
            return;
        }
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }
    //根据id查找雇员
    //找到就返回Emp，没找到就返回null
    public Emp findEmpById(int id){
        //首先判空
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp=head;
        while (true){
            if(curEmp.id==id){
                break;//这是curEmp就指向要查找的雇员
            }
            if(curEmp.next==null){
                curEmp=null;
                break;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }
    public void deleteEmpById(int No,int id){
        if(head==null){
            System.out.println("第"+No+"条链表为空");
            return;
        }
        Emp curEmp=head;
        while (true){
            if(curEmp.next.id==id&&curEmp.next.next!=null){
                curEmp.next=curEmp.next.next;
                System.out.println("删除完毕");
                return;
            }else if (curEmp.next.id==id&&curEmp.next.next==null){
                curEmp.next=null;
                System.out.println("删除完毕");
                return;
            }
            if(curEmp.next==null){
                System.out.println("第"+No+"条链表中无此id");
                return;
            }
        }
    }
}
