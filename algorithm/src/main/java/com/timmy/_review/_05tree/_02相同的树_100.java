package com.timmy._review._05tree;

import com.timmy.common.TreeNode;

public class _02相同的树_100 {

    public static void main(String[] args) {
        _02相同的树_100 demo = new _02相同的树_100();
//        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
//        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(4));

//        TreeNode p = new TreeNode(1, new TreeNode(2), null);
//        TreeNode q = new TreeNode(1, null, new TreeNode(2));

        boolean res = demo.isSameTree(p, q);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入两棵树，判断这两棵树的节点结构相同，且节点的数据也相同
     * 2。解题思路
     * -两棵树都从根节点出发遍历，遍历到的节点都为null，则返回，如果有一个不为null，说明两棵树不相等了，则直接返回
     * -如果两棵树都不为null，则判断两棵树的节点值是否相同，不相同，则结果为false，中断递归
     * --如果节点值相同，则继续遍历后面的两个节点
     */
    boolean res;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        res = true;
        isSameTreeR(p, q);
        return res;
    }

    private void isSameTreeR(TreeNode p, TreeNode q) {
        if (!res) {
            return;
        }
        if (p == null && q == null) {       //都为null，直接返回
            return;
        }
        if (p == null || q == null) {       // 有一个为null，两棵树不同，返回
            res = false;
            return;
        }

        //判断节点值是否相同
        if (p.val != q.val) {
            res = false;
            return;
        }

        //节点值也相同，则往下遍历后面的节点
        isSameTreeR(p.left, q.left);
        isSameTreeR(p.right, q.right);
    }

    /**
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * 示例 1：
     * 输入：p = [1,2,3], q = [1,2,3]
     * 输出：true
     *
     * 示例 2：
     * 输入：p = [1,2], q = [1,null,2]
     * 输出：false
     *
     * 示例 3：
     * 输入：p = [1,2,1], q = [1,1,2]
     * 输出：false
     *
     * 提示：
     * 两棵树上的节点数目都在范围 [0, 100] 内
     * -104 <= Node.val <= 104
     * 链接：https://leetcode-cn.com/problems/same-tree
     */
}
