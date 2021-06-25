package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

public class _02打家劫舍_198 {

    public static void main(String[] args) {
        _02打家劫舍_198 demo = new _02打家劫舍_198();
//        int[] nums = {2, 3, 2};
//        int[] nums = {1, 2, 3, 1};
        int[] nums = {2, 7, 9, 3, 1};
        PrintUtils.print(nums);
        int rob = demo.rob(nums);
        System.out.println("res:" + rob);
    }

    /**
     * 1.理解题意
     * -输入一个数组表示每个房间中存放的金额，现在有个小偷要进行金币的偷窃，连续两个房间不能同时偷盗，问小偷可以偷盗的最大金额
     * -有n个房间，标号从0 ~ n-1
     * 2。解题思路：DP
     * -1。最后一步：
     * --最后一个房间偷盗后的金额，分为两种情况：
     * --最后一个房间偷盗：那最后金额为 n-3 房间偷盗的金额 + n-1房间的金额
     * --最后一个房间不偷盗：那最后金额为房间n-2的金额
     * -2。递推方程式
     * --dp[i] 表示经过i房间后偷盗的总金额数
     * --dp[i] = max(dp[i-1],dp[i-2]+A[i])
     * --max(不偷，偷) i房间的最大值
     * -3。初始值与边界值
     * --dp[0] = max(0,A[0])
     * --dp[1] = max(dp[0],A[1])
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

        return dp[len - 1];
    }
    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的
     * 房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *  
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     * 链接：https://leetcode-cn.com/problems/house-robber
     */
}
