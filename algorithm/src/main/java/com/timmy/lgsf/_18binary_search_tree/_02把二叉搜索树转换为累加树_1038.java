package com.timmy.lgsf._18binary_search_tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _02把二叉搜索树转换为累加树_1038 {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, new TreeNode(8));
        TreeNode node6 = new TreeNode(6, new TreeNode(5), node7);

        TreeNode node2 = new TreeNode(2, null, new TreeNode(3));
        TreeNode node1 = new TreeNode(1, new TreeNode(0), node2);

        TreeNode root = new TreeNode(4, node1, node6);
        PrintUtils.printMid(root);
        System.out.println();
        _02把二叉搜索树转换为累加树_1038 demo = new _02把二叉搜索树转换为累加树_1038();
        TreeNode treeNode = demo.bstToGst(root);
        PrintUtils.printMid(treeNode);
        System.out.println();
    }

    /**
     * 1.理解题意
     * - 输入一棵二叉搜索树，中序遍历为升序，现在需要处理后变为累加树
     * -而且累加顺序是 从右子树-》根节点 -》左子树，为逆中序遍历顺序
     * 2。解题思路
     * -递归，逆中序遍历，数值累加
     * 3。边界和细节处理
     * -递归三要素，终止条件
     * -累加值使用辅助变量保存
     */
    int sum = 0;

    private TreeNode bstToGst(TreeNode root) {
        bstToGstReal(root);
        return root;
    }

    private void bstToGstReal(TreeNode root) {
        if (root == null) {
            return;
        }
        bstToGstReal(root.right);
        sum += root.val;
        root.val = sum;
        bstToGstReal(root.left);
    }

    /**
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     *
     * 提醒一下，二叉搜索树满足下列约束条件：
     *
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     * 注意：该题目与 538: https://leetcode-cn.com/problems/convert-bst-to-greater-tree/  相同
     *
     * 示例 1：
     *
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     * 示例 2：
     *
     * 输入：root = [0,null,1]
     * 输出：[1,null,1]
     * 示例 3：
     *
     * 输入：root = [1,0,2]
     * 输出：[3,3,2]
     * 示例 4：
     *
     * 输入：root = [3,2,4,1]
     * 输出：[7,9,4,10]
     *
     * 链接：https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree
     */
}
