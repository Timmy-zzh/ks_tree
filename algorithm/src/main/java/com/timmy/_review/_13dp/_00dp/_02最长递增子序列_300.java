package com.timmy._review._13dp._00dp;

import com.timmy.common.PrintUtils;

class _02最长递增子序列_300 {

    public static void main(String[] args) {
        _02最长递增子序列_300 demo = new _02最长递增子序列_300();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 3, 18};
        int res = demo.lengthOfLIS(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个整数数组，求一个递增的子序列的长度
     * 2。解题思路
     * 动态规划解法：
     * -原问题拆分成子问题
     * -状态转移方程式
     * --dp[0]=1;
     * --dp[i] 表示数组元素从0到下标i，的最长子序列的长度
     * -两层遍历，内层遍历需要从下标元素0开始遍历，并于nums[i]比较大小，如果nums[i]更大，则子序列长度增加
     * -dp[i] = dp[x] + 1;( if nums[x] < nums[i])
     * --or 1
     */
    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        int res = 1;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            res = Math.max(res, dp[i]);
        }
        PrintUtils.print(dp);
        return res;
    }

    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     *
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     *
     * 示例 3：
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     *  
     * 提示：
     * 1 <= nums.length <= 2500
     * -104 <= nums[i] <= 104
     *  
     * 进阶：
     * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
     * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
     *
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     */
}
