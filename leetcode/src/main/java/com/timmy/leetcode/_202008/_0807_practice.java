package com.timmy.leetcode._202008;


public class _0807_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
////        int result = reverse(123);
////        int result = reverse(-123);
////        int result = reverse(120);
//        int result = reverse(1534236469);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
////        int result = myAtoi("4193 with words");
////        int result = myAtoi("-91283472332");
////        int result = myAtoi("2147483648");
//        int result = myAtoi("-5-");
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
//        boolean result = isPalindrome(-121);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
//        boolean result = isMatch("aab", "c*a*b");
        boolean result = isMatch("mississippi", "mis*is*p*.");
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");
    }

    /**
     * 使用回溯法进行字符串匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * 匹配规则：
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * <p>
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * <p>
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     */
    private static boolean matched = false;

    public static boolean isMatch(String s, String p) {
        if (s.isEmpty()) {
            return true;
        }
        if (p.isEmpty()) {
            return false;
        }
        matched = false;
        rMatch(0, 0, s.toCharArray(), p.toCharArray());
        return matched;
    }

    /**
     * 当p 字符为.时 可以匹配一个字符，
     * 当p为* 时可以匹配零个或多个
     * 为字母时两两匹配
     * 最后的匹配规则时字符串与匹配串都遍历到末尾
     */
    private static void rMatch(int si, int pi, char[] sArr, char[] pArr) {
        if (matched) {
            return;
        }
        if (pi == pArr.length) {
            if (si == sArr.length) {
                matched = true;
            }
            return;
        }
        if (pArr[pi] == '.') {
            rMatch(si + 1, pi + 1, sArr, pArr); //匹配一个
        } else if (pArr[pi] == '*') {   //0个或多个
            for (int i = 0; i < sArr.length - si; i++) {
                rMatch(si + i, pi + 1, sArr, pArr);
            }
        } else if (si < sArr.length && sArr[si] == pArr[pi]) {
            rMatch(si + 1, pi + 1, sArr, pArr);
        }
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     */
    public static boolean isPalindrome(int x) {
        String numStr = String.valueOf(x);
        if (numStr.length() == 1) {
            return true;
        }
        char[] chars = numStr.toCharArray();
        int start = 0, end = chars.length - 1;
        while (start < end) {
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串转换整数 (atoi)
     * 思路：
     * 1。字符串转成字符数组遍历，找到第一个不为空的字符
     * 2。判断该字符是否是空字符还是+-号，还是数字，然后进行不同的处理
     * <p>
     * 输入: "42"
     * 输出: 42
     * <p>
     * 输入: "   -42"
     * 输出: -42
     * <p>
     * 输入: "4193 with words"
     * 输出: 4193
     * <p>
     * 输入: "words and 987"
     * 输出: 0
     * <p>
     * 输入: "-91283472332"
     * 输出: -2147483648
     */
    public static int myAtoi(String str) {
        if (str.isEmpty() || str.trim().isEmpty()) {
            return 0;
        }
        if (str.length() == 1 && ("+".equals(str) || "-".equals(str))) {
            return 0;
        }
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == ' ') {
                if (!sb.toString().isEmpty()) {
                    break;
                }
                continue;
            }
            if (aChar == '+' || aChar == '-') { //+-号
                if (!sb.toString().isEmpty()) {
                    break;
                }
                sb.append(aChar);
            } else if (aChar >= '0' && aChar <= '9') {      //数字
                sb.append(aChar);
            } else {    //其他
                if (!sb.toString().isEmpty()) {
                    break;
                }
                return 0;
            }
        }
        if (sb.toString().length() == 1 && (sb.toString().equals("+") || sb.toString().equals("-"))) {
            return 0;
        }
        int result;
        try {
            System.out.println("sb:" + sb.toString());
            result = Integer.valueOf(sb.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
            if (sb.toString().startsWith("-")) {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
        return result;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 输入: 123
     * 输出: 321
     * <p>
     * 输入: -123
     * 输出: -321
     * <p>
     * 输入: 120
     * 输出: 21
     * 思路：
     * 1。将整数转成字符串，并转成字符数组，对数组元素进行交换
     * 2。交换后转成字符串，注意考虑负数情况
     */
    private static int reverse(int x) {
        String numStr = String.valueOf(x);
        char[] chars = numStr.toCharArray();
        int start = x < 0 ? 1 : 0;
        int end = chars.length - 1;
        char temp;

        while (start < end) {
            //swap
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }
//        //将字符数组转成字符
//        String s = String.valueOf(chars);
//        System.out.println(s);
//        Integer integer = Integer.valueOf(s);
//        System.out.println("integer:" + integer);
        try {
            return Integer.valueOf(String.valueOf(chars));
        } catch (Exception e) {
            return 0;
        }
    }

}