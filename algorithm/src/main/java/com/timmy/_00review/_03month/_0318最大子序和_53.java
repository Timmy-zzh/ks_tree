package com.timmy._00review._03month;

import com.timmy.common.PrintUtils;

public class _0318最大子序和_53 {

    public static void main(String[] args) {
        _0318最大子序和_53 demo = new _0318最大子序和_53();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = demo.maxSubArray(nums);
        System.out.println("res:" + res);
    }

    /**
     * todo
     * 1。
     * 2。分治法： 通过使用线段树，求解不同区间范围和的最大值
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        return res;
    }

    /**
     * 1.
     * 2.动态规划解法：
     * 暴力解法存在很多重复计算，可以这样处理，使用状态转移方程进行记录
     * dp[i] 表示到数组下标i为止，子数组和的最大值，开始位置范围是[0,i]
     * ---dp[i] = max{dp[i-1]+nums[i] , nums[i]}  --是加上前面子数组的和，还是从i位置开始子数组
     * <p>
     * dp[0]=nums[0];默认第一个元素
     * dp[i]=dp[i-1]+nums[i] (如果dp[i-1]是大于0)
     * --如果dp[i-1]小于0，则子数组的开始位置从i开始计算
     * 3.动态规划核心点：dp[i]的状态方程表示 ，从xx下标开始到下标i位置，的连续子数组的最大和
     *
     * @param nums
     * @return
     */
    public int maxSubArray_v2(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        PrintUtils.print(dp);
        return res;
    }

    //动态规划优化写法
    public int maxSubArray_v22(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            System.out.println("sum:" + sum);
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 1.理解题意
     * -输入一个整数数组nums，数组中元素有正数和负数，找到一个和最大的连续子数组，并返回其最大和
     * -关键词：整数数组，和最大，连续子数组
     * 2。解题思路：暴力解法
     * -要求连续子数组，表示可以从数组中第i个元素开始的连续子数组，并记录不同子数组和大小，从中选择最大的值
     * 3。边界与细节问题
     * -两层for循环，外层循环i表示子数组的开始元素，j是子数组[i,j]范围，并求和，找到最大值
     *
     * @param nums
     * @return
     */
    public int maxSubArray_v1(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum;//  每层子数组的元素和
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                res = Math.max(res, sum);
            }
            System.out.println("i:" + i + " ,res max:" + res);
        }
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
     * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     *
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     */
}
