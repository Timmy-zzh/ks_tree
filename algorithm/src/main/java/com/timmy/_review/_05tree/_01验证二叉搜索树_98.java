package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _01验证二叉搜索树_98 {

    public static void main(String[] args) {
        _01验证二叉搜索树_98 demo = new _01验证二叉搜索树_98();
        TreeNode treeNode4 = new TreeNode(4, new TreeNode(3), new TreeNode(6));
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
     * 2。模拟运行：使用二叉搜索树的值区间进行判断
     * -根节点的值可以是任意值 (Integer.Min , Integer.Max), 假设根节点值为5，
     * -通过跟节点的值，和左子树的值比根节点值都小的特点，可以确定左子节点的值范围为[Integer.Min, 5)
     * -同理，右子节点的值范围为(5,Integer.Max)
     * --采用递归方式不断遍历下层的节点，和节点对应的值范围，如果有一个节点的值范围不在预计范围内，则说明该数不是bst
     */
    boolean isValid = true;

    public boolean isValidBST(TreeNode root) {
        isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return isValid;
    }

    private void isValidBST(TreeNode root, int l, int r) {
        //遍历到叶子节点，或者其他子树已经确定不是bst
        if (root == null || !isValid) {
            return;
        }
        //判断当前节点的值是否在预计值范围内
        if (!(l < root.val && root.val < r)) {
            //不在预计范围内，修改状态，并停止后续的递归调用
            isValid = false;
            return;
        }
        //当前节点在预计范围内，则判断左右子节点的值范围
        isValidBST(root.left, l, root.val);
        isValidBST(root.right, root.val, r);
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
