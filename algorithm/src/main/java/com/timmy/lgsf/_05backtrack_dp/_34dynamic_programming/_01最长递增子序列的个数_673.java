package com.timmy.lgsf._05backtrack_dp._34dynamic_programming;

import com.timmy.common.PrintUtils;

import java.util.Arrays;

//TODO fail
public class _01最长递增子序列的个数_673 {

    public static void main(String[] args) {
        _01最长递增子序列的个数_673 demo = new _01最长递增子序列的个数_673();
//        int[] nums = {2, 3, 5, 4, 7};
        int[] nums = {1, 2, 4, 3, 5, 4, 7, 2};
//        int[] nums = {2, 2, 2, 2, 2};
        int res = demo.findNumberOfLIS(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个无序的数组，找到最长递增子序列的个数，动态规划需要记录两个状态
     * --首先要记录在nums[i] 的情况下，最长递增子序列dp[i]的值，
     * ----dp[i] 表示在原始数组第i个元素时，最长递增子序列的元素个数
     * --还要记录一个状态count[i] 表示在有i个元素的子序列的个数
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];  //代表原数组中最长递增子序列的个数
        int[] counts = new int[N];   //代表有几个子序列
        dp[0] = 1;
        Arrays.fill(counts, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        for (int i = 1; i < N; i++) {
            if (dp[i] > dp[i - 1]) {
                counts[i] = counts[i - 1];
            } else {
                counts[i] = counts[i - 1] + 1;
            }
        }
        PrintUtils.print(dp);
        System.out.println("----------");
        PrintUtils.print(counts);
        return counts[N - 1];
    }


    /**
     * 给定一个未排序的整数数组，找到最长递增子序列的个数。
     *
     * 示例 1:
     * 输入: [1,3,5,4,7]
     * 输出: 2
     * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
     *
     * 示例 2:
     * 输入: [2,2,2,2,2]
     * 输出: 5
     * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
     *
     * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
     * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
     */
}
