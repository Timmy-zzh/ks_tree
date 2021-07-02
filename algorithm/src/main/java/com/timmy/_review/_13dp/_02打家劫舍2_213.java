package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

public class _02打家劫舍2_213 {

    public static void main(String[] args) {
        _02打家劫舍2_213 demo = new _02打家劫舍2_213();
//        int[] nums = {2, 3, 2};
        int[] nums = {1, 2, 3, 1};
//        int[] nums = {2, 1, 1, 2};
        PrintUtils.print(nums);
        int rob = demo.rob(nums);
        System.out.println("res:" + rob);
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组中每个元素代表一个房间存放的金额，现在所有的房间头尾相连围成一圈，且相邻的房间不可以同时被盗
     * --求小偷盗窃的最高金额是多少？
     * 2。解题思路
     * -如果只有一个房间，那就直接偷盗
     * -如果有两个房间，只能偷盗一个房间，选择其中金额更多的房间
     * -如果房间超过2个，因为首尾房间不能同时被盗，所以有两种选择，偷盗第一间房到倒数第二间房，最后一间房不偷盗 [0,n-2]
     * --获取第一间房不偷盗，偷盗最后一间房 [1,n-1]
     * 2.2.最后一种情况的两种选择，求最高金额结果
     */
    public int rob(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return Math.max(0, nums[0]);
        }
        if (N == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robR(nums, 0, N - 2), robR(nums, 1, N - 1));
    }

    private int robR(int[] nums, int start, int end) {
        //相邻的两间房不能同时被盗
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            // start+2 该房间可以偷 -- first + num[start+2]
            // 或不偷 -- second
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
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
