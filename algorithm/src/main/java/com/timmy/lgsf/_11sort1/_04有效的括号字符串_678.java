package com.timmy.lgsf._11sort1;

public class _04有效的括号字符串_678 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
     *
     * 任何左括号 ( 必须有相应的右括号 )。
     * 任何右括号 ) 必须有相应的左括号 ( 。
     * 左括号 ( 必须在对应的右括号之前 )。
     * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
     * 一个空字符串也被视为有效字符串。
     * 示例 1:
     * 输入: "()"
     * 输出: True
     *
     * 示例 2:
     * 输入: "(*)"
     * 输出: True
     *
     * 示例 3:
     * 输入: "(*))"
     * 输出: True
     *
     * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string
     */

    /**
     * 括号字符串匹配
     * 左右括号抵消，
     * 当遇到*字符时，
     * 解题思路：先将左右括号抵消，剩下的应该就是*和左括号，或者*和右括号，只要*的数量大于括号数量，则返回true
     */
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        //去除左右的括号
        while (left < right && chars[left] == '(' && chars[right] == ')') {
            chars[left] = ' ';
            chars[right] = ' ';
            left++;
            right--;
        }

        //去除连续的左右括号
        while (left < right) {
            if (chars[left] == '(' && chars[left + 1] == ')') {
                chars[left] = ' ';
                chars[left + 1] = ' ';
                left += 2;
            } else {
                left++;
            }
        }

        int num1 = 0;
        int numk = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '*') {
                num1++;
            } else if (aChar == '(' || aChar == ')') {
                numk++;
            }
        }
        return num1 >= numk;
    }


}
