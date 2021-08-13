package com.timmy.practice._08month;

import com.timmy.common.PrintUtils;

public class _12排序矩阵查找 {

    public static void main(String[] args) {
        _12排序矩阵查找 demo = new _12排序矩阵查找();
        int[][] matirx = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        PrintUtils.print(matirx);
        boolean res = demo.searchMatrix(matirx, 9);
        System.out.println("res:" + res);
    }

    /**
     * 2.双指针解法
     * -从二维矩阵的右上角位置元素开始判断与目标值的大小
     * --如果大于目标值，则当前位置的下面区域肯定都是大于目标值的，所有需要往左移
     * --如果小于目标值，则当前位置的左边区域肯定都市小于目标值的，所以需要往下移动
     * -直到找到目标值相等的元素，或者遍历到左下角
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        if (matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length, col = matrix[0].length;
        int currRow = 0;//当前行
        int currCol = col - 1;//   当前列

        while (currRow < row && currCol >= 0) {
            int currValue = matrix[currRow][currCol];
            if (currValue == target) {
                return true;
            }
            if (currValue > target) {
                currCol--;
            } else {
                currRow++;
            }
        }
        return false;
    }

    /**
     * 1。理解题意
     * -输入一个二维矩阵和目标值，二维矩阵的每行每列按升序排列，判断目标值是否在矩阵中，出现返回true
     * 2。解题思路
     * 2。1。暴力解法
     * -两层for循环遍历矩阵中每个元素，然后与目标值进行比较，相等返回true
     * 2。2。分治算法
     * -从左上到右下的遍历对角线上的元素
     * --如果元素小于目标值，则左上角区域的元素都是小于目标值的，如果对角线上的元素大于目标值，则右下角区域的元素都是大于目标值的
     * --所以目标值存在于左下角和右上角区域，继续递归从上面两个区域中进行目标值的查找
     */
    public boolean searchMatrix_v1(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        if (matrix[0].length == 0) {
            return false;
        }

        return searchMatrixSub(matrix, target, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    /**
     * 1。先找到区域的对角线，然后遍历对角线上的元素，进行判断
     * 下面四个参数是检索区域范围
     *
     * @param startRow 开始行
     * @param startCol 开始列
     * @param endRow   结束行
     * @param endCol   结束列
     */
    private boolean searchMatrixSub(int[][] matrix, int target, int startRow, int startCol, int endRow, int endCol) {
        if (startRow > endRow || startCol > endCol) {
            return false;
        }
        //先判断第一个元素
        if (matrix[startRow][startCol] > target) {
            return false;
        }
        //对角线个数
        int diagonal = Math.min(endRow - startRow + 1, endCol - startCol + 1);
        for (int i = 0; i < diagonal; i++) {
            //获取对角线上的元素，并进行比较，如果当前值小于目标值，而下一个元素大于目标值，则需要进行递归左下和右上两个区域进行查找目标值
            if (matrix[startRow + i][startCol + i] == target) {
                return true;
            }
            //i == diagonal - 1 查找到对角线最后一个元素了
            if (i == diagonal - 1 || matrix[startRow + i + 1][startCol + i + 1] > target) {
                return searchMatrixSub(matrix, target, startRow, startCol + i + 1, startRow + i, endCol) ||
                        searchMatrixSub(matrix, target, startRow + i + 1, startCol, endRow, startCol + i);
            }
        }
        return false;
    }

    /**
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     *
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     *
     * 链接：https://leetcode-cn.com/problems/sorted-matrix-search-lcci
     */
}
