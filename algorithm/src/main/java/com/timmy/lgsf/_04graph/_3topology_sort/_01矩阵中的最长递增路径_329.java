package com.timmy.lgsf._04graph._3topology_sort;

import com.timmy.common.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

class _01矩阵中的最长递增路径_329 {

    public static void main(String[] args) {
        _01矩阵中的最长递增路径_329 demo = new _01矩阵中的最长递增路径_329();
        int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1},
        };
        int result = demo.longestIncreasingPath(matrix);
        System.out.println("result:" + result);
    }

    /**
     * 1。理解题意
     * 2。解题思路
     * -拓扑排序算法
     * --根据二维矩阵中各个元素四周的值计算当前元素的入度，从而得到整个元素入度的二维矩阵
     * --再根据元素的入度矩阵，先找到入度为0的元素，然后将入度为0的元素指向的下一个目标元素断开连接，
     * --下一个元素的入度减少1，先遍历入度为0的，然后递归调用，查看最长的深度
     *
     * @param matrix
     * @return
     */
    //入度矩阵
    int[][] inputMatrix;

    public int longestIncreasingPath(int[][] matrix) {
        int res = 0;
        // 1。根据原始数组元素的值求所有元素的入度二维矩阵
        inputMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //判断是否的元素是否 比当前元素值小，如果小，则入度加1
                for (int h = 0; h < dir.length; h++) {
                    int[] ints = dir[h];
                    int newX = i + ints[0];
                    int newY = j + ints[1];
                    if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length
                            && matrix[newX][newY] < matrix[i][j]) {
                        inputMatrix[i][j] = inputMatrix[i][j] + 1;
                    }
                }
            }
        }
        PrintUtils.print(inputMatrix);

        //2。遍历入度二维矩阵，查找所有入度为0的元素
        // 将所有入度为0的元素放入队列中，然后从队列中取出元素，并求该元素四周的下一个目标值，将目标值的入度减少1，
        //如果目标的入度也减少为1，则将该元素入队列，直到所有的元素通过广度优先遍历检索完了，求遍历的层数
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                if (inputMatrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        //3。不断从队列中取出入度为0的元素
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int h = 0; h < size; h++) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                for (int i = 0; i < dir.length; i++) {
                    int[] ints = dir[i];
                    int newX = x + ints[0];
                    int newY = y + ints[1];
                    if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length
                            && matrix[newX][newY] > matrix[x][y]) {
                        inputMatrix[newX][newY] = inputMatrix[newX][newY] - 1;

                        if (inputMatrix[newX][newY] == 0) {
                            queue.offer(new int[]{newX, newY});
                        }
                    }
                }
            }
            res++;
        }

        return res;
    }


    /**
     * {9, 9, 4},
     * {6, 6, 8},
     * {2, 1, 1},
     * 1.理解题意
     * -给出一个二维矩阵，二维矩阵中的元素要求数值从小到大递增，求能够到达的最长路径
     * 2。解题思路
     * -遍历整个二维矩阵，从每个位置出发进行遍历，求从当前元素出发到四周四个方向的下一个元素，其值是否大于当前元素，
     * --如果大于，则递归调用，并返回当前的深度
     * 3。边界与细节问题
     * -四个方向下一个元素的值大于当前元素值，递归，有点像树形结构求深度
     * <p>
     * ====版本二，使用一个二维数组保存已遍历过的元素的深度，然后再次遍历到的时候，就不用深入递归了，可直接从数组中获取返回
     *
     * @param matrix
     * @return
     */
    int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    int[][] pathMatrix;

    public int longestIncreasingPath_v1(int[][] matrix) {
        //遍历二维矩阵每个元素，计算得出每个元素的最长路径
        int res = 0;
        pathMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int path = longestPath(matrix, i, j);
                res = Math.max(path, res);
            }
        }

        return res;
    }

    //递归，得到四个方向的最长路径
    private int longestPath(int[][] matrix, int x, int y) {
        if (pathMatrix[x][y] > 1) {
            return pathMatrix[x][y];
        }
        pathMatrix[x][y] = 1;

        for (int i = 0; i < dir.length; i++) {
            int[] ints = dir[i];
            int newX = x + ints[0];
            int newY = y + ints[1];
            if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length
                    && matrix[newX][newY] > matrix[x][y]) {

                //得到四个方向的最长路径
                int path = longestPath(matrix, newX, newY);
                pathMatrix[x][y] = Math.max(pathMatrix[x][y], path + 1);
            }
        }
        System.out.println("x:" + x + " ,y:" + y + " ,currPosPath:" + pathMatrix[x][y]);
        return pathMatrix[x][y];
    }

    /**
     * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
     *
     * 示例 1：
     * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
     * 输出：4
     * 解释：最长递增路径为 [1, 2, 6, 9]。
     *
     * 示例 2：
     * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
     * 输出：4
     * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
     *
     * 示例 3：
     * 输入：matrix = [[1]]
     * 输出：1
     *
     * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
     */
}
