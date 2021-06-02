package com.timmy._review._09double_point;

import java.util.Arrays;

public class _01无重复字符的最长子串_3 {

    public static void main(String[] args) {
        _01无重复字符的最长子串_3 demo = new _01无重复字符的最长子串_3();
//        int res = demo.lengthOfLongestSubstring("abcabcbb");
        int res = demo.lengthOfLongestSubstring("pwwkew");
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，字符串中的字符可能存在重复的字符，求其中的一个最长子串，且该子串中的字符都不相同
     * 2。解题思路
     * -双指针解法，左右子针表示子串的区间，且区间范围为左开右闭 (left,right], 区间范围的长度为 right - left
     * -右端固定法：区间的right范围固定往后移动，区间左侧left的值要保证，区间中的字符不相等，
     * --且区间右侧right新字符添加进入区间后，还是要保证区间中的字符不相等
     * -当右子针的自负在原来区间中存在相同的字符时，如何处理呢？这个时候因为区间有端固定，所以需要移动区间的左子针
     * --使用数组保存每个字符最后出现在字符串中的位置
     */
    public int lengthOfLongestSubstring(String s) {
        System.out.println(s);
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = -1;
        int res = 1;
        int[] pos = new int[256];   //保存字符在字符串中的下标位置
        Arrays.fill(pos, -1);

        for (int right = 0; right < chars.length; right++) {
            int ch = chars[right];
            //判断新字符在区间(left,right] 范围中是否出现
            if (pos[ch] > left) {
                left = pos[ch];
            }
            pos[ch] = right;
            System.out.println("left:" + left + " ,right:" + right);
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
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
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     */
}
