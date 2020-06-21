package com.meandi.Tree.BinaarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] array=new int[]{7,10,8,11,5,4,6};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < array.length; i++) {
            binarySortTree.add(new Node(array[i]));
        }
        //中序遍历一下
        binarySortTree.inOrder();
        System.out.println();

        //测试删除叶子节点
        //binarySortTree.delNode(9);
        //binarySortTree.inOrder();
        //测试删除只有一个子树的节点
        //binarySortTree.delNode(7);
        //binarySortTree.inOrder();
        //测试删除有两个子树的节点
        binarySortTree.delNode(7);
        binarySortTree.inOrder();
    }
}

//创建二叉排序树
class BinarySortTree{
    private Node root;

    //查找要删除的节点
    public Node search(int value){
        if(root==null){
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    //传入一个Node，返回以该节点为根节点的二叉排序树的最小节点的值
    public int delRightTreeMin(Node node){
        Node target=node;
        while (target.left!=null){
            target=target.left;
        }
        //删除这个最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if(root==null){
            return;
        }else {
            //先找到要删除的节点
            Node targetNode=search(value);
            if(targetNode==null){
                return;
            }
            //若只有一个节点并且还找到了要删除的节点，说明是root
            if(root.left==null&&root.right==null){
                root=null;
                return;
            }
            //去找targetNode的父节点
            Node parent=searchParent(value);
            //如果要删除的是叶子节点
            if(targetNode.left==null&&targetNode.right==null){
                //判断是parent的左子节点还是右子节点
                if(parent.left!=null&&parent.left.value==value){
                    parent.left=null;
                } else if (parent.right!=null&&parent.right.value==value) {
                    parent.right=null;
                }
            } else if(targetNode.left!=null&&targetNode.right!=null){
                //删除有两个子树的节点
                int minVal=delRightTreeMin(targetNode.right);
                targetNode.value=minVal;
            }else {//删除有一个子树的节点
                //首先判断这个节点是不是根节点（意思就是根节点只有一个子树，然后要删根节点）
                if(parent==null){//表示没有父节点，他就是根节点
                    if(targetNode.left!=null){//表示只有左子树
                        root=targetNode.left;
                    }else if(targetNode.right!=null){
                        root=targetNode.right;
                    }
                }else if(targetNode.left!=null){
                    //如果targetNode是parent的左子节点
                    if(parent.left.value==value){
                        parent.left=targetNode.left;
                    }else {
                        //targetNode是parent的右子节点
                        parent.right=targetNode.left;
                    }
                }else {//如果targetNode有右子节点
                    if(parent.left.value==value){
                        parent.left=targetNode.right;
                    }else {
                        parent.right=targetNode.right;
                    }
                }
            }
        }
    }

    //添加节点方法
    public void add(Node node){
        //首先判断root是否为空
        if(root==null){
            root=node;
            return;
        }
        root.add(node);
    }

    //遍历方法
    public void inOrder(){
        if(root!=null){
            root.inOrder();
        }else {
            System.out.println("空树，无法遍历");
            return;
        }
    }
}

//创建节点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点，找到就返回，找不到返回空
    public Node search(int value){
        if(this.value==value){
            return this;
        }else if(value<this.value){
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else {
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父节点
    //value是待删除结点的值,找到它的父节点就返回，没有就返回null
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点，就返回
        if((this.left!=null&&this.left.value==value)||
                (this.right!=null&&this.right.value==value)){
            return this;
        }else {
            //如果查找的值小于当前节点的值且当前节点的左子节点不为空，就向左递归找
            if(value<this.value&&this.left!=null){
                return this.left.searchParent(value);
            }else if(value>=this.value&&this.right!=null){
                return this.right.searchParent(value);
            }else {
                return null;//没有父节点
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void add(Node node){
        if(node==null){
            return;
        }
        if(node.value<this.value){
            //如果当前节点左子节点为空，直接连上就行
            if(this.left==null){
                this.left=node;
            }else {
                //递归向左子树添加
                this.left.add(node);
            }
        }else {
            if(this.right==null){
                this.right=node;
            }else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void inOrder(){
        if(this.left!=null){
            this.left.inOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.inOrder();
        }
    }
}
