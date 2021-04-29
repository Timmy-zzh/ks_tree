package com.timmy._review._02queue;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _01二叉树的层序遍历_102 {

    public static void main(String[] args) {
        _01二叉树的层序遍历_102 demo = new _01二叉树的层序遍历_102();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        root.right = node20;
        node20.left = new TreeNode(15);
        node20.right = new TreeNode(7);

        List<List<Integer>> res = demo.levelOrder(root);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 使用两个list保存每层节点
     * -curr 保存了当前遍历的节点
     * -next 保存了下一层遍历的节点
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> curr = new ArrayList<>();
        if (root != null) {
            curr.add(root);
        }
        List<List<Integer>> res = new ArrayList<>();

        while (!curr.isEmpty()) {
            //每一层节点的val，组成的集合
            List<Integer> levelRes = new ArrayList<>();
            //下一层节点的集合
            List<TreeNode> next = new ArrayList<>();

            for (TreeNode node : curr) {
                levelRes.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            //curr转移，下次遍历就是下一层节点集合了
            curr = next;

            res.add(levelRes);
        }
        return res;
    }

    /**
     * 1.理解题意
     * -输入一个二叉树，对这颗二叉树进行层序遍历，并输出每层节点的集合
     * 2。模拟运行
     * -从根节点开始一层一层进行遍历，每遍历完一层就输出一层的节点
     * -采用队列进行数据的保存，先保存根节点
     * -然后根节点出队列，节点出队列的时候，需要将该节点的左右子节点进行入队列
     * -出队列的时候还会入队列，所以出队列的个数要求是一层节点的个数
     * 3。边界和细节问题
     * -根节点是否为null
     * -数据结构使用队列进行保存
     * -默认添加根节点到队列中
     * -出队列操作
     * 4。复杂度分析
     * -时间：需要遍历二叉树中的每个节点O(n)
     * -空间：使用队列保存，队列元素最大值是二叉树最大宽度-O(k)
     */
    public List<List<Integer>> levelOrder_v1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            res.add(list);
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
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     */
}
