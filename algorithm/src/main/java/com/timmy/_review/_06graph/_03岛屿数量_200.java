package com.timmy._review._06graph;

import com.timmy.common.PrintUtils;

public class _03岛屿数量_200 {


    public static void main(String[] args) {
        _03岛屿数量_200 demo = new _03岛屿数量_200();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int res = demo.numIslands(grid);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个由陆地（1）和水（0）组成的二维区域，其中陆地在上下左右四个方向相连为一个连接的岛屿，
     * --求二维区域的岛屿数量
     * 2。解题思路：并查集
     * -初始化并查集，刚开始并查集的集合的个数为陆地个数
     * -遍历二维网格元素，当节点为陆地时，以该节点为中心，与四周元素节点进行合并操作，最后求集合的个数
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 上下左右

    public int numIslands(char[][] grid) {
        init(grid);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    //四周
                    for (int[] dir : dirs) {
                        int newX = dir[0] + i;
                        int newY = dir[1] + j;
                        if (checkRange(newX, newY) && grid[newX][newY] == '1') {
                            union(i, j, newX, newY);
                        }
                    }
                }
            }
        }

        PrintUtils.print(parent);
        return count;
    }

    public boolean checkRange(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    private int row;
    private int col;
    //集合个数
    int count = 0;
    //某个节点的父节点
    int[][] parent;

    public void init(char[][] grid) {
        row = grid.length;
        col = grid[0].length;
        parent = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    parent[i][j] = getIndex(i, j);
                }
            }
        }
        PrintUtils.print(parent);
        System.out.println(count);
    }

    /**
     * 根据二维矩阵的行列下标值，求特殊定位
     * 不同的下标组合，返回不同的结果
     */
    public int getIndex(int i, int j) {
        return i * row + j;
    }

    /**
     * 合并x，y元素
     * -先获取x，y的祖先节点
     * -判断x，y的祖先节点是否相同
     * -不相同则合并，其中x节点的祖先节点指向y的祖先节点
     */
    public void union(int i, int j, int x, int y) {
        int xP = find(i, j);
        int yP = find(x, y);
        if (xP != yP) {
            parent[i][j] = -1;
            parent[x][y] = -1;
            count--;
        }
    }

    /**
     * 找到x元素的祖先节点
     */
    public int find(int x, int y) {
        return parent[x][y];
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
