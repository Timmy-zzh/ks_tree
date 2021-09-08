package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;
import java.util.LinkedList;

public class _01二叉树的最大深度_104 {

    public static void main(String[] args) {
        _01二叉树的最大深度_104 demo = new _01二叉树的最大深度_104();
        TreeNode node20 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), node20);
        PrintUtils.printLevel(root);
        int res = demo.maxDepth(root);
        System.out.println("res:" + res);
    }

    /**
     * 2。dfs 广度优先遍历
     * -后续遍历，当前节点的深度，等于左右子树的深度最大值，加上1
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lefeD = maxDepth(root.left);
        int rightD = maxDepth(root.right);
        return Math.max(lefeD, rightD) + 1;
    }

    /**
     * 1.理解题意
     * -输入一颗二叉树，获取这棵树的最大深度
     * 2。解题思路
     * -bfs：广度优先遍历，从根结点开始不断往下层遍历，知道遍历到叶子结点
     * -使用一个集合保存树中每层遍历的节点，然后再取出这一层的节点，并把这一层节点的左右子节点添加到集合中用于下层节点的遍历
     */
    public int maxDepth_v1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int depth = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            while (size > 0) {
                TreeNode node = list.poll();
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
     */
}
