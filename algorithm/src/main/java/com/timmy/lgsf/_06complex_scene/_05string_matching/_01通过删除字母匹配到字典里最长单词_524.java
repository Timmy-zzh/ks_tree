package com.timmy.lgsf._06complex_scene._05string_matching;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _01通过删除字母匹配到字典里最长单词_524 {

    public static void main(String[] args) {
        _01通过删除字母匹配到字典里最长单词_524 demo = new _01通过删除字母匹配到字典里最长单词_524();
        String s = "abpcplea";
        String[] dictionary = {"ale", "apple", "monkey", "plea"};
//        String[] dictionary = {"ale", "aapple", "aazkey", "plea"};
        String res = demo.findLongestWord(s, dictionary);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，和一个字符串数组，通过删除字符串的任意字符，最终使得字符串和字符串数组中的字符相等
     * --要求最后相等的字符串最长，如果存在长度相同的则选择字典顺序更小的字符串
     * 2。解题思路
     * -对字符串进行排序，字符串长的在前面，字符串相等的则按照字典顺序排序
     * -然后按照长度从长到短，依次取出字符串，与原始给定的字符串判断，主要判断是否是给定字符串的子序列
     *
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, String[] dictionary) {
        //使用优先级队列保存字符串数组
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                if (s.length() != t1.length()) {
                    return t1.length() > s.length() ? 1 : -1;
                }
                return s.compareTo(t1);
            }
        });
        queue.addAll(Arrays.asList(dictionary));
//        System.out.println(queue.toString());

        while (!queue.isEmpty()) {
//            System.out.println("---");
            String poll = queue.poll();
            if (isPart(s, poll)) {
                return poll;
            }
//            System.out.println(queue.toString());
        }
        return "";
    }

    private boolean isPart(String longStr, String item) {
        char[] chars1 = longStr.toCharArray();
        char[] chars2 = item.toCharArray();

        int len1 = chars1.length;
        int len2 = chars2.length;
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            if (chars1[i] == chars2[j]) {
                j++;
            }
            i++;
        }
        return j == len2;
    }

    /**
     * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
     * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
     *
     * 示例 1:
     * 输入:
     * s = "abpcplea", d = ["ale","apple","monkey","plea"]
     * 输出:
     * "apple"
     *
     * 示例 2:
     * 输入:
     * s = "abpcplea", d = ["a","b","c"]
     * 输出:
     * "a"
     * 说明:
     *
     * 所有输入的字符串只包含小写字母。
     * 字典的大小不会超过 1000。
     * 所有输入的字符串长度不会超过 1000。
     *
     * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
     */
}
