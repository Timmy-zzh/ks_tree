package com.timmy.lgsf._06complex_scene._04kmp;

import com.timmy.common.PrintUtils;


public class _03重复的子字符串_459 {

    public static void main(String[] args) {
        _03重复的子字符串_459 demo = new _03重复的子字符串_459();
//        boolean res = demo.repeatedSubstringPattern("abcabcabcabc");
//        boolean res = demo.repeatedSubstringPattern("abab");
        boolean res = demo.repeatedSubstringPattern("aa");
//        boolean res = demo.repeatedSubstringPattern("aba");
        System.out.println("res:" + res);
    }

    /**
     * 1.输入一个字符串，判断该字符串是否由他的一个子串重复多次构成？
     * 2。解题思路：kmp-next数组解法
     * -求该字符串的next数组，如果他是由子串重复构成，则有以下两个规则：
     * --next数组的值是00..开头，然后是12..s不断重复
     * --原始字符串的长度是重复子串的x倍
     * ----例如：abcabcabcabc
     * next数组：000123123123
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        System.out.println(s);
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[] next = new int[N];

        //k表示相同前缀的最后一个位置
        int k = 0;
        //i 为后缀的最后一个位置
        //填充next数组的值
        for (int i = 1; i < N; i++) {
            //前后缀字符不相同，根据next数组，向前回溯k位置，回溯到0位置，则停止
            while (k > 0 && chars[k] != chars[i]) {
                k = next[k];
            }
            //如果前后缀字符相等，则k++,前缀k往后移动
            if (chars[k] == chars[i]) {
                k++;
            }
            //更新next数组的值，值表示当前下标i前后缀最长的长度
            next[i] = k;
        }
        PrintUtils.print(next);
        //计算0的位置
        int zoneCount = 0;
        for (int i = 0; i < N; i++) {
            if (next[i] == 0) {
                zoneCount++;
            } else {
                break;
            }
        }
        return next[N - 1] != 0 && N % zoneCount == 0;
    }

    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，
     * 并且长度不超过10000。
     *
     * 示例 1:
     * 输入: "abab"
     * 输出: True
     * 解释: 可由子字符串 "ab" 重复两次构成。
     *
     * 示例 2:
     * 输入: "aba"
     * 输出: False
     *
     * 示例 3:
     * 输入: "abcabcabcabc"
     * 输出: True
     * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
     *
     * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
     */
}
