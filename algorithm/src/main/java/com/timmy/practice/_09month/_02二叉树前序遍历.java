package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _02二叉树前序遍历 {

    public static void main(String[] args) {
        _02二叉树前序遍历 demo = new _02二叉树前序遍历();
        TreeNode node20 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), node20);
        PrintUtils.printLevel(root);
        List<Integer> res = new ArrayList<>();
//        demo.preTraversal(root, res);
        demo.preTraversal_v1(root, res);
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
     * 前序遍历： 根左右
     * 3，9，20，15，7
     */

    /**
     * 二叉树中序遍历:迭代
     * 左 - 根 - 右
     */
    public void preTraversal_v1(TreeNode node, List<Integer> res) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            //1.先遍历到最左侧的叶子节点,并存放到stack中
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            //2.node为null，说明遍历到最左侧叶子节点了，从stack中取出元素,并从节点的右子节点开始从头遍历
            TreeNode pop = stack.pop();
            //3.遍历右子节点
            node = pop.right;
        }
    }

    /**
     * 二叉树前序遍历-递归
     */
    public void preTraversal(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        preTraversal(node.left, res);
        preTraversal(node.right, res);
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
