package com.timmy.lgsf._02algorithm._3devide_and_conquer;

public class _03最大子序和_53 {

    public static void main(String[] args) {
        _03最大子序和_53 demo = new _03最大子序和_53();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = demo.maxSubArray(nums);
        System.out.println("result:" + result);
    }

    /**
     * TODO  这道题有意思
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * <p>
     * 进阶:
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     * <p>
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     */

    /**
     * 1.理解题意
     * -数组中，找到连续的子数组，其和最大，并返回最大和
     * 2。解题思路 - 滑动窗口
     * -暴力解法：两层for循环，从每个元素开始，计算累加和并判断
     * -分治法：
     * -累加法
     */
    public int maxSubArray(int[] nums) {
        int temp = nums[0];
        int sum = 0;

        for (int i = 1; i < nums.length; i++) {
            if (temp > 0) {
                temp += nums[i];
                sum = Math.max(sum, temp);
            } else {
                temp = sum = nums[i];
            }
        }
        return sum;
    }

}
