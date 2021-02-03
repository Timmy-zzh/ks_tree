package com.timmy.lgsf._02algorithm._15greed_recursive;

public class _03不含AAA或BBB的字符串_984 {

    public static void main(String[] args) {

    }

    /**
     * 给定两个整数 A 和 B，返回任意字符串 S，要求满足：
     * <p>
     * S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
     * 子串 'aaa' 没有出现在 S 中；
     * 子串 'bbb' 没有出现在 S 中。
     * <p>
     * 示例 1：
     * 输入：A = 1, B = 2
     * 输出："abb"
     * 解释："abb", "bab" 和 "bba" 都是正确答案。
     * <p>
     * 示例 2：
     * 输入：A = 4, B = 1
     * 输出："aabaa"
     * <p>
     * 链接：https://leetcode-cn.com/problems/string-without-aaa-or-bbb
     */

    /**
     * 1.理解题意
     * -返回字符串，个数为a+b，里面包含a个A，b个B，但是不包含AAA和BBB
     * 2。递归，
     * -每次递归都是增加一个字符，不是A，就是B，且每次增加了自负，a与b的值减少1
     * -当减少值为0时，递归结束，判断最后返回的值是否满足要求
     */
//    public String strWithout(int a, int b) {
//        return process("", a, b);
//    }
//
//    private String process(String s, int a, int b) {
//        if (s.length() == a + b && isAdapter(s)) {
//            return s;
//        }
//        if (isAdapter(s)) {
//            if (a > 0) {
//                return process(s + "a", a - 1, b);
//            } else if (b > 0) {
//                return process(s + "b", a, b - 1);
//            }
//        }
//        return process(s + "a", a - 1, b);
//    }
//
//    private boolean isAdapter(String s) {
//        if (s.length() == 0) {
//            return true;
//        }
//        int count = 0;
//        for (int i = 1; i < s.length(); i++) {
//            if (s.charAt(i) == s.charAt(i - 1)) {
//                count++;
//            }
//            if (count >= 3) {
//                return false;
//            }
//        }
//        return true;
//    }
}
