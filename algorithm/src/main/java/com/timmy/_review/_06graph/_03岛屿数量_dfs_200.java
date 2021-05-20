package com.timmy._review._06graph;

public class _03岛屿数量_dfs_200 {


    public static void main(String[] args) {
        _03岛屿数量_dfs_200 demo = new _03岛屿数量_dfs_200();
//        int[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}};
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int res = demo.numIslands(grid);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个由陆地（1）和水（0）组成的二维区域，其中陆地在上下左右四个方向相连为一个连接的岛屿，
     * --求二维区域的岛屿数量
     * 2。解题思路：
     * 深度优先遍历-dfs
     * -visited表示是否已经遍历过了
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 上下左右
    int res = 0;
    int row;
    int col;

    public int numIslands(char[][] grid) {
        row = grid.length;
        col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    System.out.println("i:" + i + " ,j:" + j);
                    res++;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        System.out.println("dfs-----x:" + x + " ,y:" + y);
        visited[x][y] = true;
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (checkRange(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                dfs(grid, visited, newX, newY);
            }
        }
    }

    public boolean checkRange(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
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
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     */
}
