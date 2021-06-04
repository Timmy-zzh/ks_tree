package com.timmy._review._09double_point;

import java.util.HashMap;
import java.util.Iterator;

public class _10最小覆盖子串_76 {

    public static void main(String[] args) {
        _10最小覆盖子串_76 demo = new _10最小覆盖子串_76();
//        String res = demo.minWindow("ADOBECODEBANC", "ABC");
        String res = demo.minWindow("cabwefgewcwaefgcf", "cae");
//        String res = demo.minWindow("a", "a");
        System.out.println("res:" + res);
    }

    private class Counter extends HashMap<Character, Integer> {

        public int get(Character key) {
            return containsKey(key) ? super.get(key) : 0;
        }

        public void add(Character ch, int incre) {
            put(ch, get(ch) + incre);
            if (get(ch) == 0) {
                remove(ch);
            }
        }

        public void print() {
            Iterator<Entry<Character, Integer>> iterator = this.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<Character, Integer> next = iterator.next();
                System.out.println(next.getKey() + " - " + next.getValue());
            }
        }
    }

    /**
     * 1。理解题意
     * -输入两个字符串s,t,s字符串更长，现在要求判断s字符串中是否包含t的子串，且子串长度最短
     * 2。解题思路
     * 双指针-最短区间
     * -先求t短字符的自负数量，再求在区间范围内覆盖t的字符，以区间右指针为固定点，区间左指针不断缩小
     */
    public String minWindow(String s, String t) {
        System.out.println(s);
        System.out.println(t);
        char[] charsLong = s.toCharArray();
        char[] charsShort = t.toCharArray();

        Counter counterShort = new Counter();
        Counter counterLong = new Counter();

        for (int i = 0; i < charsShort.length; i++) {
            counterShort.add(charsShort[i], 1);
        }
        counterShort.print();
        int shortChNum = counterShort.size();  //短-字符种类个数
        System.out.println("shortChNum:" + shortChNum);
        int okNum = 0;    //检索区间范围内，与短字符串-字符个数相等的数量

        int left = -1;
        int startIndex = -2;
        int resLen = charsLong.length + 1;
        for (int right = 0; right < charsLong.length; right++) {
            char longChar = charsLong[right];
            counterLong.add(longChar, 1);

            if (counterLong.get(longChar) == counterShort.get(longChar)) {
                okNum++;
            }

            if (right - left < charsShort.length) {
                continue;
            }

            //区间范围内，长字符串包含了段字符串，需要不断移动区间左侧指针
            while (okNum >= shortChNum && left < right) {
                System.out.println("left:" + left + " ,right:" + right + " ,okNum:" + okNum);

                int newLen = right - left;  //新的子串长度
                if (newLen < resLen) {
                    startIndex = left + 1;
                    resLen = newLen;
                    System.out.println("startIndex:" + startIndex);
                }

                //区间左指针往右移动，不断修改配置的字符个数
                left++;
                char longLeftCh = charsLong[left];
                counterLong.add(longLeftCh, -1);
                if (counterLong.get(longLeftCh) < counterShort.get(longLeftCh)) {
                    okNum--;
                }
            }
        }

        return startIndex > -1 ? s.substring(startIndex, startIndex + resLen) : "";
    }

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
     * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 示例 1：
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     *
     * 示例 2：
     * 输入：s = "a", t = "a"
     * 输出："a"
     *
     * 提示：
     * 1 <= s.length, t.length <= 105
     * s 和 t 由英文字母组成
     *
     * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     */
}
