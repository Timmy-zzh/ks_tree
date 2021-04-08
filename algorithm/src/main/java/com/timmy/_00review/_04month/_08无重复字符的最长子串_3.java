package com.timmy._00review._04month;

import java.util.HashMap;

/**
 * 其他解法：滑动窗口
 */
class _08无重复字符的最长子串_3 {

    public static void main(String[] args) {
        _08无重复字符的最长子串_3 demo = new _08无重复字符的最长子串_3();
//        int res = demo.lengthOfLongestSubstring("abcabcbb");
//        int res = demo.lengthOfLongestSubstring("bbbbb");
//        int res = demo.lengthOfLongestSubstring("pwwkew");
        int res = demo.lengthOfLongestSubstring("tmmzuxt");
        System.out.println("res:" + res);
    }

    public int lengthOfLongestSubstring(String s) {
        System.out.println(s);
        int left = 0;
        int res = 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (hashMap.containsKey(ch)) {
                //如果包含该位置,挪动子串左侧位置
                Integer index = hashMap.get(ch);
                left = Math.max(left, index + 1);
            }
            res = Math.max(res, i - left + 1);
            hashMap.put(ch, i);
        }
        return res;
    }

    /**
     * 1.理解题意
     * -输入一个字符串，找出其中最长的一个子串，要求子串中没有重复的字符
     * 2。解题思路
     * -遍历字符串中的字符，并使用HashMap保存每个字符，在数组中出现的下标位置，
     * -在遍历过程中，新遍历到的字符，需要先判断该字符在之前的字符中出现过，如果出现过，则需要挪动子串的左侧范围的位置
     * -如果该字符之前没有出现过，则子串右侧范围往后移动，并记录当前字符出现的位置
     */
    public int lengthOfLongestSubstring_v1(String s) {
        System.out.println(s);
        int left = 0;
        int res = 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (!hashMap.containsKey(ch)) {
                res = Math.max(res, i - left + 1);
            } else {
                //如果包含该位置,挪动子串左侧位置
                Integer index = hashMap.get(ch);
                left = Math.max(left, index + 1);
                res = Math.max(res, i - left + 1);
            }
            hashMap.put(ch, i);
        }
        return res;
    }

    /**
     *
     for (int i = 0; i < chars.length; i++) {
     char ch = chars[i];
     if (hashMap.containsKey(ch)) {
     //如果包含该位置,挪动子串左侧位置
     Integer index = hashMap.get(ch);
     left = Math.max(left, index + 1);
     }
     res = Math.max(res, i - left + 1);
     hashMap.put(ch, i);
     }
     */


    /**
     * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
     *
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 示例 4:
     * 输入: s = ""
     * 输出: 0
     *
     * 提示：
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     *
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     */
}
