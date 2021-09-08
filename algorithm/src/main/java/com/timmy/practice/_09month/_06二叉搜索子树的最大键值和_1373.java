package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _06二叉搜索子树的最大键值和_1373 {

    public static void main(String[] args) {
        _06二叉搜索子树的最大键值和_1373 demo = new _06二叉搜索子树的最大键值和_1373();
        TreeNode node4 = new TreeNode(4, new TreeNode(2), new TreeNode(4));
        TreeNode node5 = new TreeNode(5, new TreeNode(4), new TreeNode(6));
        TreeNode node3 = new TreeNode(3, new TreeNode(2), node5);
        TreeNode root = new TreeNode(1, node4, node3);
        PrintUtils.printLevel(root);
        int res = demo.maxSumBST(root);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -
     * 2。解题思路
     * 先判断当前根节点的树是否是一棵二叉搜索树，然后再计算这棵二叉搜索树的键值和
     */
    public int maxSumBST(TreeNode root) {

        return 0;
    }

    /**
     * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     *
     * 二叉搜索树的定义如下：
     * 任意节点的左子树中的键值都 小于 此节点的键值。
     * 任意节点的右子树中的键值都 大于 此节点的键值。
     * 任意节点的左子树和右子树都是二叉搜索树。
     *  
     * 示例 1：
     * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
     * 输出：20
     * 解释：键值为 3 的子树是和最大的二叉搜索树。
     *
     * 示例 2：
     * 输入：root = [4,3,null,1,2]
     * 输出：2
     * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
     *
     * 示例 3：
     * 输入：root = [-4,-2,-5]
     * 输出：0
     * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
     *
     * 示例 4：
     * 输入：root = [2,1,3]
     * 输出：6
     *
     * 示例 5：
     * 输入：root = [5,4,8,3,null,6,3]
     * 输出：7
     *  
     * 提示：
     * 每棵树有 1 到 40000 个节点。
     * 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
     * 链接：https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree
     */
}
