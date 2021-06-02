package com.timmy._review._09double_point;

import java.util.HashMap;

public class _02替换后的最长重复字符_424 {

    public static void main(String[] args) {
        _02替换后的最长重复字符_424 demo = new _02替换后的最长重复字符_424();
//        int res = demo.characterReplacement("ABAB", 2);
        int res = demo.characterReplacement("AABABBA", 1);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个字符串，字符串由大写字母组成，可以将字符串中的自负进行替换，最多可以替换k次，最后得到的子串最长
     * 2。解题思路
     * 双指针解法: 通过双指针控制区间，且区间中的字符最多有k个字符不同，
     * -右端指针固定移动，移动前后保证区间里面的字符最多k个不同，左侧根据区间不同字符的长度进行右移
     * -右移到那个位置去呢？
     * todo error
     */
    public int characterReplacement(String s, int k) {
        System.out.println(s);
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = -1;
        int res = 0;
        //使用map保存每个字符保存的位置
        //map 只保存区间内字符出现的第一次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            char ch = chars[right];

            if (!map.containsKey(ch)) {  //遇到新的字符，在区间中没有出现过的
                map.put(ch, right);
                //是否要移动left
                if (k >= 0) {
                    k--;
                } else {
                    left = map.get(ch);
                }
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
     * 在执行上述操作后，找到包含重复字母的最长子串的长度。
     * 注意：字符串长度 和 k 不会超过 104。
     *
     * 示例 1：
     * 输入：s = "ABAB", k = 2
     * 输出：4
     * 解释：用两个'A'替换为两个'B',反之亦然。
     *
     * 示例 2：
     * 输入：s = "AABABBA", k = 1
     * 输出：4
     * 解释：
     * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
     * 子串 "BBBB" 有最长重复字母, 答案为 4。
     *
     * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
     */
}
