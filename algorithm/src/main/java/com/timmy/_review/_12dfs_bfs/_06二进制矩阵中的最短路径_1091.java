package com.timmy._review._12dfs_bfs;

import com.timmy.common.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class _06二进制矩阵中的最短路径_1091 {

    public static void main(String[] args) {
        _06二进制矩阵中的最短路径_1091 demo = new _06二进制矩阵中的最短路径_1091();
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}};
        PrintUtils.print(grid);
        int res = demo.shortestPathBinaryMatrix(grid);
        System.out.println("res:" + res);
    }

    /**
     * 2。bfs解法
     * -使用队列不断将从[0,0]坐标位置八方的位置保存到队列中，每扩大一层检索范围，路径长度增加一
     * -为防止已遍历过的坐标重复便利，使用visited 数组进行标记
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int ans = 0;
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] polls = queue.poll();
                int x = polls[0];
                int y = polls[1];

                System.out.println("x:" + x + " ,y:" + y + " ,ans:" + ans);
                if (x == row - 1 && y == col - 1) {
                    return ans;
                }

                for (int d = 0; d < dir.length; d++) {
                    int[] ints = dir[d];
                    int newX = x + ints[0];
                    int newY = y + ints[1];
                    if (checkRange(newX, newY, grid) && !visited[newX][newY] && grid[newX][newY] == 0) {
                        visited[newX][newY] = true;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 1.理解题意
     * -输入一个二维数组表示路径，二维数组元素由0，1构成，其中0表示道路畅通，1表示墙壁不可通信，现在要从二维矩阵的
     * --左上角移动到右下角，求经过路径的最短长度
     * 2。解题思路
     * 2。1。解法一：dfs + 回溯
     * 2。2。解法二：bfs
     */
    private int res = Integer.MAX_VALUE;
    private int[][] dir = {
            {-1, -1},   //左上
            {-1, 0},    // 上
            {-1, 1},    //右上
            {1, 0},     // 下
            {1, -1},    //左下
            {0, -1},    // 左
            {1, 1},     //右下
            {0, 1}      // 右
    };

    public int shortestPathBinaryMatrix_v1(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;
        dfs(grid, visited, 0, 0, 1);

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(int[][] grid, boolean[][] visited, int x, int y, int path) {
        System.out.println("x:" + x + " ,y:" + y + " ,path:" + path);
        int row = grid.length;
        int col = grid[0].length;
        if (x == row - 1 && y == col - 1) {
            if (path < res) {
                res = path;
            }
            return;
        }

        //当前节点[x,y]的八个方向的选项
        for (int d = 0; d < dir.length; d++) {
            int[] ints = dir[d];
            int newX = x + ints[0];
            int newY = y + ints[1];
            if (checkRange(newX, newY, grid) && !visited[newX][newY] && grid[newX][newY] == 0) {
                visited[newX][newY] = true;
                dfs(grid, visited, newX, newY, path + 1);
                visited[newX][newY] = false;
            }
        }
    }

    private boolean checkRange(int newX, int newY, int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        return 0 <= newX && newX < row && 0 <= newY && newY < col;
    }

    /**
     * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
     * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，
     * 该路径同时满足下述要求：
     *
     * 路径途经的所有单元格都的值都是 0 。
     * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
     * 畅通路径的长度 是该路径途经的单元格总数。
     *
     * 示例 1：
     * 输入：grid = [[0,1],[1,0]]
     * 输出：2
     *
     * 示例 2：
     * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
     * 输出：4
     *
     * 示例 3：
     * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
     * 输出：-1
     *  
     * 提示：
     * n == grid.length
     * n == grid[i].length
     * 1 <= n <= 100
     * grid[i][j] 为 0 或 1
     * 链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
     */
}
