package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

public class _02打家劫舍2_213 {

    public static void main(String[] args) {
        _02打家劫舍2_213 demo = new _02打家劫舍2_213();
//        int[] nums = {2, 3, 2};
//        int[] nums = {1, 2, 3, 1};
        int[] nums = {2, 1, 1, 2};
        PrintUtils.print(nums);
        int rob = demo.rob(nums);
        System.out.println("res:" + rob);
    }

    /**
     * 判断房间是偶数还是奇数
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int[] dp = new int[len];
        dp[0] = Math.max(0, nums[0]);
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        PrintUtils.print(dp);
        if (len % 2 == 1) {
            return dp[len - 2];
        }
        return dp[len - 1];
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
     * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     *
     * 示例 2：
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 3：
     * 输入：nums = [0]
     * 输出：0
     *  
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     * 链接：https://leetcode-cn.com/problems/house-robber-ii
     */
}
