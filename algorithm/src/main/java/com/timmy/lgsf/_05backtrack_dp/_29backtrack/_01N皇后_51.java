package com.timmy.lgsf._05backtrack_dp._29backtrack;

import java.util.ArrayList;
import java.util.List;

public class _01N皇后_51 {
    public static void main(String[] args) {
        _01N皇后_51 demo = new _01N皇后_51();
        List<List<String>> lists = demo.solveNQueens(4);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("------");
//            PrintUtils.printStr(list);
        }
    }

    /**
     * 1.理解题意
     * -输入n为数组大小n*n，该数组表示一个棋盘，在棋盘上可以放置皇后，要求放置皇后的位置 横，列，两条对角线上都不能有其他皇后
     * -最后将放置好皇后的棋盘返回，打印出来
     * 2。解题思路
     * -从第一行开始，第一行有n列，可以遍历这n列，进行皇后的摆放，第一行摆放后，
     * --进行第二行的摆放，第二行的皇后摆放需要判断四个方位（行列，两对角线）判断是否能够摆放，能够摆放则继续递归调用进行第三行的皇后摆放
     * -能够进行到最后一行，则说明n个皇后都已经摆放好了，将当前棋盘打印出来，并存放到res数组中
     * 3。边界与细节问题
     * -行列，对角线 上皇后的位置判断
     * -一些皇后在前面几行的位置上摆放好后，后面的皇后可能就没有满足条件的位置，但是数据又被污染了
     * --所以需要复制一份污染前的数组使用
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        //1.创建默认棋盘
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        //2。在棋盘上摆放，从第1行开始摆放
        backTrack(res, chess, n, 0);
        return res;
    }

    /**
     * @param res   当摆放到第n行时，需要将摆放好皇后的棋盘，保存到List中
     * @param chess 一比方了row行皇后的期盼
     * @param n     棋盘行列
     * @param row   当前摆放的是第row行
     */
    private void backTrack(List<List<String>> res, char[][] chess, int n, int row) {
//        System.out.println("backTrack:----" + row);
        if (row == n) {
            //将棋盘保存到res中
            List<String> list = generateList(chess);
            res.add(list);
            return;
        }

        //遍历列，判断第row行和列i 上是否可以摆放皇后
        for (int col = 0; col < n; col++) {
            if (valid(chess, row, col)) {
                //TODO 注意原始数组chess已经被污染
//                chess[row][col] = 'Q';
                //复制一份
                char[][] temp = copy(chess);
                //继续判断下一行摆放皇后的位置
                temp[row][col] = 'Q';
                backTrack(res, temp, n, row + 1);
            }
        }
    }

    //当前位置[row,col] 是否可以摆放皇后，四个判断条件：行列，对角线
    private boolean valid(char[][] chess, int row, int col) {
        int n = chess.length;
        //判断 垂直方向
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        //对角线-左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //对角线-右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private char[][] copy(char[][] chess) {
        int n = chess.length;
        char[][] res = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = chess[i][j];
            }
        }
        return res;
    }

    //棋盘棋子转成list集合
    private List<String> generateList(char[][] chess) {
        List<String> res = new ArrayList<>();
        StringBuilder sb;
        for (int i = 0; i < chess.length; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < chess[i].length; j++) {
                sb.append("\t" + chess[i][j]);
//                sb.append(chess[i][j]);
            }
            res.add(sb.toString());
        }
        return res;
    }

    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *  
     * 示例 1：
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：[["Q"]]
     *
     * 链接：https://leetcode-cn.com/problems/n-queens
     */
}
