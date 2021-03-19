package com.timmy.lgsf._01basic._9deque;

/**
 * TODO ?????
 */
public class _03字符串的排列_567 {
    public static void main(String[] args) {
        _03字符串的排列_567 demo = new _03字符串的排列_567();
        boolean result = demo.checkInclusion("ab", "eidbaooo");
//        boolean result = demo.checkInclusion("ab", "eidboaoo");
        System.out.println("result:" + result);
    }

    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     * <p>
     * 示例1:
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     * <p>
     * 示例2:
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     * <p>
     * 链接：https://leetcode-cn.com/problems/permutation-in-string
     */

    /**
     * 解题思路：
     * 罗列所有s1的排列，判断s2中是否有其中的一个排列
     * -？如何求s1的排列
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map))
                return true;
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }
}
