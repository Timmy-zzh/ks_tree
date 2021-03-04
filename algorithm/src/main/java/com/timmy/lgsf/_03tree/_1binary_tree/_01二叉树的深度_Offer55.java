package com.timmy.lgsf._03tree._1binary_tree;

import com.timmy.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _01二叉树的深度_Offer55 {

    public static void main(String[] args) {
        _01二叉树的深度_Offer55 demo = new _01二叉树的深度_Offer55();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        root.right = node20;
        node20.left = new TreeNode(15);
        node20.right = new TreeNode(7);
        int result = demo.maxDepth(root);
        System.out.println("result:" + result);
    }


    private int maxDepth(TreeNode root) {
//        int depth = bfs(root);
        int depth = dfs(root);
        return depth;
    }

    /**
     * 深度优先遍历
     * 1。递归，当前节点的深度，等于左右子树最大深度加1
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = dfs(root.left);
        int rightDepth = dfs((root.right));
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * 1。理解题意
     * -广度优先遍历获取树的深度
     * 2。解题思路
     * -从跟结点开始，while循环，不断获取左右子节点，存放到队列中
     * -默认先将跟节点存放到队列中，队列中只有一个元素，便利完一个元素，就说明本层遍历完毕
     * -在遍历本层的节点的时候，将左右子树的节点存放到队列中
     */
    private int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        //入队列
        queue.offer(root);
        int res = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            //取出当前层的元素，并将当前层元素的左右子树保存到队列中
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            res++;
        }
        return res;
    }

    /**
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，
     * 最长路径的长度为树的深度。
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     */
}
