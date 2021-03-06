package com.timmy.testlib;

public class Manacher {

    public static void main(String[] args) {
        Manacher demo = new Manacher();
//        String res = demo.longestPalindrome("babad");
//        String res = demo.longestPalindrome("cbbd");
        String res = demo.longestPalindrome("ccc");
        System.out.println("res:" + res);
    }

    public String longestPalindrome(String s) {
        int startIndex = 0, maxLen = 1;
        char[] chars = s.toCharArray();
        int N = chars.length;
        for (int i = 0; i < N; i++) {
            int len1 = getPalindromeLen(chars, i, i);
            int len2 = getPalindromeLen(chars, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                startIndex = i - (maxLen - 1) / 2;
                System.out.println("startIndex:" + startIndex + " ,maxLen:" + maxLen);
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    private int getPalindromeLen(char[] chars, int start, int end) {
        while (0 <= start && end < chars.length && chars[start] == chars[end]) {
            start--;
            end++;
        }
        System.out.println("start:" + start + " ,end:" + end);
        return end - start - 1;
    }

    private boolean isPalindrome(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }
        return true;
    }

}
