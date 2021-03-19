package com.timmy.lgsf._01basic._8hashtable;


public class _00无重复字符的最长子串_3 {

    public static void main(String[] args) {
        _00无重复字符的最长子串_3 demo = new _00无重复字符的最长子串_3();
        int result = demo.lengthOfLongedSubstring("abcabcbb");
//        int result = demo.lengthOfLongedSubstring("bbbbb");
//        int result = demo.lengthOfLongedSubstring("pwwkew");

        System.out.println("result:" + result);
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *  
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 示例 4:
     * 输入: s = ""
     * 输出: 0
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     */

    /**
     * 解题思路：优化解法
     * 6c解题法
     * 1。理解题意
     * -在基本解法中，使用的是最右侧字符在 前面子串是否存在进行判断，然后进行左右指针的移动
     * -因为ASCII表中所有的字符数量是128个，所以可以定义一个字符数组char[] 用于保存每个字符出现的下标位置
     * --初始化字符数组所有元素为-1，遍历字符串，保存每个字符出现的下标到自负数组中
     * ---当新的字符在字符数组中出现过时，则移动左侧指针的位置
     * <p>
     * 数组是一种特殊的哈希表
     */
    public int lengthOfLongedSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        //初始化字符数组，保存该字符出现的索引位置
        int[] chars = new int[128];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = -1;
        }

        int res = 1, left = 0, right = 0;
        int len = s.length();
        while (right < len) {
            char ch = s.charAt(right);
            if (chars[ch] != -1) {    //存在-改变lefe的位置
                int start = chars[ch] + 1;
                // 下面这样直接赋值会存在问题，例如 abba，访问到第二个a的时候，left会变成1，应该是2
//                left = start;
                left = left >= start ? left : start;
            }
            int size = right - left + 1;
            res = res > size ? res : size;
            // 字符数组保存左侧字符的下标位置
            chars[ch] = right;
            right++;
        }
        return res;
    }

    /**
     * 解题思路：基本解法
     * 6c解题法
     * 1。理解题意
     * -遍历字符串，找到不含重复字符的最长子串
     * -可以遍历字符串，双指针指向字串的起始结束位置，lefe-right
     * --right指针不断往后遍历，每遍历一次，都需要判断right指针字符在字串中是否出现
     * ---如果没出现，则最长子串数量加1，
     * ---如果新的right字符在左侧子串中存在，则left指针往右移1位，right指针从left指针右侧开始重新计算
     * 2。code
     * -两层for循环，内层循环right指针新字符，判断在前面的子串中是否存在该字符？
     */
    public int lengthOfLongedSubstring_v0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int res = 1,
                left = 0,
                right = 0;
        int len = s.length();
        for (left = 0; left < len; left++) {
            for (right = left + 1; right < len; right++) {
                //右侧指针的新字符
                char rightChar = s.charAt(right);
                // 左侧的子串，判断新字符在左侧子串中是否有出现
                String subStr = s.substring(left, right);
                if (subStr.indexOf(rightChar) != -1) {
                    //出现
                    break;
                }
                //没有出现
                int size = right + 1 - left;
                res = res > size ? res : size;
            }
        }
        return res;
    }
}
