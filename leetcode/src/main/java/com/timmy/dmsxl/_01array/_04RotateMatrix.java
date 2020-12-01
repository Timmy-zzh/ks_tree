package com.timmy.dmsxl._01array;

import com.timmy.common.PrintUtils;

public class _04RotateMatrix {

    public static void main(String[] args) {
        _04RotateMatrix demo = new _04RotateMatrix();
        int[][] matrix = demo.generateMatrix(3);
        PrintUtils.print(matrix);
    }

    /**
     * 题目59.螺旋矩阵II
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * <p>
     * 示例:
     * <p>
     * 输入: 3 输出:
     * [ [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     * <p>
     * 思路：
     * 轮询次数while为 n/2
     * 轮询开始位置（0，0），（1，1，），（2，2）
     * 每次轮询四条边控制，左闭右开
     */
    private int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int count = 1;
        int offset = 1;  //每次轮询完需要首位减2
        int startX = 0, startY = 0;//开始位置
        int loop = n / 2;   //轮询次数
        int i, j;    //当前遍历使用的标记位置
        while (loop-- > 0) {
            i = startX;
            j = startY;

            //上边界
            for (j = startY; j < startY + n - offset; j++) {
                result[startX][j] = count++;
            }

            //右边界
            for (i = startX; i < startX + n - offset; i++) {
                result[i][j] = count++;
            }

            //下边界
            for (; j > startY; j--) {
                result[i][j] = count++;
            }

            //左边界
            for (; i > startX; i--) {
                result[i][j] = count++;
            }

            //每次轮询位置为对角线位置
            startX++;
            startY++;

            //偏移量减2
            offset -= 2;
        }

        if (n % 2 == 1) {
            result[n / 2][n / 2] = count;
        }

        return result;
    }
}
