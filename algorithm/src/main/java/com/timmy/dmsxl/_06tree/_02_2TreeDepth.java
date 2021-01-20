package com.timmy.dmsxl._06tree;

import com.timmy.common.TreeNode;

import java.util.ArrayDeque;

public class _02_2TreeDepth {

    public static void main(String[] args) {
        _02_2TreeDepth demo = new _02_2TreeDepth();
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        root.left = node9;
        root.right = node20;

        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node20.left = node15;
        node20.right = node7;

        int maxDepth = demo.maxDepth(root);
        System.out.println("result:" + maxDepth);

    }

    /**
     * 104. 二叉树的最大深度
     * <p>
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * 返回它的最大深度 3
     * <p>
     * 解题思路：递归，
     * 1。入参与返回值：节点/返回执行到当前节点的深度
     * 2。终止条件：执行到叶子节点返回0
     * 3。单层递归逻辑：
     * --先求左右子树的深度，取最大值max
     * --在最大值max基础上+1，返回
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int max = Math.max(leftDepth, rightDepth);
        return max + 1;
    }


    /**
     * 层序遍历
     * 解题思路：
     * --使用队列保存每层节点，
     * --不断遍历，
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            result++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = deque.removeFirst();
                if (treeNode.left != null) {
                    deque.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.add(treeNode.right);
                }
            }
        }
        return result;
    }

    /**
     * 110. 平衡二叉树
     * <p>
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * <p>
     * 解题思路：递归 + 判断左右子树的高度
     * 1。入参与返回值
     * --节点
     * 2。终止条件
     * --遇到叶子节点
     * 3。单层递归逻辑
     * --求左右子树的高度，再判断左右子树是否平衡
     */
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;
    }

    /**
     * @param treeNode 节点
     * @return 返回树深度，-1:不平衡
     */
    private int getDepth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int leftDepth = getDepth(treeNode.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = getDepth(treeNode.right);
        if (rightDepth == -1) {
            return -1;
        }
        int abs = Math.abs(leftDepth - rightDepth);
        return abs > 1 ? -1 : Math.max(leftDepth, rightDepth) + 1;
    }


}
