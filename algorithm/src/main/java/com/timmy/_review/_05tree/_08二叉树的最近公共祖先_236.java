package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _08二叉树的最近公共祖先_236 {

    public static void main(String[] args) {
        _08二叉树的最近公共祖先_236 demo = new _08二叉树的最近公共祖先_236();
        TreeNode node2 = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        TreeNode node5 = new TreeNode(5, new TreeNode(6), node2);
        TreeNode node1 = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode root = new TreeNode(3, node5, node1);

        System.out.println("层序遍历");
        PrintUtils.printLevel(root);
        System.out.println();
//        TreeNode node = demo.lowestCommonAncestor(root, node5, node1);
        TreeNode node = demo.lowestCommonAncestor(root, node5, node2);
        System.out.println("res:" + node.val);

    }

    /**
     * 1.理解题意
     * -输入一个二叉树，和两个节点，求两个节点的最近公共祖先
     * 2。解题思路
     * -两个节点的公共祖先也是一个节点：存在两种情况
     * --p在祖先的左子树中，q在祖先的右子树中
     * --p，q在同一子树中
     * 2。1。采用二叉树的后序遍历法
     * -判断左子树中和右子树中是否有包含p，或q的？存在的话返回1，不存在或遍历到空节点返回0
     */

    TreeNode res = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        postOrder(root, p, q);
        return res;
    }

    private int postOrder(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return 0;
        }
        //先遍历获取左右子树的数量
        int ln = postOrder(root.left, p, q);
        int rn = postOrder(root.right, p, q);

        if (ln == 1 && rn == 1) {
            res = root;
        } else if (ln == 1 || rn == 1) { //有一个为1
            //根节点等于其中一个
            if (root == p || root == q) {   //p,q 公用同一个节点
                res = root;
            }
        }

        //返回当前节点表示的p，q数量
        int cn = ln + rn + ((root == p || root == q) ? 1 : 0);
        return cn;
    }

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 示例 1：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     *
     * 示例 2：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出：5
     * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
     *
     * 示例 3：
     * 输入：root = [1,2], p = 1, q = 2
     * 输出：1
     *
     * 提示：
     * 树中节点数目在范围 [2, 105] 内。
     * -109 <= Node.val <= 109
     * 所有 Node.val 互不相同 。
     * p != q
     * p 和 q 均存在于给定的二叉树中。
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
     */
}
