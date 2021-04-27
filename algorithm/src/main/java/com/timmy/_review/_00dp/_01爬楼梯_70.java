package com.timmy._review._00dp;

import com.timmy.common.PrintUtils;

class _01爬楼梯_70 {

    public static void main(String[] args) {
        _01爬楼梯_70 demo = new _01爬楼梯_70();
        int res = demo.climbStairs(2);  //2
//        int res = demo.climbStairs(3);  //3
//        int res = demo.climbStairs(4); //5
//        int res = demo.climbStairs(5); //8
//        int res = demo.climbStairs(10);
        System.out.println("res:" + res);
    }

    /**
     * 2。动态规划解法
     * 状态转移方程
     * dp[0]=1
     * dp[i] 表示i+1阶台阶的走法
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        PrintUtils.print(dp);
        return dp[n - 1];
    }

    /**
     * 1.理解题意
     * -求爬n阶楼梯，每次可以爬1或2个台阶，求n个台阶有多少种不同的爬法
     * 2。解题思路
     * 分析：
     * -1阶楼梯：只有1种爬法
     * -2阶楼梯：有2种爬法（1，1），（2）
     * -3阶楼梯：有3种爬法（1，1，1），（1，2），（2，1）
     * -4阶楼梯：有5种爬法（从2级台阶再爬两个台阶）+（从3级台阶再爬一个台阶）
     * -n阶楼梯：（从n-2级台阶爬两个台阶）+（从n-1级台阶爬一个台阶）
     * 3。递归实现
     */
    public int climbStairs_v1(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
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
