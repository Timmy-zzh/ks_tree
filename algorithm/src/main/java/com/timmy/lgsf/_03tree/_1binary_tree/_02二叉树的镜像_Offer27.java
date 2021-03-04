package com.timmy.lgsf._03tree._1binary_tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _02二叉树的镜像_Offer27 {

    public static void main(String[] args) {
        _02二叉树的镜像_Offer27 demo = new _02二叉树的镜像_Offer27();

        TreeNode node2 = new TreeNode(2,new TreeNode(1),new TreeNode(3));
        TreeNode node7 = new TreeNode(7,new TreeNode(6),new TreeNode(9));
        TreeNode root = new TreeNode(4,node2,node7);
        PrintUtils.printPre(root);
        TreeNode node = demo.mirrorTree(root);
        System.out.println("------");
        PrintUtils.printPre(node);
    }

    /**
     * 1。理解题意
     * -输入以颗树，将树中左右子树进行交换
     * 2。解题思路
     * -递归，获取当前节点的左右结点，并进行交换
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }

    /**
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * 例如输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 镜像输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     */
}
