package com.timmy.lgsf._03tree._3binary_search_tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _01二叉搜索子树的最大键值和_1373 {

    //9, 3, 15, 20, 7,
    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5, new TreeNode(4), new TreeNode(9));
        TreeNode root = new TreeNode(3, new TreeNode(2), node5);
        PrintUtils.printMid(root);
        System.out.println();

        _01二叉搜索子树的最大键值和_1373 demo = new _01二叉搜索子树的最大键值和_1373();
        int sumBST = demo.maxSumBST(root);
        System.out.println("sumBST:" + sumBST);
    }

    /**
     * 解题思路：
     * -后续遍历： 左子树 > 右子树 > 跟节点
     * --获取左子树的信息： 最大值，最小值，是否是bst，总和sum
     */
    int res = 0;

    private int maxSumBST(TreeNode root) {
        Result result = process(root);
        return res;
    }

    //后续遍历，左右根
    private Result process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Result leftRes = process(root.left);
        Result rightRes = process(root.right);

        //左子树不是bst
        if (leftRes != null && (!leftRes.isBST || leftRes.max >= root.val)) {
            return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
        }

        //右子树不是bst
        if (rightRes != null && (!rightRes.isBST || root.val > rightRes.min)) {
            return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
        }

        int min = leftRes != null ? leftRes.min : root.val;
        int max = rightRes != null ? rightRes.max : root.val;
        int sum = leftRes != null ? leftRes.sum : 0;
        sum += rightRes != null ? rightRes.sum : 0;
        sum += root.val;
        res = res > sum ? res : sum;
        return new Result(min, max, sum, true);
    }

    //后续遍历，左右根,有空指针问题，考虑不周
//    private Result process_q(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//        Result leftRes = process(root.left);
//        Result rightRes = process(root.right);
//
//        if (leftRes == null && rightRes == null) {   //叶子节点
//            return new Result(root.val, root.val, root.val, true);
//        }
//
//        // 左子树为空
//        if (leftRes == null && rightRes.isBST && root.val <= rightRes.min) {
//            return new Result(root.val, rightRes.max, root.val + rightRes.sum, true);
//        }
//
//        // 右子树为空
//        if (rightRes == null && leftRes.isBST && leftRes.max < root.val) {
//            return new Result(leftRes.min, root.val, root.val + leftRes.sum, true);
//        }
//
//        int min = Integer.MIN_VALUE;
//        int max = Integer.MAX_VALUE;
//        int sum = 0;
//        boolean isBST = false;
//        if (leftRes.isBST && rightRes.isBST && leftRes.max < root.val && root.val <= rightRes.min) {
//            min = leftRes.min;
//            max = rightRes.max;
//            sum = leftRes.sum + rightRes.sum + root.val;
//            isBST = true;
//        }
//
//        return new Result(min, max, sum, isBST);
//    }

    class Result {
        int min;
        int max;
        int sum;
        boolean isBST;

        public Result(int min, int max, int sum, boolean isBST) {
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.isBST = isBST;
        }
    }

    /**
     * 1.理解题意
     * -输入是一棵二叉树，返回任意二叉搜索子树的最大健值和
     * --如何判断一棵子树是否是二叉搜索树
     * --如何记录一棵子树的健值和
     * --如何得到最大健值和？
     * 2。解题思路
     * - 前序遍历树
     * --判断当前节点是否是一棵二叉搜索树，如果是的话，返回这颗二叉搜索树的累加和，并更新结果值
     * --接着递归遍历左右子树
     * <p>
     * 暴力解法： O(n平方)
     * 先前序遍历，边界到跟节点，在中序遍历判断这颗树是否是二叉搜索树
     */
    int maxSum = 0;

    private int maxSumBST_v1(TreeNode root) {
        if (root == null) {
            maxSum = maxSum > 0 ? maxSum : 0;
            return maxSum;
        }
        int[] validBST = isValidBST(root);
        if (validBST[0] == 1) {
            maxSum = maxSum > validBST[2] ? maxSum : validBST[2];
        }

        maxSumBST_v1(root.left);
        maxSumBST_v1(root.right);
        return maxSum;
    }


    private int[] isValidBST(TreeNode root) {
        int[] res = new int[3];
        res[0] = 0;
        res[1] = Integer.MIN_VALUE;
        res[2] = 0;
        helper(root, res);
        return res;
    }

    // 递归 中序遍历
    private void helper(TreeNode root, int[] res) {
        if (root == null) {
            res[0] = 1;
            return;
        }
        helper(root.left, res);
        //判断前面节点是否是二叉搜索树？
        //判断上一个节点与当前节点值是否升序？
        if (res[0] == 0 || res[1] > root.val) {
            //前面节点不是二叉所搜树，或前一个节点值大于当前节点值
            res[0] = 0;
            res[2] = 0;
            return;
        }
        res[0] = 1;
        res[1] = root.val;
        res[2] = res[2] + root.val;
        helper(root.right, res);
    }

    /**
     * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     *
     * 二叉搜索树的定义如下：
     * 任意节点的左子树中的键值都 小于 此节点的键值。
     * 任意节点的右子树中的键值都 大于 此节点的键值。
     * 任意节点的左子树和右子树都是二叉搜索树。
     *  
     * 示例 1：
     * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
     * 输出：20
     * 解释：键值为 3 的子树是和最大的二叉搜索树。
     * 示例 2：
     *
     * 输入：root = [4,3,null,1,2]
     * 输出：2
     * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
     * 示例 3：
     *
     * 输入：root = [-4,-2,-5]
     * 输出：0
     * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
     * 示例 4：
     *
     * 输入：root = [2,1,3]
     * 输出：6
     * 示例 5：
     *
     * 输入：root = [5,4,8,3,null,6,3]
     * 输出：7
     *
     * 链接：https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree
     */
}
