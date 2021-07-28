package com.timmy.practice._07month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class _27无重复字符的最长子串_3 {

    public static void main(String[] args) {
        _27无重复字符的最长子串_3 demo = new _27无重复字符的最长子串_3();
        int res = demo.lengthOfLongestSubstring("abcabcbb");
        System.out.println("res:" + res);
    }

    /**
     * 3。数组 + 双指针 解法
     * -因为hashmap保存的是一个字符，而ascii码表中所有字符的大小是128个，所以可以使用int[128]的数组替换hashmap进行数据保存
     * -每个字符在数组中都有其位置，数组元素的值表示当前字符在数组中的下标位置
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        System.out.println(s);
        int[] allChars = new int[128];
        Arrays.fill(allChars, -1);
        int start = 0;
        int res = 0;

        char[] chars = s.toCharArray();
        for (int end = 0; end < chars.length; end++) {
            char endCh = chars[end];
            if (allChars[endCh] != -1) {    //存在重复字符
                int preIndex = allChars[endCh];
                start = start > preIndex ? start : preIndex + 1;
            }
            allChars[endCh] = end;
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    /**
     * 2。hashmap + 双指针解法
     * -使用hashmap保存已遍历过的字符，并且记录好该字符出现在数组中的下标位置
     * -双指针start，end 标示不重复字符的子串
     * -将字符串转成字符数组，不断遍历字符，如果该字符在hashmap中存在，则移动start字符
     * -并且将新遍历的字符添加到hashmap中
     */
    public int lengthOfLongestSubstring_v2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        System.out.println(s);
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int start = 0;
        int res = 0;
        for (int end = 0; end < chars.length; end++) {
            char endCh = chars[end];
            if (hashMap.containsKey(endCh)) {
                int preIndex = hashMap.get(endCh);
                //寻找不重复字符的开始位置
                start = start > preIndex ? start : preIndex + 1;
            }
            hashMap.put(endCh, end);
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    /**
     * 1。理解题意
     * -输入一个字符串s，寻找其中一个最长子串，要求子串不存在重复字符，返回子串的长度
     * 2。解题思路：暴力解法
     * -使用两个循环，外层循环为子串的开始位置，内层循环为子串的结束位置，
     * --内层循环遍历到的字符不断与子串进行比较，如果内层新字符存在相同的字符，则内外层循环从新遍历，
     * --如果不相同子串范围扩大
     * -使用一个list保存不同的子串
     */
    public int lengthOfLongestSubstring_v1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        System.out.println(s);
        int len = s.length();
        ArrayList<String> list = new ArrayList<>();

        for (int start = 0; start < len; start++) {
            list.add(s.substring(start, start + 1));
            for (int end = start + 1; end < len; end++) {
                //已遍历过的子串[start,end)
                String subStr = s.substring(start, end);
                //判断新遍历的字符end，已便利字符串是否存在相同的字符
                if (subStr.contains(s.substring(end, end + 1))) {
                    break;
                }
                list.add(s.substring(start, end + 1));
            }
        }
        PrintUtils.printStr(list);
        int res = 0;
        for (String str : list) {
            res = Math.max(res, str.length());
        }
        return res;
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
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
