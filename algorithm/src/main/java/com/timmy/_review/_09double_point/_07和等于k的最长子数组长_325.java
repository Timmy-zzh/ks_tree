package com.timmy._review._09double_point;

import com.timmy.common.PrintUtils;

public class _07和等于k的最长子数组长_325 {

    public static void main(String[] args) {
        _07和等于k的最长子数组长_325 demo = new _07和等于k的最长子数组长_325();
//        int[] nums = {1, -1, 5, -2, 3};
        int[] nums = {-2, -1, 2, 1};
        PrintUtils.print(nums);
//        int res = demo.maxSubArrayLen(nums, 3);
        int res = demo.maxSubArrayLen(nums, 1);
        System.out.println("res:" + res);
    }

    /**
     * TODO 优化：前缀后
     * 2.
     */
    public int maxSubArrayLen(int[] A, int k) {

        return 0;
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组元素有正有负，要求其中一个最长子数组，要求子数组的的和等于3
     * 2。解题思路
     * -双指针 - 暴力解法， 右端指针不断往后移动，左侧指针后往前移动到0，不断增加元素和，当元素和等于k时，记录子数组的长度
     */
    public int maxSubArrayLen_v1(int[] A, int k) {
        int res = 0;
        int sum;
        for (int right = 0; right < A.length; right++) {
            sum = 0;
            for (int left = right; left >= 0; left--) {
                sum += A[left];
                if (sum == k) {
                    res = Math.max(res, right - left + 1);
                }
            }
        }
        return res;
    }

    /**
     * 题目描述:
     * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。
     *
     * 注意:
     * nums 数组的总和是一定在 32 位有符号整数范围之内的。
     *
     * 示例:
     * 示例 1:
     * 输入: nums = [1, -1, 5, -2, 3], k = 3
     * 输出: 4
     * 解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
     *
     * 示例 2:
     * 输入: nums = [-2, -1, 2, 1], k = 1
     * 输出: 2
     * 解释: 子数组 [-1, 2] 和等于 1，且长度最长。
     *
     * 进阶:
     * 你能使时间复杂度在 O(n) 内完成此题吗?
     */
}
