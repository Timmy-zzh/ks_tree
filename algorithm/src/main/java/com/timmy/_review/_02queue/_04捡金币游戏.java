package com.timmy._review._02queue;

import com.timmy.common.PrintUtils;

public class _04捡金币游戏 {

    public static void main(String[] args) {
        _04捡金币游戏 demo = new _04捡金币游戏();
        int[] A = {1, -1, -100, -1000, 100, 3};
        PrintUtils.print(A);
        int res = demo.maxResult(A, 2);
        System.out.println(res);
    }

    /**
     * 单调队列解法
     */
    public int maxResult(int[] A, int k) {
        return 0;
    }

    /**
     * 动态规划解法：
     * -状态转移方程式
     * --dp[i] 表示小明走到位置i的时候，手机的最多金币数
     * --dp[0] = nums[0], dp[1] = dp[0]+nums[1];
     * --dp[i] = max( dp[i-2] ,dp[i-1]) + nums[i]
     */
    public int maxResult_v1(int[] A, int k) {
        if (A.length == 1) {
            return A[0];
        }
        int[] dp = new int[A.length];
        dp[0] = A[0];
        dp[1] = A[0] + A[1];

        for (int i = 2; i < A.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + A[i];
        }
        PrintUtils.print(dp);
        return dp[A.length - 1];
    }
    /**
     * 【题目】给定一个数组 A[]，每个位置 i 放置了金币 A[i]，小明从 A[0] 出发。当小明走到 A[i] 的时候，
     * 下一步他可以选择 A[i+1, i+k]（当然，不能超出数组边界）。
     * 每个位置一旦被选择，将会把那个位置的金币收走（如果为负数，就要交出金币）。
     * 请问，最多能收集多少金币？
     *
     * 输入：[1,-1,-100,-1000,100,3], k = 2
     * 输出：4
     */
}
