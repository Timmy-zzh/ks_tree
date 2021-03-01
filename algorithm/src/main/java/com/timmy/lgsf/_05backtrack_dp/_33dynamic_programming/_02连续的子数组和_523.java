package com.timmy.lgsf._05backtrack_dp._33dynamic_programming;

public class _02连续的子数组和_523 {

    public static void main(String[] args) {
        _02连续的子数组和_523 demo = new _02连续的子数组和_523();
        int[] nums = {};
        int k = 0;
        boolean res = demo.checkSubarraySum(nums, k);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个非负数的数组和整数k，求连续子数组，要求子数组的长度最少为2，子数组的总和为k的倍数
     * 2。解题思路
     * -暴力解法
     * --1层遍历确定子数组的end，二层遍历确定子数组的start下标，将start到end的元素全部相加，并求sum，再继续判断
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int N = nums.length;
        for (int end = 1; end < N; end++) {
            for (int start = end - 1; start >= 0; start--) {
                int sum = 0;
                for (int index = start; index <= end; index++) {
                    sum += nums[index];
                }
                if (sum == k || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，
     * 其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
     *
     * 示例 1：
     * 输入：[23,2,4,6,7], k = 6
     * 输出：True
     * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
     *
     * 示例 2：
     * 输入：[23,2,6,4,7], k = 6
     * 输出：True
     * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
     *  
     * 说明：
     * 数组的长度不会超过 10,000 。
     * 你可以认为所有数字总和在 32 位有符号整数范围内。
     *
     * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
     */
}
