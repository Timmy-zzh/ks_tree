package com.timmy.lgsf._05backtrack_dp._32dynamic_programming;

public class _03最长递增子序列_300 {

    public static void main(String[] args) {
        _03最长递增子序列_300 demo = new _03最长递增子序列_300();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int res = demo.lengthOfLIS(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个整数数组，查找最长增长子序列的长度
     * 2.解题思路
     * 2.1.原问题分解为子问题
     * -求数组nums[n] 的最长递增子序列的长度 = max(nums[n-1]) + 1
     * --等于求解求解数组n-1个元素的最长增长子序列的长度+1；
     * 2.2.状态转移方程
     * -dp[0] 代表数组0的增长子序列长度=1
     * -dp[i] = dp[i-1] +1  ;如果第i个元素的值比i-1的元素值大
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = 1;
        int res = 1;

        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     *
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
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     */
}
