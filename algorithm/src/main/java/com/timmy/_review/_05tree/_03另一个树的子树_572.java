package com.timmy._review._05tree;

import com.timmy.common.TreeNode;

public class _03另一个树的子树_572 {

    public static void main(String[] args) {
        _03另一个树的子树_572 demo = new _03另一个树的子树_572();
        TreeNode node4 = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        TreeNode root = new TreeNode(3, node4, new TreeNode(5));
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));

        boolean res = demo.isSubtree(root, subRoot);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入两棵树，判断树2是否是树1的一棵子树，
     * 2。解题思路
     * -遍历树1，从树1的每个节点开始判断从该节点出发与树2是否相等
     * --如果相等，说明树2是树1的一颗子树
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return check(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    //判断树1从节点root，和树2从节点subRoot开始，两棵树是否相同
    private boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        }
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }

    /**
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
     * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     *
     * 示例 1:
     * 给定的树 s:
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 t：
     *    4
     *   / \
     *  1   2
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     *
     * 示例 2:
     * 给定的树 s：
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * 给定的树 t：
     *    4
     *   / \
     *  1   2
     * 返回 false。
     *
     * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
     */
}
