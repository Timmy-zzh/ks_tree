package com.timmy.lgsf._05backtrack_dp._32dynamic_programming;

public class _dp1_爬楼梯_70 {

    public static void main(String[] args) {
        _dp1_爬楼梯_70 demo = new _dp1_爬楼梯_70();
        for (int i = 1; i <= 10; i++) {
            int res = demo.climbStairs(i);
            System.out.println("i:" + i + " ,res:" + res);
        }
    }

    /**
     * 1。
     * 2。动态规划解法
     * -定义大小为n+1的int[] dp数组，用于保存i-2，i-1的状态（爬楼梯方法）
     * -从第3次开始遍历，一直到第n个，通过之前保存的状态，获取到下一阶段的爬楼梯方法数目
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 2];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 1。
     * 2。暴力解法--递归
     *
     * @param n
     * @return
     */
    public int climbStairs_v1(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs_v1(n - 1) + climbStairs_v1(n - 2);
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     *
     * 示例 2：
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 链接：https://leetcode-cn.com/problems/climbing-stairs
     */
}
