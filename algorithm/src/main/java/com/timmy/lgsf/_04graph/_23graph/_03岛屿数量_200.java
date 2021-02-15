package com.timmy.lgsf._04graph._23graph;

import java.util.LinkedList;
import java.util.Queue;

public class _03岛屿数量_200 {

    public static void main(String[] args) {
        _03岛屿数量_200 demo = new _03岛屿数量_200();
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}};
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int result = demo.numIslands(grid);
        System.out.println("result:" + result);
    }

    /**
     * 深度优先遍历
     *
     * @param grid
     * @return
     */
    private int numIslands(char[][] grid) {
        int islandNums = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    islandNums++;
                }
            }
        }
        return islandNums;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        for (int[] dir : dirs) {
            int newX = dir[0] + i;
            int newY = dir[1] + j;
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                    && grid[newX][newY] == '1') {
                grid[newX][newY] = '0';
                dfs(grid, newX, newY);
            }
        }
    }

    /**
     * 1。
     * 2。解题思路
     * 广度优先：
     * -遍历二维矩阵，找到陆地元素，然后将该陆地元素位置入队列，
     * --再将该陆地位置的四周陆地位置入队列，直到该区域所有的陆地都遍历完了，则当前陆地区域加1
     * 3。边界与细节问题
     * -遍历找到第一块陆地元素位置
     * -找到了陆地位置入队列，继续找四周陆地，也入队列,出队列后，该陆地位置需要反转为水，则可以避免二次检索
     * -查找四周陆地，使用二维数组，
     * -队列每搜索完一块区域，则表示查找了一块陆地区域
     *
     * @param grid
     * @return
     */
    int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
    };

    private int numIslands_v2(char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int islandNums = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] ints = queue.poll();
                        for (int[] dir : dirs) {
                            int newX = dir[0] + ints[0];
                            int newY = dir[1] + ints[1];
                            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                                    && grid[newX][newY] == '1') {
                                grid[newX][newY] = '0';
                                queue.add(new int[]{newX, newY});
                            }
                        }
                    }
                    islandNums++;
                }
            }
        }
        return islandNums;
    }

    //广度优先遍历，查找所有字符等于'1'的元素
    private int numIslands_v1(char[][] grid) {
        int res = 0;
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j]) {
                    res += bfs_v1(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    private int bfs_v1(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return 0;
        }
        if (grid[x][y] == '0' || visited[x][y]) {
            visited[x][y] = true;
            return 0;
        }
        visited[x][y] = true;
        bfs_v1(grid, visited, x, y + 1);
//        bfs(grid, visited, x, y - 1);
//        bfs(grid, visited, x - 1, y);
        bfs_v1(grid, visited, x + 1, y);
        return 1;
    }


    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 示例 1：
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     *
     * 示例 2：
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     */
}
