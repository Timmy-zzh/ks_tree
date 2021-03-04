package com.timmy.lgsf._06complex_scene._01odd_even_jump;

import com.timmy.common.PrintUtils;

public class _01最大正方形_221 {

    public static void main(String[] args) {
        _01最大正方形_221 demo = new _01最大正方形_221();
//        char[][] matrix = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'},
//        };
        char[][] matrix = {
                {'0'},
        };
        int res = demo.maximalSquare(matrix);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个矩阵，矩阵元素由字符'0'和'1'组成，求矩阵内包含1的最大正方形，并返回面积
     * 2。解题思路
     * 动态规划
     * -原问题拆分成子问题
     * --是否是正方形，主要当当前位置 与（左，上，左上）三个位置是否是正方形相关，如果上诉三个位置是正方形，则当前位置也是正方形
     * -状态方程转移
     * --dp[i][j] 表示从左上方到当前位置存在正方形的边长，dp[i][j]的值由三个方向的最小值+1
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int count = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    }
                    count = Math.max(count, dp[i][j]);
                }
            }
        }
        PrintUtils.print(dp);

        return count * count;
    }

    /**
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     *
     * 示例 1：
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：4
     *
     * 示例 2：
     * 输入：matrix = [["0","1"],["1","0"]]
     * 输出：1
     *
     * 示例 3：
     * 输入：matrix = [["0"]]
     * 输出：0
     *  
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 300
     * matrix[i][j] 为 '0' 或 '1'
     *
     * 链接：https://leetcode-cn.com/problems/maximal-square
     */
}
