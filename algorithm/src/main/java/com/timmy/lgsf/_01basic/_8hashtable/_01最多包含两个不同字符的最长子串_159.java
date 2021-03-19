package com.timmy.lgsf._01basic._8hashtable;

import java.util.HashSet;

public class _01最多包含两个不同字符的最长子串_159 {

    public static void main(String[] args) {
        _01最多包含两个不同字符的最长子串_159 demo = new _01最多包含两个不同字符的最长子串_159();
//        String result = demo.lengthOfLongedSubstring_v1("abcabcbb");
        int result = demo.lengthOfLongestSubstringKDistinct("abcabcbb", 2);
        System.out.println("result:" + result);
    }

    /**
     * 最多包含两个不同字符的最长子串-159
     * <p>
     * 解题思路：
     * 1。理解题意：
     * HashSet + 双指针
     * 遍历字符数组，确定左右指针，left-right,
     * 每次遍历right字符时， 判断当前hashset中是否包含该字符，并计算不同字符的个数
     * 当不同字符达到3个时，就需要移动left指针
     */
    public String lengthOfLongedSubstring_v1(String s) {
        String res = "";
        if (s == null || s.length() == 0) {
            return res;
        }
        int left = 0,
                right = 0;
        int len = s.length();
        HashSet<Character> hashSet = new HashSet<>();
        int skip = 0;

        //bbaabbcb
        for (left = 0; left < len; left++) {
            skip = 0;
            hashSet.clear();
            for (right = left; right < len; right++) {
                //右侧指针的新字符
                char rightChar = s.charAt(right);
                System.out.println("left:" + left + " ,right:" + right);
                if (!hashSet.contains(rightChar)) {
                    skip++;
                    hashSet.add(rightChar);
                }

                if (skip == 3) {
                    break;
                }
//                if (right > 0 && s.charAt(right) != s.charAt(right - 1)) {
//                    left = right;
//                }
                int size = right + 1 - left;
                if (res.length() < size) {
                    res = s.substring(left, right + 1);
                }
                System.out.println("res:" + res);
            }
        }
        return res;
    }

    //最多包含k个字符的最长子串
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        String res = "";
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0,
                right = 0;
        int len = s.length();
        HashSet<Character> hashSet = new HashSet<>();
        int skip = 0;

        //bbaabbcb
        for (left = 0; left < len; left++) {
            skip = 0;
            hashSet.clear();
            for (right = left; right < len; right++) {
                //右侧指针的新字符
                char rightChar = s.charAt(right);
                System.out.println("left:" + left + " ,right:" + right);
                if (!hashSet.contains(rightChar)) {
                    skip++;
                    hashSet.add(rightChar);
                }

                if (skip == k + 1) {
                    break;
                }
                int size = right + 1 - left;
                if (res.length() < size) {
                    res = s.substring(left, right + 1);
                }
                System.out.println("res:" + res);
            }
        }
        return res.length();
    }
}
