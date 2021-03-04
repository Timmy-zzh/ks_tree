package com.timmy.lgsf._06complex_scene._01odd_even_jump;

public class _02最大矩形_85 {

    public static void main(String[] args) {
        _02最大矩形_85 demo = new _02最大矩形_85();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
        };
//        char[][] matrix = {
//                {'0'},
//        };
        int res = demo.maximalRectangle(matrix);
        System.out.println("res:" + res);
    }

    public int maximalRectangle(char[][] matrix) {

        return 0;
    }

    /**
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     *
     * 示例 1：
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],
     * ["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：6
     * 解释：最大矩形如上图所示。
     *
     * 示例 2：
     * 输入：matrix = []
     * 输出：0
     *
     * 示例 3：
     * 输入：matrix = [["0"]]
     * 输出：0
     *
     * 示例 4：
     * 输入：matrix = [["1"]]
     * 输出：1
     *
     * 示例 5：
     * 输入：matrix = [["0","0"]]
     * 输出：0
     *
     * 提示：
     * rows == matrix.length
     * cols == matrix[0].length
     * 0 <= row, cols <= 200
     * matrix[i][j] 为 '0' 或 '1'
     *
     * 链接：https://leetcode-cn.com/problems/maximal-rectangle
     */
}
