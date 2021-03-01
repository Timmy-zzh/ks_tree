package com.timmy.lgsf._05backtrack_dp._33dynamic_programming;

import com.timmy.common.PrintUtils;

public class _01最大子序和_53 {

    public static void main(String[] args) {
        _01最大子序和_53 demo = new _01最大子序和_53();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = demo.maxSubArray(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，求连续子数组和为最大值的，并返回其最大和
     * 2。解题思路：
     * -动态规划
     * --原问题拆分成子问题：
     * --状态转移方程式：dp[i] = dp[i-1] + nums[i]  or// nums[i]
     * -因为要求是连续子数组，所以到dp[i] 的最大和要么是前一个状态值加nums[i],要么从当前元素开始
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        PrintUtils.print(dp);
        return res;
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     *
     * 示例 2：
     * 输入：nums = [1]
     * 输出：1
     *
     * 示例 3：
     * 输入：nums = [0]
     * 输出：0
     *
     * 示例 4：
     * 输入：nums = [-1]
     * 输出：-1
     *
     * 示例 5：
     * 输入：nums = [-100000]
     * 输出：-100000
     *
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * -105 <= nums[i] <= 105
     *
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     */
}
