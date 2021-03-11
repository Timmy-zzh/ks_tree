package com.timmy.lgsf._06complex_scene._05string_matching;

import com.timmy.common.PrintUtils;

public class _00通配符匹配_44_贪心回溯算法 {

    public static void main(String[] args) {
        _00通配符匹配_44_贪心回溯算法 demo = new _00通配符匹配_44_贪心回溯算法();
        boolean res = demo.isMatch("adceb", "*a*b");
//        boolean res = demo.isMatch("acdcb", "a*c?b");
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串s和匹配串p，判断两个字符串是否完全匹配？
     * --其中s，p都可能为空，s只包含小写字母，p包含小写字母和通配符?和*
     * --其中？可以匹配单个字符，*可以匹配任意个字符串（包括空字符串）
     * 2。解题思路：贪心+回溯算法+双指针
     * 之前使用动态规划算法，需要将字符串与匹配全部进行比较，如果在比较的中途发现不匹配了，其实就可以停止直接返回false
     * -字符串与匹配串同时从尾部开始遍历比较
     * --字符比较相等，同时左移，当遇到字符"*"时，退出循环，
     * -接着从头部使用双指针进行比较，采用回溯的思想
     * --遇到相同的字符或匹配串字符串为"?",同时向右移动
     * --当遇到匹配串字符为"*"时，因为可以匹配任意个字符（包括空字符）
     * ---所以第一次不匹配字符串字符，模式串向右移动， 并记录当前位置record，当匹配不成功时，回溯到前面的位置+1，匹配字符串后面的1个字符
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        System.out.println(s);
        System.out.println(p);
        //为空处理
        if (s == null || s.isEmpty()) {
            if (p == null || p.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else if (p == null || p.isEmpty()) {
            return false;
        }

        //字符串转成字符数组
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int m = sChars.length;
        int n = pChars.length;

        int sRight = m - 1;
        int pRight = n - 1;
        while (sRight >= 0 && pRight >= 0) {
            if (sChars[sRight] == pChars[pRight] || pChars[pRight] == '?') {
                sRight--;
                pRight--;
            } else if (pChars[pRight] == '*') {
                //退出
                break;
            } else {
                //不想等
                return false;
            }
        }
        //如果pRight到达头部，需要判断sRight是否到达头部
        if (pRight < 0) {
            return sRight < 0;
        }

        //从头部开始遍历
        int sLeft = 0;
        int pLeft = 0;
        //回溯位置
        int sRecord = -1;
        int pRecord = -1;

        //从左侧开始遍历
        while (sLeft <= sRight && pLeft <= pRight) {
            if (pChars[pLeft] == '*') {    //模式串往后移，匹配空字符，并记录回溯位置，等待下次回溯
                pLeft++;
                sRecord = sLeft;
                pRecord = pLeft;
            } else if (sChars[sLeft] == pChars[pLeft] || pChars[pLeft] == '?') {
                pLeft++;
                sLeft++;
            } else if (sRecord >= 0 && sRecord + 1 <= sRight) {    //不匹配了，判断前面是否预留有回溯位置
                sRecord++;
                sLeft = sRecord;
                pLeft = pRecord;
            } else {
                //真匹配不了了
                return false;
            }
        }

        /**
         * 1。如果字符串挨到一起了，则判断匹配字符pLeft到pRight下标位置是否都是*。否则不匹配
         * 2。如果匹配串碰撞到一起了，则字符串不用管，因为sRight位置是*，可以匹配任意字符
         */
        for (int i = pLeft; i <= pRight; i++) {
            if (pChars[i] != '*') {
                return false;
            }
        }

        return true;
    }

    /**
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     *
     * 说明:
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     *
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     *
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释: '*' 可以匹配任意字符串。
     *
     * 示例 3:
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     *
     * 示例 4:
     * 输入:
     * s = "adceb"
     * p = "*a*b"
     * 输出: true
     * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     *
     * 示例 5:
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输出: false
     *
     * 链接：https://leetcode-cn.com/problems/wildcard-matching
     */
}
