package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _07打家劫舍3_337 {

    public static void main(String[] args) {
        _07打家劫舍3_337 demo = new _07打家劫舍3_337();
//        TreeNode node2 = new TreeNode(2, null, new TreeNode(3));
//        TreeNode node3 = new TreeNode(3, null, new TreeNode(1));
//        TreeNode root = new TreeNode(3, node2, node3);

        TreeNode node4 = new TreeNode(4, new TreeNode(1), new TreeNode(3));
        TreeNode node5 = new TreeNode(5, null, new TreeNode(1));
        TreeNode root = new TreeNode(3, node4, node5);
        PrintUtils.printLevel(root);

        int res = demo.rob(root);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一颗二叉树，树中每个节点对应一个房间，现在小偷要从根节点开始行窃，且相邻的根节点和子节点不能同时被盗，否则会触发报警
     * --现在小偷开始从根节点房子进行行窃，在不触发报警的前提下求行窃的最高金额
     * 2。解题思路：后续遍历
     * 2。1。最后一步
     * -不断往下层后续遍历，每个根节点行窃后的结果有两种：
     * --偷盗根节点房间，则下层的左右子节点不行窃 root.val
     * --不偷盗根节点房间，则下层左右子节点房间可以行窃： left.val + right.val
     * --所有最后根节点房间行窃后，从上面的两种情况中选择行窃金额更高的方案; max<偷根节点,不偷根节点>
     * -每个节点行窃后的返回值就有两种情况，使用数据进行表示[偷，不偷] 的结果
     * 2。2。推导方程式
     * -如果遇到了空节点，返回[0,0]
     * -如果遇到了根节点，返回两种情况的最大值
     * --情况一：偷盗根节点 root.val + left[1](不偷) + right[1]
     * --情况二：不偷根节点 left[0](左子节点偷) + right[0](右子节点偷)
     * 2。3。初始值和边界值
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] ans = robR(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] robR(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] leftR = robR(root.left);
        int[] rightR = robR(root.right);

        //情况一：偷盗根节点
        int get = root.val;
        get += leftR[1] + rightR[1];

        //情况二：根节点不偷
        // 两个子节点可以偷，也可以不偷
        int noGet = Math.max(leftR[0], leftR[1]) + Math.max(rightR[1], rightR[0]);

        return new int[]{get, noGet};
    }

    /**
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     * 输入: [3,2,3,null,3,null,1]
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     *
     * 示例 2:
     * 输入: [3,4,5,1,3,null,1]
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * 链接：https://leetcode-cn.com/problems/house-robber-iii
     */
}
