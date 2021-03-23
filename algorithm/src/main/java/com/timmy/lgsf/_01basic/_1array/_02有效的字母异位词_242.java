package com.timmy.lgsf._01basic._1array;

import com.timmy.common.PrintUtils;

public class _02有效的字母异位词_242 {

    public static void main(String[] args) {
        _02有效的字母异位词_242 demo = new _02有效的字母异位词_242();
//        boolean res = demo.isAnagram("anagram", "nagaram");
        boolean res = demo.isAnagram("rat", "car");
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -字母异位词
     * 2。解题思路
     * -因为题目说了字符串只由小写字母组成，可以使用int[26]数组记录s中每个字母出现的次数
     * -然后t字符串中 遍历的时候不断减少字母出现的次数
     * -最后判断是否有不等于0的字母出现次数
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] nums = new int[26];
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            nums[sArr[i] - 'a']++;
        }
        PrintUtils.print(nums);
        for (int i = 0; i < tArr.length; i++) {
            nums[tArr[i] - 'a']--;
        }
        PrintUtils.print(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     *
     * 链接：https://leetcode-cn.com/problems/valid-anagram
     */
}
