package com.meandi.Tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        /*TreeNode resNode = binaryTree.postOrderSearch(5);
        if(resNode!=null){
            System.out.println("找到了编号为5的节点");
        }else {
            System.out.println("没找到");
        }*/

        System.out.println("删除前前序遍历");
        binaryTree.preOrder();
        binaryTree.deleteNodeById(3);
        System.out.println("删除后前序遍历");
        binaryTree.preOrder();
    }
}

//先创建树节点
class TreeNode {
    private int data;
    private TreeNode left;//左右默认为空
    private TreeNode right;

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

//创建BinaryTree
class BinaryTree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
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
