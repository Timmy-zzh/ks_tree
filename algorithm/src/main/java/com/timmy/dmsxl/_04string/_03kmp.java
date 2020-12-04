package com.timmy.dmsxl._04string;

import com.timmy.common.PrintUtils;

public class _03kmp {

    public static void main(String[] args) {
        _03kmp demo = new _03kmp();
//        int result = demo.strStr("aabaabaafa", "aabaaf");
//        System.out.println("result:" + result);

        boolean result = demo.repeatedSubstringPattern("abcabcabcabc");
        System.out.println("result:" + result);
    }

    /**
     * 题目：28. 实现 strStr()
     * 实现 strStr() 函数。
     * <p>
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出
     * needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * <p>
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * <p>
     * 说明:
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     * <p>
     * ---?在文本串：aabaabaafa中查找是否出现过一个模式串：aabaaf
     * 解题思路：
     * 1。构建模式串的next数组，
     * 2。然后主串与模式串进行，比较，当字符不匹配的时候，通过next前缀表找到重新匹配的位置
     */
    public int strStr(String haystack, String needle) {
        int[] next = new int[needle.length()];
        getNext(needle, next);

        int j = -1;
        char[] s = haystack.toCharArray();  //文本串
        char[] t = needle.toCharArray();    //模式串
        for (int i = 0; i < s.length; i++) {
            while (j >= 0 && s[i] != t[j + 1]) {    //不匹配
                j = next[j];
            }
            if (s[i] == t[j + 1]) {     //文本串与模式串匹配
                j++;
            }
            if (j == t.length - 1) {  //模式串到末尾
                return (i - t.length + 1);
            }
        }
        return -1;
    }

    /**
     * 获取模式串的前缀表next数组
     * aabaaf
     * - 长度为前1个字符的子串a,最长相同前后缀的长度为0       -1
     * - 长度为前2个字符的子串aa，最长相同前后缀长度为1       0
     * - 长度为前3个字符的子串aab，最长相同前后缀长度为0      -1
     * - 长度为前4个字符的子串aaba，最长相同前后缀长度为1     0
     * - 长度为前5个字符的子串aabaa，最长相同前后缀长度为2    1
     * - 长度为前6个字符的子串aabaaf，最长相同前后缀长度为0   -1
     */
    private void getNext(String needle, int[] next) {
        char[] chars = needle.toCharArray();
        int j = -1;     //j指向前缀终止位置,i指向后缀终止位置
        next[0] = -1;   //长度为1的子串，默认相同前后缀长度为-1
        for (int i = 1; i < chars.length; i++) {
            while (j >= 0 && chars[i] != chars[j + 1]) {  //字符不想等，需要回溯
                j = next[j];
            }
            if (chars[i] == chars[j + 1]) {    //字符相等，j长度++
                j++;
            }
            next[i] = j;
//            System.out.println("==i:" + i);
//            PrintUtils.print(next);
        }
    }


    /**
     * 题目459.重复的子字符串
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     * <p>
     * 示例 1:
     * 输入: "abab"
     * 输出: True
     * 解释: 可由子字符串 "ab" 重复两次构成。
     * <p>
     * 示例 2:
     * 输入: "aba"
     * 输出: False
     * <p>
     * 示例 3:
     * 输入: "abcabcabcabc"
     * 输出: True
     * 解释: 可由子字符串 "abc" 重复四次构成。(或者子字符串 "abcabc" 重复两次构成。)
     * <p>
     * 解题思路：
     * 1。根据字符串求出前缀表next数组
     * 2。如果next数组最后元素值不等于-1，则说明存在相同的前后缀
     * 3。在求出最长相同前后缀的个数，与字符串总长度相除，能够除净，则说明存在子字符串重复构成字符串情况
     */
    public boolean repeatedSubstringPattern(String s) {
        int size = s.length();
        int[] next = new int[size];
        getNext(s, next);
        PrintUtils.print(next);
        if (next[size - 1] == -1) {
            return false;
        }
        if (size % (size - (next[size - 1] + 1)) == 0) {
            return true;
        }
        return false;
    }
}
