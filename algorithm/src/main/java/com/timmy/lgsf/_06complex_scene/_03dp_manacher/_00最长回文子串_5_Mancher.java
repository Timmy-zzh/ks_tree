package com.timmy.lgsf._06complex_scene._03dp_manacher;

public class _00最长回文子串_5_Mancher {

    public static void main(String[] args) {
        _00最长回文子串_5_Mancher demo = new _00最长回文子串_5_Mancher();
        String res = demo.longestPalindrome("babad");
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.Mancher 马拉车算法
     * 目的也是一样，将后续需要用到的计算量，前期先计算好了，后面循环时直接使用
     * 2.1.
     * -在原始字符串中插入字符，是的每次中位数计算时，都能得到奇位数的最长回文子串
     * -再使用一维数组作为备忘录
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int startIndex = 0, maxLen = 1;
        char[] chars = s.toCharArray();
        int N = chars.length;
        return s.substring(startIndex, startIndex + maxLen);
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
