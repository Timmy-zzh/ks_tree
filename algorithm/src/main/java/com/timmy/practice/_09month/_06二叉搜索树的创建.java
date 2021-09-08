package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _06二叉搜索树的创建 {

    public static void main(String[] args) {
        _06二叉搜索树的创建 demo = new _06二叉搜索树的创建();
        int[] nums = {5, 2, 8, 5, 9};
        TreeNode node = null;
        for (int num : nums) {
            node = demo.insert(node, num);
            System.out.println("添加元素：" + num);
            PrintUtils.printLevel(node);
        }
    }

    /**
     * 1.理解题意
     * -在已有的二叉树基础上新插入一个元素val，插入新元素后还是一棵二叉搜素树
     * 2。解题思路
     * -新插入的元素作为一个节点插入到原先二叉树的叶子节点上，根据二叉树的中序遍历为升序进行区分
     */
    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } else if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    /**
     * 将一个整数数组创建为一个二叉搜索树[5,2,8,5,9]
     *       5
     *    2    8
     *       5   9
     */
}
