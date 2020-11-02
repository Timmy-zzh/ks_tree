package com.timmy.dmsxl._06tree;

import com.timmy.leetcode.PrintUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 层序遍历
 *
 */
public class _01_2TreeLevelIter {

    public static void main(String[] args) {
        _01_2TreeLevelIter demo = new _01_2TreeLevelIter();
//        TreeNode root = new TreeNode(3);
//        TreeNode node9 = new TreeNode(9);
//        TreeNode node20 = new TreeNode(20);
//        root.left = node9;
//        root.right = node20;
//
//        TreeNode node15 = new TreeNode(15);
//        TreeNode node7 = new TreeNode(7);
//        node20.left = node15;
//        node20.right = node7;
//
//        List<List<Integer>> result = demo.levelOrder(root);
//        for (List<Integer> list : result) {
//            PrintUtils.print(list);
//        }

        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node7;

        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        node2.left = node1;
        node2.right = node3;

        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);
        node7.left = node6;
        node7.right = node9;

        PrintUtils.print(root);
        demo.invertTree(root);
        System.out.println("invertTree");
        PrintUtils.print(root);
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。
     * （即逐层地，从左到右访问所有节点）。
     * <p>
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     * <p>
     * 解题思路：
     * 1.树是前序遍历方式存储的
     * 2.层序遍历需要使用队列数据结构进行存取
     * 3.遍历队列，取出队尾元素，然后将该节点的左右子节点入队列
     */
    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result;
        if (root == null) {
            return null;
        }
        result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.remove();
                System.out.println("treenode:" + treeNode.val);
                levelList.add(treeNode.val);

                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            PrintUtils.print(levelList);
            result.add(levelList);
        }
        return result;
    }

    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     * <p>
     * 解题思路：
     * 1.递归方式遍历树--前序遍历
     * 2.然后对数节点的左右子树节点翻转，然后继续遍历节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

}
