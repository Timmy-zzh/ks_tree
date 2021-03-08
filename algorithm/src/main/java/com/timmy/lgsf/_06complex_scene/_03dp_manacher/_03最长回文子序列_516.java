package com.timmy.lgsf._06complex_scene._03dp_manacher;

import com.timmy.common.PrintUtils;


public class _03最长回文子序列_516 {

    public static void main(String[] args) {
        _03最长回文子序列_516 demo = new _03最长回文子序列_516();
        int res = demo.longestPalindromeSubseq("bbbab");
        System.out.println("res:" + res);
    }

    /**
     * 1。深入一个字符串s，查找子序列，子序列的长度最长
     * --字符串中选择不同的字符进行组合，组合后要求是回文串
     * 2。解题思路：动态规划
     * -原问题拆分成子问题
     * --单个字符都是回文串，如果两边的字符相等，则取中间的字符
     * -状态转移方程：dp[i][j] 代表字符串下标i到下标j的回文串的长度
     * --当i==j时，dp[i][j]==1
     * --当头尾两端的字符相等时，dp[i][j]=dp[i+1][j-1]+2
     * --如果头尾两端不想等，则dp[i][j] = max( dp[i+1][j] , dp[i][j-1])
     * 3。边界与细节问题
     * -从后往前遍历
     *
     * @param s
     * @return
     */

    public int longestPalindromeSubseq(String s) {
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[][] dp = new int[N][N];
        for (int i = N - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < N; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
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
     * 输出: 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:
     * "cbbd"
     * 输出: 2
     * 一个可能的最长回文子序列为 "bb"。
     *
     * 提示：
     * 1 <= s.length <= 1000
     * s 只包含小写英文字母
     *
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
     */
}
