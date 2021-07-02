package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

public class _06目标和_494 {

    public static void main(String[] args) {
        _06目标和_494 demo = new _06目标和_494();
        int[] nums = {1, 1, 1, 1, 1};
        PrintUtils.print(nums);
        int res = demo.findTargetSumWays(nums, 3);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个整数数组，和整数目标值target，现在通过符号+,-将所有的整数串联起来，最后得到结果target，求一共有多少种串联方法
     * 2。解题思路：回溯法
     * -第i个元素如何选
     * --第i个元素可以选择+，也可以选择-，
     * -回溯停止：遍历完数组所有元素
     * -添加满足条件，遍历到最后元素时，累加值是否等于目标值target
     * 3。去除元素值为0的个数
     */
    int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        ans = 0;
        find(nums, target, 0);
        return ans;
    }

    private void find(int[] nums, int target, int i) {
        System.out.println("i:" + i + " ,target:" + target);
        if (i >= nums.length) {
            ans += target == 0 ? 1 : 0;
            return;
        }
        if (nums[i] != 0) {
            find(nums, target - nums[i], i + 1);
            find(nums, target + nums[i], i + 1);
        } else {
            find(nums, target, i + 1);
        }
    }

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * 示例 1：
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     *
     * 示例 2：
     * 输入：nums = [1], target = 1
     * 输出：1
     *
     * 提示：
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 1000
     * 链接：https://leetcode-cn.com/problems/target-sum
     */
}
