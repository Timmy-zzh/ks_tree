package com.timmy._00review._00dp;

import com.timmy.common.PrintUtils;

/**
 * 动态规划
 * 状态转移还是线性的变化
 */
public class _04不同路径_62 {

    public static void main(String[] args) {
        _04不同路径_62 demo = new _04不同路径_62();
//        int res = demo.uniquePaths(3, 7);
        int res = demo.uniquePaths(7, 3);
//        int res = demo.uniquePaths(3, 3);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -有m*n的网络，要求机器人从网络的左上角移动到网络的右下角，一共有多少条不同的移动路径
     * -要求每部移动方向只能是向下或向右
     * 2。解题思路
     * 动态规划解法
     * -使用二维数组dp[i][j] 表示从左上角位置[0,0]开始到位置[i,j]的移动路径的数目
     * -到达dp[i][j]位置只能从两个方向到达，从左边到右边，从上边移动下边
     * --所以dp的状态转移方程式为：dp[i][j]=dp[i-1][j] + dp[i][j-1];
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        PrintUtils.print(dp);
        return dp[m - 1][n - 1];
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     * 示例 2：
     *
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     *
     * 示例 3：
     * 输入：m = 7, n = 3
     * 输出：28
     *
     * 示例 4：
     * 输入：m = 3, n = 3
     * 输出：6
     *  
     * 提示：
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 109
     *
     * 链接：https://leetcode-cn.com/problems/unique-paths
     */
}
