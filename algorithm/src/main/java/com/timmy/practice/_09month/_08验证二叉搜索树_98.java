package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _08验证二叉搜索树_98 {

    public static void main(String[] args) {
        _08验证二叉搜索树_98 demo = new _08验证二叉搜索树_98();
//        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
//        PrintUtils.printLevel(root);

//        TreeNode ndoe4 = new TreeNode(4, new TreeNode(3), new TreeNode(6));
//        TreeNode root = new TreeNode(5, new TreeNode(1), ndoe4);
//        PrintUtils.printLevel(root);

        TreeNode ndoe4 = new TreeNode(4, new TreeNode(3), null);
        TreeNode root = new TreeNode(5, new TreeNode(1), ndoe4);
        PrintUtils.printLevel(root);

        boolean res = demo.isValidBST(root);
        System.out.println("res:" + res);
    }

    private static class Result {
        public int min;
        public int max;
        public boolean isBst;

        public Result(int min, int max, boolean isBst) {
            this.min = min;
            this.max = max;
            this.isBst = isBst;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "min=" + min +
                    ", max=" + max +
                    ", isBst=" + isBst +
                    '}';
        }
    }

    /**
     * 1.理解题意
     * -输入一棵二叉树，判断这棵二叉树是否是二叉搜索树
     * 2.后续遍历解法
     * -先遍历左右子树，每棵子树遍历后得到当前子树的最大最小值和是否是bst的标识结果
     * -得到左右子树的结果后，与当前跟节点的值进行比较，是bst要求 根节点值 大于左子树的最大值
     * --且根节点值小于右子树的最小值
     * -两棵子树可以分别判断，先判断左子树是否为空，不为空判断左子树是否是bst，或者左子树最大值是否比根节点值小
     * --右子树也一样判断，不过是判断右子树最小值是否比根节点值大
     * -两棵子树与根节点的值都ok的话，则说明根节点子树是bst，并返回当前根节点子树的最大最小值
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Result res = isValidBstPost(root);
        System.out.println(res.toString());
        return res.isBst;
    }

    private Result isValidBstPost(TreeNode root) {
        if (root == null) {
            return null;
        }
        Result leftRes = isValidBstPost(root.left);
        Result rightRes = isValidBstPost(root.right);
        /**
         * 情况1：两棵子树都为空，
         * 情况2：两棵子树其中一棵为空
         * 情况3：两颗子树都不为空
         */
        //分别判断左右子树与根节点值的大小，不是bst直接返回false
        if (leftRes != null && (!leftRes.isBst || leftRes.max >= root.val)) {
            return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, false);
        }
        if (rightRes != null && (!rightRes.isBst || root.val >= rightRes.min)) {
            return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, false);
        }

        int nMin = leftRes == null ? root.val : leftRes.min;
        int nMax = rightRes == null ? root.val : rightRes.max;

        return new Result(nMin, nMax, true);
    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *  
     * 示例 1：
     * 输入：root = [2,1,3]
     * 输出：true
     *
     * 示例 2：
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     *  
     * 提示：
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     */
}
