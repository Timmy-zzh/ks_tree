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
     * 2.后续遍历解法
     * -先遍历左右子树，并记录子树范围的最大最小值，和当前子树是否是一颗二叉搜索树
     * -最后返回
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
        if (leftRes == null && rightRes == null) {
            return new Result(root.val, root.val, true);
        }
        if (leftRes == null && (!rightRes.isBst || root.val > rightRes.min)) {
            return new Result(root.val, rightRes.max, false);
        }
        if (rightRes == null && (!leftRes.isBst || leftRes.max >= root.val)) {
            return new Result(leftRes.min, root.val, false);
        }
        int lMax = leftRes == null ? Integer.MIN_VALUE : leftRes.max;
        int rMin = rightRes == null ? Integer.MAX_VALUE : rightRes.min;
        if (lMax >= root.val || root.val > rMin) {
            return new Result(0, 0, false);
        }

        int nMin = leftRes == null ? root.val : leftRes.min;
        int nMax = rightRes == null ? root.val : rightRes.max;

        return new Result(nMin, nMax, true);
//        Result nRes = new Result(nMin, nMax, true);
//        System.out.println("leftRes" + (leftRes == null ? "null" : leftRes.toString()));
//        System.out.println("node:" + root.val);
//        System.out.println("rightRes:" + (rightRes == null ? "null" : rightRes.toString()));
//        System.out.println("newRes:" + nRes.toString());
//        return nRes;
    }

    /**
     * 1.理解题意
     * -输入一棵二叉树，判断这棵二叉树是否是二叉搜索树
     * 2。解题思路
     * -二叉搜索树的特点是中序遍历的时候，元素是升序排序的
     * -采用中序遍历方式，使用一个全局变量preVal保存前一个节点的元素值
     * --然后与当前遍历节点的元素值进行比较，如果preVal>= 当前节点的值，说明不是二叉搜索树
     * --然后更新preVal的值
     * -最后继续中序遍历其他节点
     */
    private long preVal = Integer.MIN_VALUE;

    public boolean isValidBST_v1(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftBst = isValidBST(root.left);
        if (!leftBst || preVal >= root.val) {
            return false;
        }
        preVal = root.val;
        return isValidBST(root.right);
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
