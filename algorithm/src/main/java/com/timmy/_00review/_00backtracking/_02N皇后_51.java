package com.timmy._00review._00backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 回溯算法：经典题目
 * N皇后
 */
public class _02N皇后_51 {
    public static void main(String[] args) {
        _02N皇后_51 demo = new _02N皇后_51();
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
     * 2。解题思路：回溯算法-优化解法
     * -解题思路还是之前的，从第一行开始，循环每一列，判断当前位置【row,col】是否可以摆放皇后
     * -而判断是否可以摆放皇后，有四个条件，行，列，两条对角线
     * --根据特性，行可以不用考虑，使用三个Set集合
     * ---Set<Integer> columns 表示在那几列上存在皇后，存在则当前列不满足条件
     * ---Set<Integer>  diagonal1，对角线1-左上，有个特点row-col 等于的值都不相同 [-3,3]
     * ---Set<Integer>  diagonal2，对角线2-右上，有个特点row+col 等于的值都不相同 [0，7]
     * -所以在判断的时候只要根据这三个集合进行判断
     * --最重要的是回溯，递归调用结束后，需要将添加的值remove
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonal1 = new HashSet<>();
        Set<Integer> diagonal2 = new HashSet<>();

        //创建默认棋盘
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '*';
            }
        }
        //回溯
        backtrack(res, chess, n, 0, columns, diagonal1, diagonal2);
        return res;
    }

    /**
     * @param res
     * @param chess
     * @param n
     * @param row       当前遍历到的第几行
     * @param columns   三个方向的集合
     * @param diagonal1
     * @param diagonal2
     */
    private void backtrack(List<List<String>> res, char[][] chess, int n, int row,
                           Set<Integer> columns, Set<Integer> diagonal1, Set<Integer> diagonal2) {
        if (n == row) {
            //保存
            res.add(generateList(chess));
        } else {
            //遍历列，判断
            for (int col = 0; col < n; col++) {
                if (columns.contains(col)) {
                    continue;
                }
                int diff1 = row - col;
                if (diagonal1.contains(diff1)) {
                    continue;
                }
                int diff2 = row + col;
                if (diagonal2.contains(diff2)) {
                    continue;
                }

                //
                columns.add(col);
                diagonal1.add(diff1);
                diagonal2.add(diff2);

                char[][] temp = copy(chess);
                //继续判断下一行摆放皇后的位置
                temp[row][col] = 'Q';
                backtrack(res, temp, n, row + 1, columns, diagonal1, diagonal2);

                columns.remove(col);
                diagonal1.remove(diff1);
                diagonal2.remove(diff2);
            }
        }
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
