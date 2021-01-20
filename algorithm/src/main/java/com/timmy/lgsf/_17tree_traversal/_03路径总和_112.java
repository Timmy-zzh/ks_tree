package com.timmy.lgsf._17tree_traversal;

import com.timmy.common.TreeNode;

public class _03路径总和_112 {

    /**
     * * 给定如下二叉树，以及目标和 sum = 22，
     * *
     * *               5
     * *              / \
     * *             4   8
     * *            /   / \
     * *           11  13  4
     * *          /  \      \
     * *         7    2      1
     */
    public static void main(String[] args) {
        TreeNode node11 = new TreeNode(11, new TreeNode(7), new TreeNode(2));
        TreeNode node41 = new TreeNode(4, node11, null);

        TreeNode node42 = new TreeNode(4, null, new TreeNode(1));
        TreeNode node8 = new TreeNode(8, new TreeNode(13), node42);

        TreeNode root = new TreeNode(5, node41, node8);

        _03路径总和_112 demo = new _03路径总和_112();
        boolean result = demo.hasPathSum(root, 27);
        System.out.println("result:" + result);
    }

    /**
     * 112
     * 1.理解题意
     * -中序遍历树，遍历到叶子节点是判断综合和目标值是否相等
     * 2。解题思路
     * -前序遍历树，不断减少targetSum的值，直到遇到叶子节点
     * 3。边界与细节问题
     * -递归三要素，
     */
    private boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum - root.val == 0;
        }
        targetSum = targetSum - root.val;
        boolean left = hasPathSum(root.left, targetSum);
        boolean right = hasPathSum(root.right, targetSum);
        return left || right;
    }

    /**
     * 112
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例: 
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     *
     * 链接：https://leetcode-cn.com/problems/path-sum
     */

    /**
     * 113
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     *
     * 链接：https://leetcode-cn.com/problems/path-sum-ii
     */
}
