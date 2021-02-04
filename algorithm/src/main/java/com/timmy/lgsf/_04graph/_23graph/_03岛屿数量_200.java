package com.timmy.lgsf._04graph._23graph;

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

    //广度优先遍历，查找所有字符等于'1'的元素
    private int numIslands(char[][] grid) {
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
                    res += bfs(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    private int bfs(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return 0;
        }
        if (grid[x][y] == '0' || visited[x][y]) {
            visited[x][y] = true;
            return 0;
        }
        visited[x][y] = true;
        bfs(grid, visited, x, y + 1);
        bfs(grid, visited, x, y - 1);
        bfs(grid, visited, x - 1, y);
        bfs(grid, visited, x + 1, y);
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
