package com.timmy.lgsf._05backtrack_dp._6dynamic_programming;

public class _03目标和_494 {

    public static void main(String[] args) {
        _03目标和_494 demo = new _03目标和_494();
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        int res = demo.findTargetSumWays(nums, S);
        System.out.println("res:" + res);
    }

    /**
     * 枚举：在处理每个元素前都有两种情况
     *
     * @param nums
     * @param S
     * @return
     */
    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, S, 0, 0);
        return count;
    }

    /**
     * @param nums  原始数组
     * @param S     目标和
     * @param index 当前处理的第i个元素
     * @param sum   当处理到第i个元素时，前面元素的总和
     */
    private void calculate(int[] nums, int S, int index, int sum) {
        if (index == nums.length) {
            if (sum == S) {
                count++;
            }
            return;
        }
        calculate(nums, S, index + 1, sum + nums[index]);
        calculate(nums, S, index + 1, sum - nums[index]);
    }

    /**
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
     * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * 示例：
     * 输入：nums: [1, 1, 1, 1, 1], S: 3
     * 输出：5
     * 解释：
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * 一共有5种方法让最终目标和为3。
     *  
     * 提示：
     * 数组非空，且长度不会超过 20 。
     * 初始的数组的和不会超过 1000 。
     * 保证返回的最终结果能被 32 位整数存下。
     *
     * 链接：https://leetcode-cn.com/problems/target-sum
     */
}
