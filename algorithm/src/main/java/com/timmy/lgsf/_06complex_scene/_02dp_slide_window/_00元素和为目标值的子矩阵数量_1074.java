package com.timmy.lgsf._06complex_scene._02dp_slide_window;

import com.timmy.common.PrintUtils;

import java.util.HashMap;
import java.util.Map;

public class _00元素和为目标值的子矩阵数量_1074 {

    public static void main(String[] args) {
        _00元素和为目标值的子矩阵数量_1074 demo = new _00元素和为目标值的子矩阵数量_1074();
        int target = 0;
        int[][] matrix = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        int res = demo.numSubmatrixSumTarget(matrix, target);
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.滑动窗口解法
     * 2。1。将备忘录继续前置准备好，等需要的时候直接获取使用
     * -先获取每行的行前缀，然后遍历选中两条不同的起始列和结束列，在这两条列之间，求子矩阵的元素和
     * --子矩阵的元素和，是每行的元素和
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int count = 0;
        //保存行前缀 元素和
        int[][] opt = new int[matrix.length][matrix[0].length];

        //状态转移方程
        //opt[i][j] = opt[i][j-1]+matrixpi][j];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                opt[i][j] = (j == 0 ? 0 : opt[i][j - 1]) + matrix[i][j];
            }
        }

        int row = matrix.length;
        int col = matrix[0].length;
        //起始列，终止列
        for (int l = 0; l < col; l++) {
            for (int r = l; r < col; r++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                //子矩阵元素和
                int matrixSum = 0;
                //遍历行
                for (int m = 0; m < row; m++) {
                    System.out.println("l:" + l + " ,r:" + r + " ,m:" + m);
                    //当前行的元素和
                    int currRowSum = opt[m][r] - (l == 0 ? 0 : opt[m][l - 1]);
                    matrixSum += currRowSum;
//                    System.out.println("Map=====:" + map.toString());
//                    System.out.println("matrixSum-----:" + matrixSum + " ,matrixSum - target:" + (matrixSum - target));
                    Integer integer = map.getOrDefault(matrixSum - target, 0);
                    count += integer;
//                    System.out.println("integer:" + integer + " ,count:" + count);
                    map.put(matrixSum, map.getOrDefault(matrixSum, 0) + 1);
//                    if (matrixSum == target) {
//                        count++;
//                    }
                }
                System.out.println("Map>>>>>>>>>>>>:" + map.toString());
            }
        }

        return count;
    }

    /**
     * 1.
     * 2.动态规划解法
     * 2。1。之前的暴力解法，在确定了起始坐标后，不同的终止坐标，每次都需要使用两层for循环进行计算，
     * -而且存在大量的覆盖计算，可以使用动态规划备忘录的方式进行状态转移，目的就是为了减少重复计算
     * 2。2。动态规划
     * -原问题拆分为子问题：
     * --每次确定了起始坐标时，都定义一个备忘录opt[m][n],表示从其实坐标到坐标[m,n]的子矩阵元素和
     * -状态转移方程式
     * --opt[m,n] = opt[m,n-1] + opt[m-1,n] - opt[m-1,n-1] + matrix[m,n]
     * 3。边界和细节问题
     * -起始和终止坐标确定
     * -同行与同列问题处理
     * -opt的状态计算注意
     * 4。复杂度
     * -时间：O(m^2 * n^2)
     * -空间: O(m*n)
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget_v2(int[][] matrix, int target) {
        int count = 0;
        //起始坐标
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //终止坐标
                int[][] opt = new int[matrix.length][matrix[i].length];
                for (int m = i; m < matrix.length; m++) {
                    for (int n = j; n < matrix[i].length; n++) {

                        if (m == i && n == j) {
                            opt[m][n] = matrix[m][n];
                        } else if (m == i) {
                            //行相等
                            opt[m][n] = opt[m][n - 1] + matrix[m][n];
                        } else if (n == j) {
                            //列相等
                            opt[m][n] = opt[m - 1][n] + matrix[m][n];
                        } else {
                            opt[m][n] = opt[m][n - 1] + opt[m - 1][n] - opt[m - 1][n - 1] + matrix[m][n];
                        }
                        if (opt[m][n] == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 1.理解题意
     * -输入一个二维矩阵，查找其中的子矩阵，子矩阵的元素和为目标值target，找出符合条件的子矩阵个数
     * 2。解题思路
     * 2。1。暴力解法
     * -确定起始点[i,j] 确定终止点[m,n] , 再求起始点到终止点这个矩阵的元素和[i~m, j~n]，
     * --判断元素和是否等于目标值
     * 3。边界和细节问题
     * 4.复杂度
     * -时间：Q(m^3 * n^3)
     * -空间：O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget_v1(int[][] matrix, int target) {
        int count = 0;
        //起始坐标
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //终止坐标
                for (int m = i; m < matrix.length; m++) {
                    for (int n = j; n < matrix[i].length; n++) {

                        //计算子矩阵的大小
                        int matrixSum = 0;
                        for (int x = i; x <= m; x++) {
                            for (int y = j; y <= n; y++) {
                                matrixSum += matrix[x][y];
                            }
                        }
                        System.out.println("i:" + i + " ,j:" + j + " -- m:" + m + " ,n:" + n);
                        System.out.println("sum:" + matrixSum);
                        if (matrixSum == target) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
     * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
     * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     *
     * 示例 1：
     * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
     * 输出：4
     * 解释：四个只含 0 的 1x1 子矩阵。
     *
     * 示例 2：
     * 输入：matrix = [[1,-1],[-1,1]], target = 0
     * 输出：5
     * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
     *  
     * 提示：
     * 1 <= matrix.length <= 300
     * 1 <= matrix[0].length <= 300
     * -1000 <= matrix[i] <= 1000
     * -10^8 <= target <= 10^8
     *
     * 链接：https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target
     */
}
