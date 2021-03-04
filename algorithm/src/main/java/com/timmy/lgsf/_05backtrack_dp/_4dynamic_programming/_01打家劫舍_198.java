package com.timmy.lgsf._05backtrack_dp._4dynamic_programming;

import com.timmy.common.PrintUtils;

public class _01打家劫舍_198 {

    public static void main(String[] args) {
        _01打家劫舍_198 demo = new _01打家劫舍_198();
        int[] nums = {2, 7, 9, 3, 1};
        int res = demo.rob(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组表示沿街的房屋，数组中每个元素代表该房屋中的现金，现在有个小偷会进入房屋内偷窃
     * -要求小偷不能偷窃相邻的两个房屋，否则系统会自动报警，求小偷可以偷盗的最高金额
     * 2.解题思路-动态规划
     * 2.1.
     * -如果只有一个房屋，那么偷盗这个房屋的现金，即为最高偷盗金额
     * -如果有两个房屋，因为不能同时偷盗两个相邻的房屋，所有只能偷盗两个房屋中数值更高的的金额
     * -到第i个房间，有两种情况：
     * --要么偷盗了第i-1个房屋，当前房屋不偷
     * --要么偷盗了第i-2个房屋，继续偷盗当前房屋的金额
     * ---求这两种情况的最高金额值
     * 3.边界和细节处理
     * -使用dp[]动态数组表示偷盗第i个房屋的情况，数值位偷盗第i个房屋的最高金额
     *
     * @param nums
     * @return
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
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，
     * 一夜之内能够偷窃到的最高金额。
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
