package com.timmy._00review._03month;

import com.timmy.common.PrintUtils;

public class _0323二维区域和检索_矩阵不可变_304 {

    public static void main(String[] args) {
        _0323二维区域和检索_矩阵不可变_304 demo = new _0323二维区域和检索_矩阵不可变_304();

        demo.init();
//        int res = demo.sumRegion(2, 1, 4, 3);
//        int res = demo.sumRegion(1, 1, 2, 2);
        int res = demo.sumRegion(1, 2, 2, 4);
        System.out.println("res:" + res);
    }

    private int[][] dp;

    private void init() {
        int row = matirx.length;
        int col = matirx[0].length;
        dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = j == 0 ? matirx[i][j] : dp[i][j - 1] + matirx[i][j];
            }
        }
        PrintUtils.print(dp);
    }

    private int[][] matirx = new int[][]{
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}};

    /**
     * 1.理解题意
     * -输入二维矩阵，选择子矩阵区域，求子矩阵的元素和大小
     * --输入子矩阵的左上角，和右下角的坐标
     * 2。解题思路
     * -使用备忘录二维数组dp[i][j] 表示第i行中，0到j列-[0,j]范围的元素和
     * --子矩阵的元素和，可以转换为每行的元素和
     *
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; i++) {
            //每行的元素和
            int rowSum = dp[i][col2] - (col1 == 0 ? 0 : dp[i][col1 - 1]);
            res += rowSum;
            System.out.println("rowSum:" + rowSum);
        }
        return res;
    }

    /**
     * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
     * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
     *  
     * 示例：
     * 给定 matrix = [
     *   [3, 0, 1, 4, 2],
     *   [5, 6, 3, 2, 1],
     *   [1, 2, 0, 1, 5],
     *   [4, 1, 0, 1, 7],
     *   [1, 0, 3, 0, 5]
     * ]
     *
     * sumRegion(2, 1, 4, 3) -> 8
     * sumRegion(1, 1, 2, 2) -> 11
     * sumRegion(1, 2, 2, 4) -> 12
     *  
     * 提示：
     *
     * 你可以假设矩阵不可变。
     * 会多次调用 sumRegion 方法。
     * 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
     *
     * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
     */
}
