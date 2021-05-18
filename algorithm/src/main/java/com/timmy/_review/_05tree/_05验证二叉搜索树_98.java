package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _05验证二叉搜索树_98 {

    public static void main(String[] args) {
        _05验证二叉搜索树_98 demo = new _05验证二叉搜索树_98();
//        TreeNode treeNode4 = new TreeNode(4, new TreeNode(3), new TreeNode(6));
//        TreeNode root = new TreeNode(5, new TreeNode(1), treeNode4);
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        System.out.print("中序遍历：");
        PrintUtils.printMid(root);
        System.out.println();
        boolean res = demo.isValidBST(root);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -判断一棵二叉树是否是二叉搜索树
     * -二叉搜索树特点：
     * --左子树所有节点值都小于根节点值
     * --右子树所有节点值都大于根节点值
     * --左右子树也是一棵二叉搜索树
     * 2.解题思路
     * -中序遍历方式求解，左子树节点 < 根节点 < 右子树节点
     * -使用一个变量保存上一次遍历节点的数值，然后与当前节点的值进行比较，如果比当前节点值小，说明是一颗bst，否则不是
     */
    boolean isValid = true;
    int preValue = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        isValidBSTR(root);
        return isValid;
    }

    private void isValidBSTR(TreeNode root) {
        if (root == null || !isValid) {
            return;
        }
        isValidBSTR(root.left);
        if (preValue >= root.val) {
            isValid = false;
            return;
        }
        preValue = root.val;
        isValidBSTR(root.right);
    }


    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 示例 1:
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     *
     * 示例 2:
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     */
}
