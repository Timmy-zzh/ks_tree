package com.timmy._review._00dp;

import com.timmy.common.PrintUtils;

public class _03打家劫舍_198 {

    public static void main(String[] args) {
        _03打家劫舍_198 demo = new _03打家劫舍_198();
//        int[] nums = {1, 2, 3, 1};
        int[] nums = {2, 7, 9, 3, 1};
        int res = demo.rob(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组表示沿街房屋内藏的现金，现在有一个小偷想进行偷盗，并使偷盗金额最大
     * --要求不同同时盗取两间相邻的房屋，否则会引起警报
     * 2。解题思路
     * 动态规划解法：0-1问题
     * -int[] dp表示经过i家后可能偷盗的金额
     * 状态转移方程式
     * --dp[0]= nums[0];
     * --dp[i]表示偷盗到第i家时的最大金额，有两种情况
     * --- dp[i]=dp[i-1]; //第i家不盗取
     * --- dp[i]=dp[i-2] + nums[i]; // 第i-1家不盗取，第i家盗取
     * -然后去dp[i]的最大值
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        PrintUtils.print(dp);
        return dp[N - 1];
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 提示：
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     *
     * 链接：https://leetcode-cn.com/problems/house-robber
     */
}
