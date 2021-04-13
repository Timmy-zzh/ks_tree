package com.timmy._00review._00dp;

import com.timmy.common.PrintUtils;

public class _05最长回文子序列_516 {

    public static void main(String[] args) {
        _05最长回文子序列_516 demo = new _05最长回文子序列_516();
        int res = demo.longestPalindromeSubseq("bbbab");
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，求该字符串的最长回文子序列的长度
     * 2。解题思路
     * 动态规划解法：
     * -状态转移方程式：dp[i][j] 表示字符串下标i到下标j，之间回文子序列的长度
     * --dp[i][i] =1; 默认每个字符都是回文子序列，长度为1
     * --如果s[i] == s[j] , dp[i][j] = dp[i+1][j-1] + 2
     * --否则，dp[i][j] = max(dp[i][j-1] , dp[i+1][j])
     */
    public int longestPalindromeSubseq(String s) {
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        //从后往前便利
        for (int i = N; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        PrintUtils.print(dp);
        return dp[0][N - 1];
    }

    /**
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     * 示例 1:
     * 输入:
     * "bbbab"
     * 输出:
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:
     * "cbbd"
     * 输出:
     * 2
     * 一个可能的最长回文子序列为 "bb"。
     *
     * 提示：
     * 1 <= s.length <= 1000
     * s 只包含小写英文字母
     *
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
     */
}
