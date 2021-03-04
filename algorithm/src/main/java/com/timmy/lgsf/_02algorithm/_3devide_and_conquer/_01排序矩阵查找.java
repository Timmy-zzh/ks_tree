package com.timmy.lgsf._02algorithm._3devide_and_conquer;

public class _01排序矩阵查找 {

    public static void main(String[] args) {
        _01排序矩阵查找 demo = new _01排序矩阵查找();
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        boolean result = demo.searchMatrix(matrix, 14);
        System.out.println("result:" + result);
    }

    /**
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     * <p>
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     * <p>
     * 链接：https://leetcode-cn.com/problems/sorted-matrix-search-lcci
     */

    /**
     * 2。解题思路二
     * -根据行列数据都是升序特性，从右上角开始遍历数据
     * --如果数据大于target，则往左移一位
     * --如果数据小于target，则往下移动一位
     * 3。边界问题
     * -range范围控制
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        //行
        int row = matrix.length;
        //列
        int column = matrix[0].length;

        int m = 0, n = column - 1;
        while (m < row && n >= 0) {
            System.out.println("curr:" + matrix[m][n]);
            if (matrix[m][n] == target) {
                return true;
            } else if (matrix[m][n] > target) {
                n--;    //列左移
            } else {
                m++;    //行下移
            }
        }
        return false;
    }

    /**
     * 1.理解题意
     * -矩阵（二维数组）且行列的数据是升序，在矩阵中查找目标值
     * 2。解题思路
     * -暴力解法：两层for循环，查找元素与target是否相等
     * -根据数据的升序特性，通过查找比较对角线（从左上到右下）的元素，
     * --如果对角线的元素小于target值，对角线元素继续往下遍历，
     * --如果找到大于target的值，则目标值在左下，和右上两块区间，通过分治的思想进行小区域的检索
     */
    public boolean searchMatrix_v1(int[][] matrix, int target) {
        //行
        int row = matrix.length;
        //列
        int column = matrix[0].length;
        return realSearch(matrix, target, 0, 0, row - 1, column - 1);
    }

    /**
     * 注意细节：当范围矩阵是长方形的时候，如果按照对角线的元素进行切割，
     *
     * @param matrix
     * @param target
     * @param startRow    开始行
     * @param startColumn 开始列
     * @param endRow      结束行
     * @param endColumn   结束列
     * @return
     */
    private boolean realSearch(int[][] matrix, int target, int startRow, int startColumn, int endRow, int endColumn) {
        if (startRow > endRow || startColumn > endColumn) {
            return false;
        }
        //如果最小值都小于target，则直接返回
        if (matrix[startRow][startColumn] > target) {
            return false;
        }
        System.out.println("startRow:" + startRow + " ,startColumn:" + startColumn + " ,endRow:" + endRow + " ,endColumn:" + endColumn);
        //对角线
        int diagonal = Math.min(endRow - startRow, endColumn - startColumn);
        for (int i = 0; i <= diagonal; i++) {
            if (matrix[startRow + i][startColumn + i] == target) {
                return true;
            }
            if (i == diagonal || matrix[startRow + i + 1][startColumn + i + 1] > target) {
                //右上
                return realSearch(matrix, target, startRow, startColumn + i + 1, startRow + i, endColumn) ||
                        //左下
                        realSearch(matrix, target, startRow + i + 1, startColumn, endRow, startColumn + i);
            }
        }
        return false;
    }
}
