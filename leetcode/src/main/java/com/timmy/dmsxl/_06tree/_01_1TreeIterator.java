package com.timmy.dmsxl._06tree;

import com.timmy.leetcode.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的遍历
 */
public class _01_1TreeIterator {

    public static void main(String[] args) {
        _01_1TreeIterator demo = new _01_1TreeIterator();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.right = node2;
        node2.left = node3;

//        List<Integer> list = demo.orderTraversal(root);
        List<Integer> list = demo.orderTraversalIter(root);
//        PrintUtils.print(list);
    }

    /**
     * 144. 二叉树的遍历
     * 给定一个二叉树，返回它的 前序 遍历。
     * <p>
     * 示例:
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [1,2,3]
     */
    public List<Integer> orderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preTraversal(root, result);
        System.out.println("前序遍历");
        PrintUtils.print(result);

        result.clear();
        midTraversal(root, result);
        System.out.println("中序遍历");
        PrintUtils.print(result);

        result.clear();
        nextTraversal(root, result);
        System.out.println("后序遍历");
        PrintUtils.print(result);
        return result;
    }

    //前序遍历
    private void preTraversal(TreeNode node, List<Integer> list) {
        if (null == node) {
            return;
        }
        list.add(node.val);
        preTraversal(node.left, list);
        preTraversal(node.right, list);
    }

    //中序遍历
    private void midTraversal(TreeNode node, List<Integer> list) {
        if (null == node) {
            return;
        }
        midTraversal(node.left, list);
        list.add(node.val);
        midTraversal(node.right, list);
    }

    //后序遍历
    private void nextTraversal(TreeNode node, List<Integer> list) {
        if (null == node) {
            return;
        }
        nextTraversal(node.left, list);
        nextTraversal(node.right, list);
        list.add(node.val);
    }


    /***********************************
     * 二叉树的遍历--迭代法
     * 用递归能实现的，用栈也能实现
     */
    private List<Integer> orderTraversalIter(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        preTraversalIter(root, result);
        System.out.println("前序遍历");
        PrintUtils.print(result);

        return result;
    }

    /**
     * 前序遍历--用迭代法
     * 用栈保存入节点，入栈顺序 中右左 才能保存出栈是中左右
     */
    private void preTraversalIter(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            result.add(node.val);
            stack.push(node.right);
            stack.push(node.left);
        }
    }
}
