package com.timmy.lgsf._04graph._23graph;

import com.timmy.common.PrintUtils;

public class _02被围绕的区域_130 {

    /**
     * X X X X
     * * X O O X
     * * X X O X
     * * X O X X
     *
     * @param args
     */
    public static void main(String[] args) {
        _02被围绕的区域_130 demo = new _02被围绕的区域_130();
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        demo.solve(board);
    }

    /**
     * 1。理解题意
     * -输入一个二维矩阵，内容包含字符 'X'和'O',需要将X完成包围的'O'字符，替换为X字符
     * -在矩阵边界上的自负O 不可被替换，
     * --可以将边界上的O和与边界O相连的O都进行标记，表示这些O字符不可被替换，其他被X字符包围的中间O字符可以替换
     * 2。解题思路
     * -广度优先遍历
     * --从四条边界上的坐标开始遍历，
     * --如果当前坐标的字符是O，则不断广度遍历与之相连的坐标点--递归，直到与该字符所有相连的O都遍历完。--标记为#
     * -将标记的O现在是边界O相连，不可被替换，所以最后遍历时，
     * --如果是O则替换为X（被包围的），如果被标记为#，则还原为O
     * 3。边界与细节问题
     * -遍历边界-4条边， 不可越界，
     * -边界是O字符，则广度递归遍历
     * -将边界的O字符，更新为#，后期需要还原
     * -最后遍历整个矩阵，将未被标记的O替换为X，标记为#的字符，还原为O
     *
     * @param board
     */
    private void solve(char[][] board) {
        PrintUtils.print(board);
        System.out.println("-----start---");

        int row = board.length;     //行
        if (row == 0) {
            return;
        }
        int col = board[0].length;  //列

        //4条边界 标记

        //遍历行
        for (int i = 0; i < row; i++) {
            bfs(board, i, 0);
            bfs(board, i, col - 1);
        }
        //遍历列
        for (int i = 1; i < col - 1; i++) {
            //第一行，或 最后一行
            bfs(board, 0, i);
            bfs(board, row - 1, i);
        }

        PrintUtils.print(board);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
        System.out.println("--------");
        PrintUtils.print(board);
    }

    private void bfs(char[][] board, int x, int y) {
        //边界控制 ， 是否是字符O
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') {
            return;
        }
        board[x][y] = '#';
        //往四周扩散
        bfs(board, x, y + 1);
        bfs(board, x, y - 1);
        bfs(board, x + 1, y);
        bfs(board, x - 1, y);
    }

    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     *
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
     * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/surrounded-regions
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

}
