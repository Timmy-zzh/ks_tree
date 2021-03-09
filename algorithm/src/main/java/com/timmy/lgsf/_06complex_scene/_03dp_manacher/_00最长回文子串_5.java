package com.timmy.lgsf._06complex_scene._03dp_manacher;

public class _00最长回文子串_5 {

    public static void main(String[] args) {
        _00最长回文子串_5 demo = new _00最长回文子串_5();
        String res = demo.longestPalindrome("babad");
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.中位扩展法
     * -以字符串的某个下标为基准点，同时向左，向右进行判断是否相等，相等的话，则继续向两边扩展
     * --直至到边界或 不相等，并返回回文串的长度
     * -因为回文串存在奇数个和偶数个，所以基准点需要判断以当前下标为中心的奇数回文
     * --还有以当前下标和右侧下标，组合一起为偶数的回文串
     * 3.
     * 4.时间复杂度：O(n^2)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int startIndex = 0, maxLen = 1;
        char[] chars = s.toCharArray();
        int N = chars.length;
        //标识从起始下标到终止下标这段字符是否是回文子串
        for (int i = 0; i < N; i++) {   //长度，从1开始到N
            //求以i为基准的回文串的长度
            int len1 = getPalindromeLen(chars, i, i);
            //偶数回文串
            int len2 = getPalindromeLen(chars, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                startIndex = i - (maxLen - 1) / 2;
                System.out.println("startIndex:" + startIndex + " ,maxLen:" + maxLen);
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    private int getPalindromeLen(char[] chars, int start, int end) {
        while (0 <= start && end < chars.length && chars[start] == chars[end]) {
            start--;
            end++;
        }
        return end - start - 1;
    }

    /**
     * 1.
     * 2.动态规划解法
     * 2.1.动态规划需要解决重复计算的问题，之前的暴力解法中
     * -例如：当需要计算 abcba 是否是回文串时，需要先判断 开始元素a与结束元素a是否相等，
     * --如果相等则需要继续比较里面的bcb是否是回文串
     * 2.2.动态规划思路
     * -原问题拆分为子问题
     * --abcba求否是回文串？ 先判断最外两层是否是相等，如果相等则判断里面的bcb字符是否是回文串
     * -状态转移方程式
     * --定义dp[i][j] 表示字符串从下标i开始，到下标j结束的这段字符串是否是回文串
     * 如果：arr[i]!=arr[j] --》 dp[i][j] = false
     * 如果：arr[i]=arr[j] --》 dp[i][j] = arr[i]=arr[j] && dp[i++][j--]
     * 3.边界与细节问题
     * -先判断长度短的，如果长度为1，i==j，则dp[i][j]=true
     * -如果长度为2，则判断arr[i] == arr[j]
     * -如果长度超过2，则使用动态转移方程式推导
     * -先从长度为1开始，然后下标位置从0
     * -然后长度增长到n
     *
     * @param s
     * @return
     */
    public String longestPalindrome_v2(String s) {
        int startIndex = 0, maxLen = 1;
        char[] chars = s.toCharArray();
        int N = chars.length;
        //标识从起始下标到终止下标这段字符是否是回文子串
        boolean[][] dp = new boolean[N][N];
        for (int len = 1; len <= N; len++) {   //长度，从1开始到N
            for (int i = 0; i + len - 1 < N; i++) {       //i表示起始位置，
                int j = i + len - 1;//j表示终止下标位置
                if (len == 1) {
                    dp[i][j] = true;
                } else if (len == 2) {    //回文串只有两个字符
                    dp[i][j] = chars[i] == chars[j];
                } else {
                    dp[i][j] = chars[i] == chars[j] && dp[i + 1][j - 1];
                }
                //dp[i][j] 也是回文子串，求长度
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    /**
     * 1.理解题意
     * -从一个字符串s中找到长度最长的回文子串
     * 2.解题思路：暴力解法
     * -遍历字符串，从字符串每一个元素作为起始点，起始点后面的元素作为终止点，
     * --期间截取的字符子串，判断是否是回文串
     * -判断字符串是否是回文串--双指针法
     *
     * @param s
     * @return
     */
    public String longestPalindrome_v1(String s) {
        int startIndex = 0, maxLen = 1;
        char[] chars = s.toCharArray();
        int N = chars.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isPalindrome(chars, i, j) && (j - i + 1) > maxLen) {
                    startIndex = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    private boolean isPalindrome(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     * 示例 3：
     * 输入：s = "a"
     * 输出："a"
     *
     * 示例 4：
     * 输入：s = "ac"
     * 输出："a"
     *  
     * 提示：
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     *
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     */
}
