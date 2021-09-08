package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _06二叉搜索树中的搜索_700 {

    public static void main(String[] args) {
        _06二叉搜索树中的搜索_700 demo = new _06二叉搜索树中的搜索_700();
        TreeNode node2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root = new TreeNode(4, node2, new TreeNode(7));
        PrintUtils.printLevel(root);
        TreeNode res = demo.searchBST(root, 2);
        System.out.println("res:" + (res == null ? "null" : res.val));
    }

    /**
     * 1.理解题意
     * -输入一棵二叉搜索树，和一个整数，判断输入的整数在二叉搜索树中是否存在？存在的话返回当前节点
     * 2。解题思路：递归法
     * -从根节点开始判断，如果根节点的值和目标值相等，则返回该节点
     * -如果目标值比根节点值小，说明目标值在在左子树中，否则在右子树中
     * -如果遍历到空节点，说明不存在该目标值
     */
    public TreeNode searchBST_v1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST_v1(root.left, val);
        } else {
            return searchBST_v1(root.right, val);
        }
    }

    /**
     * 2。迭代法
     * -新建一个node节点变量作为遍历节点，然后从根节点开始不断往下遍历
     * -找到与目标值相等的节点则返回，不想等则从左右子节点继续往下找
     * -直到节点不为null，否则返回null
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (root.val == val) {
                return root;
            }
            if (val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }

    /**
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。
     * 如果节点不存在，则返回 NULL。
     *
     * 例如，
     * 给定二叉搜索树:
     *
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     * 和值: 2
     * 你应该返回如下子树:
     *
     *       2
     *      / \
     *     1   3
     * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
     * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
     */
}
