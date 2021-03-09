package com.timmy.lgsf._06complex_scene._04kmp;

import com.timmy.common.PrintUtils;

public class _01实现strStr_28 {

    public static void main(String[] args) {
        _01实现strStr_28 demo = new _01实现strStr_28();
//        int res = demo.strStr("hello", "ll");
//        int res = demo.strStr("aaaaa", "bba");
        int res = demo.strStr("a", "a");
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入两个字符串 haystack 和needle， 求在haystack 中出现 needle 字符串的第一个位置
     * -函数strStr实现
     * 2。解题思路：kmp算法
     * -求模式串的next[]数组，next[i] 表示i字符之前的前后缀的最长长度
     * -接着遍历文本串
     * --如果文本串与模式串字符相等，则同时往右移动
     * --如果不想等，则根据next数据确定模式串的移动位置
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        System.out.println("haystack:" + haystack + " ,needle:" + needle);
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        //模式串字符数组
        char[] pArr = needle.toCharArray();
        //文本串字符数组
        char[] tArr = haystack.toCharArray();
        int pLen = pArr.length;
        int tLen = tArr.length;

        //获取模式串的next数组
        int[] next = getNext(pArr);
        PrintUtils.print(next);

        //模式串与文本串比较
        int p = 0, t = 0;
        while (p < pLen && t < tLen) {
            if (pArr[p] == tArr[t]) {   //模式串与文本串字符相等，则同时往右移动
                p++;
                t++;
            } else {
                //不相登的话，则根据next数组，移动模式串的比较位置p
                if (next[p] == -1) {
                    p = 0;
                    t++;
                } else {
                    p = next[p];
                }
            }
            if (p == pLen) {
                return t - p;
            }
        }
        return -1;
    }

    /**
     * k 的值表示字符左侧比较的下标位置，默认=-1；
     * i 表示遍历到的自负右侧下标位置
     * <p>
     * next[i] 数组的值表示元素i位置前的字符的前后缀最长长度
     * -默认next[0]=-1;表示在位置0之前没有前后缀，然后i++
     * -next[1] = 0;因为下表1前面只有一个字符，前后缀为0
     * -next[2]= 的值则根据下标1和下标2的字符是否相等进行确定，
     * --如果相等，next[2]=1,代表下标位置2前面存在长度1的前后缀
     * --如果不相等，则next[k] 回溯，当k=-1时，便利回到原点，说明当前下标前面的字符串不存在相等的前后缀，则k从位置0，i往后继续遍历判断
     *
     * @param pArr
     * @return
     */
    private int[] getNext(char[] pArr) {
        int len = pArr.length;
        int[] next = new int[len];
        int k = -1;
        next[0] = -1;
        int i = 0;
        while (i < len - 1) {
            /**
             * k == -1; 则回到初始点，从下标0 重新开始遍历，i位置没有相等的前后缀，next[i]=0
             * pArr[k] == pArr[i] 相等，说明存在相等的前后缀字符，则前后下标同时右移
             */
            if (k == -1 || pArr[k] == pArr[i]) {
                k++;
                i++;
                next[i] = k;
            } else {
                //k值回溯，回到前面的字符，判断与当前位置i字符，是否相等
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1。
     *
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     *
     * 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     *
     * 说明:
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     *
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     */
}
