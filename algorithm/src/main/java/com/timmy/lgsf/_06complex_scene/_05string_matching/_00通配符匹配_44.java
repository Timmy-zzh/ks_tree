package com.timmy.lgsf._06complex_scene._05string_matching;

import com.timmy.common.PrintUtils;

public class _00通配符匹配_44 {

    public static void main(String[] args) {
        _00通配符匹配_44 demo = new _00通配符匹配_44();
//        boolean res = demo.isMatch("adceb", "*a*b");
//        boolean res = demo.isMatch("acdcb", "a*c?b");
        boolean res = demo.isMatch("", "******");
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串s和匹配串p，判断两个字符串是否完全匹配？
     * --其中s，p都可能为空，s只包含小写字母，p包含小写字母和通配符?和*
     * --其中？可以匹配单个字符，*可以匹配任意个字符串（包括空字符串）
     * 2。解题思路：动态规划
     * -原问题拆分成子问题
     * --字符串s不变，匹配串p长度不同情况下和字符串s的匹配情况
     * -状态转移方程
     * --因为s，p都可能存在空字符串的情况，所以状态数组dp需要考虑空字符的情况
     * --boolean dp[i][j]的值表示字符串第i个字符到匹配串第j个字符是否匹配
     * 分成不同情况：
     * - dp[i][j] = dp[i-1][j-1];   当s[i] == p[j]
     * - dp[i][j] = dp[i-1][j-1];   当p[j] == '?'
     * - dp[i][j] = dp[i][j-1] || dp[i-1][j];  当p[j]=='*' 当模式串字符为*时，只要前面的字符存在匹配即可
     * - 其他为不匹配
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        System.out.println(s);
        System.out.println(p);
        //字符串转成字符数组
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int m = sChars.length;
        int n = pChars.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        //因为第一列是字符串s的比较，默认都为false，第一行为匹配串p的比较
        for (int i = 0; i < n; i++) {
            if (pChars[i] == '*') {    //匹配串为*字符才赋值为true，否则全为false，直接退出循环
                dp[0][i + 1] = true;
            } else {
                break;
            }
        }
        PrintUtils.print(dp);

        //两层for循环，进行字符串与匹配串比较
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sChars[i - 1] == pChars[j - 1] || pChars[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChars[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        System.out.println("-----");
        PrintUtils.print(dp);
        return dp[m][n];
    }

    /**
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     *
     * 说明:
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     *
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     *
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释: '*' 可以匹配任意字符串。
     *
     * 示例 3:
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     *
     * 示例 4:
     * 输入:
     * s = "adceb"
     * p = "*a*b"
     * 输出: true
     * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     *
     * 示例 5:
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输出: false
     *
     * 链接：https://leetcode-cn.com/problems/wildcard-matching
     */
}
