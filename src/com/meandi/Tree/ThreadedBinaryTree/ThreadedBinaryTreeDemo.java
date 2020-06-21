package com.meandi.Tree.ThreadedBinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索化二叉树的功能
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(10);
        TreeNode node6 = new TreeNode(14);

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threadedNodes();

        //测试10这个节点，看他的前驱和后继对不对
        /*System.out.println(node5.getLeft());//前驱 3
        System.out.println(node5.getRight());//后继 1*/

        //测试遍历
        binaryTree.threadedList();//8,3,10,1,14,6
    }
}

//先创建树节点
class TreeNode {
    private int data;
    private TreeNode left;//左右默认为空
    private TreeNode right;

    //说明：leftType==0表示指向的是左子树，leftType==1表示指向的是前驱节点
    //说明：rightType==0表示指向的是右子树，rightType==1表示指向的是后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public TreeNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }

    //递归删除节点
    /*
     * 说明：
     *   若是叶子节点就直接删除
     *   若是非叶子节点就将其子树一并删掉
     * */
    public void deleteNodeById(int value) {
        //切记要判空
        if (this.left != null && this.left.data == value) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.data == value) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.deleteNodeById(value);
            //这儿不要return，因为可能你左边没有但是右边有，你这儿return他就不去右边找了
        }
        if (this.right != null) {
            this.right.deleteNodeById(value);
        }
    }

    //先序遍历
    public void preOrder() {
        System.out.println(this);
        //向左递归
        if (this.left != null) {
            this.left.preOrder();
        }
        //向右递归
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.inOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //先序遍历查找
    public TreeNode preOrderSearch(int value) {
        if (this.data == value) {
            return this;
        }
        //若左子节点不为空，就向左递归，找到就返回
        TreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(value);
        }
        if (resNode != null) {
            //找到了
            return resNode;
        }
        //如果还为空，就向右子节点递归
        if (this.right != null) {
            resNode = this.right.preOrderSearch(value);
        }
        return resNode;
    }

    //中序遍历查找
    public TreeNode inOrderSearch(int value) {
        TreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.inOrderSearch(value);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.data == value) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.inOrderSearch(value);
        }
        return resNode;
    }

    //后序遍历查找
    public TreeNode postOrderSearch(int value) {
        TreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(value);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(value);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.data == value) {
            return this;
        }
        return resNode;
    }
}

//创建ThreadedBinaryTree,实现了线索化功能的二叉树
//因为代码是从之前二叉树的代码复制来的，所以类的名字我就不改了
class BinaryTree {
    private TreeNode root;
    //为了实现线索化，要定义一个指针指向当前节点的前驱节点
    //在递归进行线索化的过程中，pre总是保存前驱节点
    private TreeNode pre = null;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    //重载threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历中序线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，储存当前遍历的节点，从root开始
        TreeNode node = root;
        while (node != null) {
            //循环找到leftType==1的节点，第一个就是8
            //后面随着遍历而变化，因为leftType==1时
            //说明该节点是线索化处理的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //编写二叉树中序线索化的方法
    //node 需要线索化的节点
    public void threadedNodes(TreeNode node) {
        //首先判空
        //node==null  不能线索化
        if (node == null) {
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.getLeft());

        //2.线索化当前节点[有难度，多看几次]
        //如果当前节点左指针为null，将其指向前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            //改变当前左指针类型
            node.setLeftType(1);
        }
        //处理右节点其实就是让node指向下一个节点，让pre指向当前节点，再让pre的right指向node
        if (pre != null && pre.getRight() == null) {
            //前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前趋节点右指针类型
            pre.setRightType(1);
        }
        //重要：每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //3.再线索化右子树
        threadedNodes(node.getRight());
    }

    //删除节点
    public void deleteNodeById(int value) {
        //切记首先判断root是否为空
        if (root != null) {
            //如果只有一个root节点，就要判断root是否是要删除的节点
            if (root.getData() == value) {
                root = null;
            } else {
                //递归删除
                root.deleteNodeById(value);
            }
        } else {
            System.out.println("空树无法操作");
        }
    }

    //先序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //中序遍历
    public void inOrder() {
        if (this.root != null) {
            this.root.inOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //前序遍历查找
    public TreeNode preOrderSearch(int value) {
        if (root != null) {
            return root.preOrderSearch(value);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public TreeNode inOrderSearch(int value) {
        if (root != null) {
            return root.inOrderSearch(value);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public TreeNode postOrderSearch(int value) {
        if (root != null) {
            return root.postOrderSearch(value);
        } else {
            return null;
        }
    }
}