package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _02二叉树后序遍历 {

    public static void main(String[] args) {
        _02二叉树后序遍历 demo = new _02二叉树后序遍历();
        TreeNode node20 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), node20);
        PrintUtils.printLevel(root);
        List<Integer> res = new ArrayList<>();
        demo.nextTraversal(root, res);
//        demo.nextTraversal_v1(root, res);
        PrintUtils.print(res);
    }

    /**
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 前序遍历： 左右根
     * 9，15，7，20，3
     */

    /**
     * 二叉树中序遍历:迭代
     * 左-右-根
     */
    public void nextTraversal_v1(TreeNode node, List<Integer> res) {
        Stack<TreeNode> stack = new Stack<>();
    }

    /**
     * 二叉树后序遍历-递归
     */
    public void nextTraversal(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        nextTraversal(node.left, res);
        nextTraversal(node.right, res);
        res.add(node.val);
    }

    /**
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
}
