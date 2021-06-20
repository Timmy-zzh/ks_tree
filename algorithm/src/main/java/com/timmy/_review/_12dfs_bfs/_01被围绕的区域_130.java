package com.timmy._review._12dfs_bfs;

import com.timmy.common.PrintUtils;

public class _01被围绕的区域_130 {

    public static void main(String[] args) {
        _01被围绕的区域_130 demo = new _01被围绕的区域_130();
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
//        char[][] board = {{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
        PrintUtils.print(board);
        demo.solve(board);
    }

    /**
     * 1.理解题意
     * -输入一个二维数组，数组元素由字符X 和 O组成，现在要将所有被X包围起来的O字符进行替换为X
     * 2。解题思路：dfs解法
     * -先从区域的四条边的元素开始遍历，从四个方向开始不断延伸，将元素是O字符的位置替换为其他字符A，
     * --从区域四条边开始遍历 -- 四个方向
     * --已遍历元素，不再重复 -- 设置boolean[][] visited数组
     * -替换完成之后，从新便利，将A字符替换为O，将O字符替换为X，X字符不变
     */
    private static char A = 'A';
    private int[][] dir = {
            {-1, 0},    // 上
            {1, 0},     // 下
            {0, -1},    // 左
            {0, 1}      // 右
    };

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;
//        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    if (board[i][j] == 'O') {
                        dfs(i, j, board);
                    }
                }
            }
        }

        PrintUtils.print(board);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == A) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
        PrintUtils.print(board);
    }

    /**
     * 字符替换 O --》 X
     * 标记已遍历过
     * 遍历四周的元素
     *
     * @param i
     * @param j
     * @param board
     */
    private void dfs(int i, int j, char[][] board) {
        board[i][j] = A;

        for (int w = 0; w < dir.length; w++) {
            int[] ints = dir[w];
            int newX = i + ints[0];
            int newY = j + ints[1];
            if (checkRange(newX, newY, board) && board[newX][newY] == 'O') {
                dfs(newX, newY, board);
            }
        }
    }

    private boolean checkRange(int newX, int newY, char[][] board) {
        int row = board.length;
        int col = board[0].length;
        return 0 <= newX && newX < row && 0 <= newY && newY < col;
    }

    /**
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例 1：
     * 输入：board = [
     * ["X","X","X","X"],
     * ["X","O","O","X"],
     * ["X","X","O","X"],
     * ["X","O","X","X"]]
     *
     * 输出：[
     * ["X","X","X","X"],
     * ["X","X","X","X"],
     * ["X","X","X","X"],
     * ["X","O","X","X"]]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * 示例 2：
     * 输入：board = [["X"]]
     * 输出：[["X"]]
     *
     * 提示：
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 200
     * board[i][j] 为 'X' 或 'O'
     * 链接：https://leetcode-cn.com/problems/surrounded-regions
     */
}
