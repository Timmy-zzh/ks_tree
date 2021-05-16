package com.timmy._review._05tree._00tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _04二叉树的层序遍历_102 {

    public static void main(String[] args) {
        TreeNode treeNode2 = new TreeNode(2, new TreeNode(1), null);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, new TreeNode(4));
        TreeNode root = new TreeNode(5, treeNode3, new TreeNode(6));

        System.out.print("中序遍历：");
        PrintUtils.printMid(root);
        System.out.println();
        System.out.print("前序遍历：");
        PrintUtils.printPre(root);
        System.out.println();
        _04二叉树的层序遍历_102 demo = new _04二叉树的层序遍历_102();
        List<List<Integer>> res = demo.levelOrder(root);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 层序遍历：使用队列保存遍历的节点
     * -当前节点出队列的时候，需要将左子节点和右子节点进行入队列
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode node = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                levelList.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            res.add(levelList);
        }
        return res;
    }

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层序遍历结果：
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     */
}
