package com.timmy.lgsf._03tree.review;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.Stack;

public class _01二叉搜索树中第K小的元素_230 {

    public static void main(String[] args) {
        _01二叉搜索树中第K小的元素_230 demo = new _01二叉搜索树中第K小的元素_230();
        TreeNode treeNode2 = new TreeNode(2, new TreeNode(1), null);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, new TreeNode(4));
        TreeNode root = new TreeNode(5, treeNode3, new TreeNode(6));

        PrintUtils.printMid(root);
        System.out.println();
        int res = demo.kthSmallest(root, 3);
        System.out.println("res:" + res);
    }

    /**
     * 1。
     * 2。迭代法-使用栈数据结构进行辅助
     * -从根节点开始遍历左子节点，将所有遍历到的节点放入栈中，
     * --直到左子节点为空，则栈顶元素则为最左侧节点，需要出栈
     * -出栈后遍历节点转到右节点，继续以右节点为根节点进行遍历左子节点
     * 3。边界与细节问题
     * -注意遍历到的当前节点的位置--遍历路线一定要清晰
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        int index = 1;

        while (curr != null || !stack.isEmpty()) {
            //中序遍历： 左-根-右
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            //遍历到最左边的左子树，出栈
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if (index++ == k) {
                return pop.val;
            }
            curr = pop.right;
        }
        return res;
    }


    /**
     * 1.理解题意
     * -输入一个二叉搜素树，查找第k个最小的元素
     * 2。解题思路:递归遍历
     * -对树进行中序遍历，当遍历到第k个元素时返回
     *
     * @param root
     * @param k
     * @return
     */
    int index = 1;
    int res = 0;

    public int kthSmallest_v1(TreeNode root, int k) {
        midTraversal(root, k);
        return res;
    }

    private void midTraversal(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        midTraversal(root.left, k);
        if (index++ == k) {
            res = root.val;
            return;
        }
        System.out.println(root.val);
        midTraversal(root.right, k);
    }

    /**
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     *
     * 示例 1：
     * 输入：root = [3,1,4,null,2], k = 1
     * 输出：1
     *
     * 示例 2：
     * 输入：root = [5,3,6,2,4,null,null,1], k = 3
     * 输出：3
     *
     * 提示：
     * 树中的节点数为 n 。
     * 1 <= k <= n <= 104
     * 0 <= Node.val <= 104
     *
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
     *
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
     */
}
