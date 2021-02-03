package com.timmy.lgsf._03tree._16binary_tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _03二叉树的右视图_199 {

    public static void main(String[] args) {
        _03二叉树的右视图_199 demo = new _03二叉树的右视图_199();

        TreeNode node2 = new TreeNode(2, null, new TreeNode(5));
        TreeNode node3 = new TreeNode(3, null, new TreeNode(4));
        TreeNode root = new TreeNode(1, node2, node3);
        PrintUtils.printPre(root);
        List<Integer> list = demo.rightSideView(root);
        System.out.println("------");
        PrintUtils.print(list);
    }

    /**
     * 1。理解题意
     * -输入一颗树，返回这棵树中右侧的节点值集合
     * 2。解题思路
     * -层序遍历，获取每层遍历后的最后一个元素（就是右侧试图能看到的节点）
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (size == 1) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
        }
        return list;
    }

    /**
     *
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 示例:
     *
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     *
     */
}
