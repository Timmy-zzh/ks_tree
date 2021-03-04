package com.timmy.lgsf._04graph._4union_find_set;


public class _02岛屿的最大面积_695 {

    public static void main(String[] args) {
        _02岛屿的最大面积_695 demo = new _02岛屿的最大面积_695();

        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        int result = demo.amxAreaOfIsland(grid);
        System.out.println("result:" + result);
    }

    /**
     * 1。理解题意
     * -求岛屿的最大面积
     * 2。解题思路：使用深度优先遍历方法
     * -遍历二维矩阵，遇到陆地了，则进行遍历四周是否也是陆地，是陆地则岛屿面积加1
     * -遍历过后，并将陆地修改为水
     *
     * @param grid
     * @return
     */
    public int amxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int count = dfs(grid, i, j);
                    System.out.println("count:" + count);
                    res = Math.max(count, res);
                }
            }
        }
        return res;
    }

    int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
    };

    private int dfs(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        int res = 1;
        for (int[] dir : dirs) {
            int newX = dir[0] + x;
            int newY = dir[1] + y;
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                    && grid[newX][newY] == 1) {
                res += dfs(grid, newX, newY);
            }
        }
        return res;
    }


    /**
     * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
     *
     * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
     * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     *
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     *
     * 示例 1:
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
     *
     * 示例 2:
     * [[0,0,0,0,0,0,0,0]]
     * 对于上面这个给定的矩阵, 返回 0。
     *
     * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
     *
     * 链接：https://leetcode-cn.com/problems/max-area-of-island
     */
}
