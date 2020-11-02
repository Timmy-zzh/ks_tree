package com.timmy.leetcode._202008;

public class _0829_practice {

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        _0829_practice practice = new _0829_practice();
//        boolean result = practice.isMatch("aa", "a");
//        boolean result = practice.isMatch("aa", "*");
//        boolean result = practice.isMatch("cb", "?a");
//        boolean result = practice.isMatch("adceb", "*a*b");
        boolean result = practice.isMatch("acdcb", "a*c?b");
        System.out.println("resulg:" + result);
        System.out.println("-----------------------------------------");
    }

    /**
     * 44. 通配符匹配
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * <p>
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     * <p>
     * 说明:
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     * <p>
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     */
    boolean isMatch = false;

    public boolean isMatch(String s, String p) {
        isMatch = false;
        realMathch(s, p, 0, 0);
        return isMatch;
    }

    /**
     * @param s  字符串
     * @param p  通配符
     * @param si 当前字符串匹配位置
     * @param pi 当前通配符匹配位置
     */
    private void realMathch(String s, String p, int si, int pi) {
        if (isMatch) {
            return;
        }
        System.out.println("s:" + s + ",p:" + p + ",si:" + si + ",pi:" + pi);
        if (si == s.length()) {
            if (pi == p.length()) {
                isMatch = true;
            }
            return;
        }

        char pChar = p.charAt(pi);
        if (pChar == '?') {    //单个字符
            realMathch(s, p, si + 1, pi + 1);
        } else if (pChar == '*') {      //0个或多个
            realMathch(s, p, si + 1, pi);
            realMathch(s, p, si, pi + 1);
        } else {
            if (si < s.length() && pChar == s.charAt(si)) {
                realMathch(s, p, si + 1, pi + 1);
            } else {
                realMathch(s, p, si, pi + 1);
            }
        }

    }


}
