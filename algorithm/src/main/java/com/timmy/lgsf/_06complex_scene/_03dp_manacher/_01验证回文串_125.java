package com.timmy.lgsf._06complex_scene._03dp_manacher;

public class _01验证回文串_125 {

    public static void main(String[] args) {
        _01验证回文串_125 demo = new _01验证回文串_125();
        boolean palindrome = demo.isPalindrome("A man, a plan, a canal: Panama");
//        boolean palindrome = demo.isPalindrome("race a car");
//        boolean palindrome = demo.isPalindrome("raceacar");
        System.out.println("res:" + palindrome);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，判断他是否是回文串，只考虑字母和数字字符，忽略字母的大小写
     * 2。解题思路：双指针解法
     * -将字符串转换成字符数组，前后指针不断向中间移动，并判断指针指向的字符是否相等
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int N = chars.length;
        int l = 0, r = N - 1;
        while (l < r) {
            while (l < N && !isEnable(chars[l])) {
                l++;
            }
            while (r >=0 &&!isEnable(chars[r])) {
                r--;
            }

            if (l >= r) {
                break;
            }
            /**
             * 如果是大写字母，则转成小写字母
             * -字母小写a-z：97-122；
             * -字母大写A-Z：65-90；
             */
            if (chars[l] >= 'A' && chars[l] <= 'Z') {
                chars[l] = (char) (chars[l] + 32);
            }
            if (chars[r] >= 'A' && chars[r] <= 'Z') {
                chars[r] = (char) (chars[r] + 32);
            }
            System.out.println("l:" + l + " ,r:" + r);

            if (chars[l++] != chars[r--]) {
                return false;
            }
        }
        return true;
    }

    //是否是有效字符
    private boolean isEnable(char ch) {
//        chars[l] == ' '||((chars[l] < 'a' || chars[l] > 'z') && (chars[l] < 'A' || chars[l] > 'A') && (chars[l] > '9' || chars[l] < '0')
        if (ch == ' ') {
            return false;
        }
        if (ch >= 'a' && ch <= 'z') {
            return true;
        }
        if (ch >= 'A' && ch <= 'Z') {
            return true;
        }
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }


    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     *
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     *
     * 链接：https://leetcode-cn.com/problems/valid-palindrome
     */
}
