package com.timmy._review._09double_point;

import java.util.HashMap;

/**
 * 2。定长区间
 */
public class _08字符串的排列_567 {

    public static void main(String[] args) {
        _08字符串的排列_567 demo = new _08字符串的排列_567();
        boolean res = demo.checkInclusion("ab", "eidbaooo");
//        boolean res = demo.checkInclusion("ab", "eidboaoo");
        System.out.println("res:" + res);
    }

    private class Counter extends HashMap<Character, Integer> {

        public Integer get(Character key) {
            return containsKey(key) ? super.get(key) : 0;
        }

        public void add(Character ch, int incre) {
            put(ch, get(ch) + incre);
            if (get(ch) == 0) {
                remove(ch);
            }
        }
    }

    /**
     * 2.使用Map保存区间字符的个数，然后与字符s1的字符个数进行比较是否相同
     */
    public boolean checkInclusion(String s1, String s2) {
        Counter counter1 = new Counter();
        char[] chars1 = s1.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            counter1.add(chars1[i], 1);
        }

        int left = -1;
        char[] chars2 = s2.toCharArray();
        Counter counter2 = new Counter();

        //使用一个变量记录counter2中每个字符的个数与counter1中的字符个数相等的数量
        int chNum = 0;

        for (int right = 0; right < chars2.length; right++) {
            char rightCh = chars2[right];
            counter2.add(rightCh, 1);   //区间右侧字符个数增加

            //右侧新增字符的个数 与 s1中的该字符的数量是否一样
            if (counter2.get(rightCh) == counter1.get(rightCh)) {
                chNum++;
            }

            if (right - left < chars1.length) {
                continue;
            }

            //判断count2 和 count1 中保存的字符个数是否相等,相等的数量与count1中的字符数量相等
            if (chNum == counter1.size()) {
                return true;
            }
            //区间左侧指针删除
            char leftCh = chars2[++left];   //左侧字符剔除，需要将count2中和 chNum减少
            if (counter2.get(leftCh) == counter1.get(leftCh)) {
                chNum--;
            }
            counter2.add(leftCh, -1);
        }

        return false;
    }

    /**
     * 1.理解题意
     * -输入两个字符串s1，s2，判断s2中的子串，与s1中的任意排列相同？
     * 2。解题思路
     * 双指针解法
     * -取双指针区间(left,right]切区间的长度等于s1字符串的长度，区间内的字符，等于s1字符串的任意排列
     * -控制条件有两个：一个是区间长度等于s1字符串的长度，另一个条件是区间范围的子串，等于s1字符串的排列
     * -如何判断区间的字符，与s1字符串相同呢，只要两个部分的字符数量相等即可
     */
    public boolean checkInclusion_v1(String s1, String s2) {
        //1。先求s1字符串的字符个数
        char[] chars1 = s1.toCharArray();
        int[] count1 = new int[256];
        for (int i = 0; i < chars1.length; i++) {
            count1[chars1[i]]++;
        }

        int left = -1;
        int[] count2 = new int[256];    // 表示s2字符数组中，区间范围的自负个数
        char[] chars2 = s2.toCharArray();
        for (int right = 0; right < chars2.length; right++) {
            count2[chars2[right]]++;

            //区间长度控制
            if (right - left < chars1.length) {
                continue;
            }

            //判断s2区间表示的字符个数与s1的自负个数是否相等
            boolean isSame = true;
            for (int i = 0; i < count1.length; i++) {
                isSame = count1[i] == count2[i];
                if (!isSame) {
                    break;
                }
            }
            //全比较完是否还相等，不想等，继续后面的区间判断
            if (isSame) {
                return true;
            }

            //将左侧left的自负从区间剔除，为右指针新增自负作准备
            count2[chars2[++left]]--;
        }
        return false;
    }

    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
     *
     * 示例 1：
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     *
     * 示例 2：
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     *  
     * 提示：
     * 输入的字符串只包含小写字母
     * 两个字符串的长度都在 [1, 10,000] 之间
     * 链接：https://leetcode-cn.com/problems/permutation-in-string
     */
}
