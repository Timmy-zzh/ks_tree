package com.timmy.lgsf._01basic._05string;

public class _02最大公共子串 {

    public static void main(String[] args) {
        String s = "13451856";
        String t = "123456";
        String subStr = findMaxSubStr(s, t);
        System.out.println("subStr：" + subStr);
    }

    /**
     * 求最长公共子串
     * 解题思路：
     * 1。同时遍历字符串s1，s2
     * 2.查看两个字符串s1，s2，遍历过程中的字符是否匹配
     * 3。匹配的话记录匹配的最长长度，并且返回
     *
     * @param s
     * @param t
     * @return
     */
    private static String findMaxSubStr(String s, String t) {
        String maxSub = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    System.out.println("i:" + i + " ,j:" + j);
                    int x = 0, y = 0;
                    for (; x < s.length() - i && y < t.length() - j; x++, y++) {
                        if (s.charAt(i + x) != t.charAt(j + y)) {
                            break;
                        }
                    }
                    System.out.println("maxSub:" + maxSub + " ，x:" + x + " ,i:" + i);
                    if (x > maxSub.length()) {
                        maxSub = s.substring(i, x + i);
                        System.out.println("maxSub:" + maxSub);
                    }
                }
            }
        }
        return maxSub;
    }


}
