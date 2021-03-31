package com.timmy._00review._00tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _02二叉树的中序遍历_94 {

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
        _02二叉树的中序遍历_94 demo = new _02二叉树的中序遍历_94();
        List<Integer> res = demo.inorderTraversal(root);
        PrintUtils.print(res);
    }

    /**
     * 2.迭代法实现
     * -不断遍历寻找左子节点，并将遍历到的节点保存到栈中，直到最左叶子节点
     * -叶子节点的左子节点为null，栈顶元素出栈，并遍历右子节点，
     * --右子节点为不为null，则遍历右子节点的左子节点
     * --右子节点为null，则栈顶元素出栈
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //左子节点为null，栈顶元素出栈
            node = stack.pop();
            res.add(node.val);
            //遍历右子节点
            node = node.right;
        }
        return res;
    }

    /**
     * -中序遍历：左-根-右
     * -递归实现
     */
    public List<Integer> inorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    /**
     * 中序遍历：
     */
    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
