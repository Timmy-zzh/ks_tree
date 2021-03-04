package com.timmy.lgsf._03tree._2tree_traversal;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;


public class _02递增顺序查找树_897 {

    private TreeNode header;

    /**
     * *        5
     * *       / \
     * *     3    6
     * *    / \    \
     * *   2   4    8
     * *  /        / \
     * * 1        7   9
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(2, new TreeNode(1), null);
        TreeNode node3 = new TreeNode(3, node2, new TreeNode(4));

        TreeNode node8 = new TreeNode(8, new TreeNode(7), new TreeNode(9));
        TreeNode node6 = new TreeNode(6, null, node8);

        TreeNode root = new TreeNode(5, node3, node6);

        PrintUtils.printMid(root);
        System.out.println("----");
        _02递增顺序查找树_897 demo = new _02递增顺序查找树_897();
        TreeNode node = demo.increaseBFS_v1(root);
        PrintUtils.printNex(node);
    }

    /**
     * 将头节点传入递归方法中处理
     */
    TreeNode head;

    private TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.right = increasingBST(root.right);
        if (root.left != null) {
            TreeNode node = root.left;
            root.left = null;
            head = node;
            while (node.right != null) {
                node = node.right;
            }
            node.right = root;
            return increasingBST(head);
        } else {
            return root;
        }
    }

    /**
     * 1。理解题意
     * -对当前树，进行中序遍历（左-中-右）
     * -然后构建一棵新的树，只有右子树节点
     * 2。解题思路
     * -定义属性 头节点
     * -中序遍历，不断设置头节点的右子节点，和移动节点
     */
    private TreeNode increaseBFS_v1(TreeNode root) {
        if (root == null) {
            return null;
        }
        header = new TreeNode(0);
        TreeNode temp = header;
        inorder(root);
        return temp.right;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        header.left = null;
        header.right = root;
        header = header.right;
        inorder(root.right);
    }

    /**
     * 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
     *
     * 示例 ：
     * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
     *
     *        5
     *       / \
     *     3    6
     *    / \    \
     *   2   4    8
     *  /        / \
     * 1        7   9
     *
     * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     *  1
     *   \
     *    2
     *     \
     *      3
     *       \
     *        4
     *         \
     *          5
     *           \
     *            6
     *             \
     *              7
     *               \
     *                8
     *                 \
     *                  9
     *
     * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
     */
}
