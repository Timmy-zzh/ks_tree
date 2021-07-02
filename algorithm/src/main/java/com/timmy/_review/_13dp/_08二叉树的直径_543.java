package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _08二叉树的直径_543 {

    public static void main(String[] args) {
        _08二叉树的直径_543 demo = new _08二叉树的直径_543();
        TreeNode node2 = new TreeNode(2, new TreeNode(4), new TreeNode(5));
//        TreeNode node5 = new TreeNode(5, null, new TreeNode(1));
        TreeNode root = new TreeNode(1, node2, new TreeNode(3));
        PrintUtils.printLevel(root);
        int res = demo.diameterOfBinaryTree(root);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一棵二叉树，求这颗二叉树的直径长度，直径长度是二叉树中任意两个节点长度的最大值
     * 2。解题思路：后序遍历
     * 2。1。最后一步
     * -遍历到空节点，长度为0，叶子节点长度为1
     * -根节点的长度 = max(左子节点的长度,右子节点长度)+1
     * 2。2。推导方程式
     * -最后的求解结果是= 左子树长度 + 右子树长度 + 1
     * 3.直径长度为节点数 - 1
     */
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ans = 1;
        postOrder(root);
        return ans - 1;
    }

    private int postOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = postOrder(root.left);
        int r = postOrder(root.right);
        ans = Math.max(ans, (l + r + 1));
        return Math.max(l, r) + 1;
    }

    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
     * 这条路径可能穿过也可能不穿过根结点。
     *
     * 示例 :
     * 给定二叉树
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
     */
}
