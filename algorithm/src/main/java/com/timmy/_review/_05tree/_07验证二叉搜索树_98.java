package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _07验证二叉搜索树_98 {

    public static void main(String[] args) {
        _07验证二叉搜索树_98 demo = new _07验证二叉搜索树_98();
        TreeNode treeNode4 = new TreeNode(4, new TreeNode(3), new TreeNode(6));
        TreeNode root = new TreeNode(5, new TreeNode(1), treeNode4);
//        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        System.out.print("后序遍历：");
        PrintUtils.printNex(root);
        System.out.println();
        boolean res = demo.isValidBST(root);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -判断一棵二叉树是否是二叉搜索树
     * -二叉搜索树特点：
     * --左子树所有节点值都小于根节点值
     * --右子树所有节点值都大于根节点值
     * --左右子树也是一棵二叉搜索树
     * 2.解题思路
     * 后序遍历解法
     * -先遍历左子树，接着遍历右子树，然后是根节点，--可以先拿到左右子树的返回的范围信息
     * -根节点的范围： 大于左子树的最大值， 并且小于右子树的最小值
     * -如果是空节点，则范围是[max,min]
     */
    boolean isValid = true;
    Range empty = new Range();

    public boolean isValidBST(TreeNode root) {
        isValidBSTR(root);
        return isValid;
    }

    private Range isValidBSTR(TreeNode root) {
        if (root == null || !isValid) { //便利到叶子节点的下一层，返回空节点边界
            return empty;
        }
        //获取左右子节点的范围
        Range rangeL = isValidBSTR(root.left);
        Range rangeR = isValidBSTR(root.right);

        //根据左右子树的范围，判断当前节点值是否在范围内
        if (!(rangeL.max < root.val && root.val < rangeR.min)) {
            isValid = false;
            return empty;
        }
        return new Range(Math.min(rangeL.min, root.val),
                Math.max(rangeR.max, root.val));
    }

    class Range {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        public Range() {
        }

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }


    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 示例 1:
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     *
     * 示例 2:
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     */
}
