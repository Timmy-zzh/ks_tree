package com.timmy._00review._04month;

import com.timmy.common.PrintUtils;

public class _19正则表达式匹配_10 {

    public static void main(String[] args) {
        _19正则表达式匹配_10 demo = new _19正则表达式匹配_10();
//        boolean res = demo.isMatch("aa", "a*");
        boolean res = demo.isMatch("aab", "c*a*b");
        System.out.println("res:" + res);
    }

    /**
     * 1.正则表达式匹配
     * 2。动态规划解法
     * -原问题拆分为子问题
     * --字符串s，和p 拆分成从左到右，长度很小的字符串进行匹配，并且新增字符串进行匹配时，可以借助之前的匹配结果，进行推导
     * -状态转移方程式: boolean dp[i][j] 表示字符串s的i位置，与字符串p的j位置的匹配结果
     * --dp[0][0] = true; 表示字符串s与p都为空，所以匹配成功
     * -dp[i][j] 表示的是s的第i个字符=s[i-1]的字符；与 字符p的第j个字符=p[j-1]的字符的匹配结果
     * --情况1：p的第j个字符不是*，
     * ---如果s的第i个字符和p的第j个字符相同，dp[i][j]的值等于 dp[i-1][j-1] (前面字符串匹配的结果 )
     * ---如果s的第i个字符和p的第j个字符不相同，则dp[i][j]=false
     * --情况2：p的第j个字符为*
     * --例如p字符串为(a*)，把他看作一个整体，他可以为空情况，也可以为一个a，两个aa，多个aaa...
     * ---将(a*)看作空字符，dp[i][j] = dp[i][j-2]
     * ---将(a*)看作多个字符，如果s的i个字符和p的第j-1个字符相等，dp[i][j]的值等于 dp[i][j-2] || dp[i-1][j]
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        //s，p字符串的位置
        boolean dp[][] = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        //如果s字符串为空
        for (int i = 1; i <= n; i++) {
            dp[0][i] = n > 1 && p.charAt(i - 1) == '*' && dp[0][i - 2];
        }
        PrintUtils.print(dp);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //判断j位置的 p[j-1]字符是否为*
                if (p.charAt(j - 1) == '*') {
                    //情况1: (a*)为空
                    //情况2：(a*)与s[i-1]相等
                    dp[i][j] = dp[i][j - 2] || (isMatchChar(s, i, p, j - 1) && dp[i - 1][j]);
                } else {
                    //不为*，则判断两两字符判断是否匹配
                    dp[i][j] = dp[i - 1][j - 1] && isMatchChar(s, i, p, j);
                }
            }
        }
        System.out.println("-------");
        PrintUtils.print(dp);
        return dp[m][n];
    }

    private boolean isMatchChar(String s, int i, String p, int j) {
        if (p.charAt(j - 1) == '.') {
            return true;
        } else {
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }

    /**
     * 1。理解题意
     * -输入字符串s，和模式串p，字符串s包含a-z的小写字母，模式串包含所有小写字母和字符* .
     * -其中模式次p中匹配规则如下：
     * --'.' 可以匹配任意单个字符
     * --'*' 可以匹配零个或多个前面的哪一个元素（注意是前面的那个元素）
     * 2。解题思路
     * -注意理解题意模式串p中的字符'*'，可以匹配零个零个或多个前面的那个元素
     * --例如s = "aab" p = "c*a*b"
     * --p中的c* 可以匹配前面的元素c零个，相当于aab 与 a*b 匹配
     * --接着 a* 匹配前面的a元素一个，相当于 aab 与aab匹配
     * 2。1。匹配成功的标准是字符串s和字符串p都遍历到最后一个元素
     * 分三种情况：
     * -1。s[i] 与 p[j] 元素相等，两个字符串同时往后偏移
     * -2。p[j] 等于字符'.' ，可以匹配任意一个s中的元素，两个字符串同时往后偏移
     * -3。p[j+1] 等于字符'*',则有两种情况零个字符，或者n个重复字符（n>0）
     * --第三种情况通过回溯法进行
     */
    public boolean isMatch_v1(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return isMatch(s, p, 0, 0);
    }


    /**
     * 递归调用：
     * 三大要素：
     * 1。入参与返回值
     * 2。终止条件
     * 3。单层递归逻辑
     *
     * @param s 字符串
     * @param p 模式串
     * @param i 字符串匹配位置i
     * @param j 模式串匹配位置j
     * @return 是否匹配成功
     */
    private boolean isMatch(String s, String p, int i, int j) {
//        System.out.println("i:" + s.charAt(i) + " ,j:" + p.charAt(j));
        System.out.println("i:" + i + " ,j:" + j);
        //1。check 校验
        int m = s.length();  // --i
        int n = p.length(); //  --j

        if (j == n) {
            return i == m;
        }

        //2.1. ,p[j+1]不为'*' -- p[j]='.', 或者s[i]==p[j]
        if (j == n - 1 || p.charAt(j + 1) != '*') {
            //i，j后移
            return (i < m) && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))
                    && isMatch(s, p, i + 1, j + 1);
        }

        //2.1.p[j+1]='*' ,分情况回溯
        if (j + 1 < n && p.charAt(j + 1) == '*') {
//            //情况1： a* 整体匹配零个字符 --》i不变，j+2
//            if (!isMatch(s, p, i, j + 2)) {
//                //情况1失败--》继续回溯情况2
//            }

            //循环写法， i不断递增
            /**
             * a* ,可以匹配 ''，'a','aa','aaa...'
             * 后面的情况需要s[i]==p[j]
             * --直到s[i] != p[j]
             */
            while ((i < m) && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
                //后面的情况匹配成功，直接返回
                if (isMatch(s, p, i, j + 2)) {
                    return true;
                }
                i++;
            }
        }
        return isMatch(s, p, i, j + 2);
    }

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *  
     * 示例 1：
     * 输入：s = "aa" p = "a"
     * 输出：false
     * 解释："a" 无法匹配 "aa" 整个字符串。
     *
     * 示例 2:
     * 输入：s = "aa" p = "a*"
     * 输出：true
     * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     *
     * 示例 3：
     * 输入：s = "ab" p = ".*"
     * 输出：true
     * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     *
     * 示例 4：
     * 输入：s = "aab" p = "c*a*b"
     * 输出：true
     * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     *
     * 示例 5：
     * 输入：s = "mississippi" p = "mis*is*p*."
     * 输出：false
     *
     * 提示：
     * 0 <= s.length <= 20
     * 0 <= p.length <= 30
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 保证每次出现字符 * 时，前面都匹配到有效的字符
     *
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching
     */
}
