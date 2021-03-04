package com.timmy.lgsf._05backtrack_dp._1backtrack;

import java.util.Arrays;
import java.util.List;

public class _02串联字符串的最大长度_1239 {

    public static void main(String[] args) {
        _02串联字符串的最大长度_1239 demo = new _02串联字符串的最大长度_1239();
//        String[] arr = {"cha", "r", "act", "ers"};
//        String[] arr = {"un", "iq", "ue"};
        String[] arr = {"yy", "bkhwmpbiisbldzknpm"};
        int res = demo.maxLength(Arrays.asList(arr));
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串数组，从数组中取出n个字符串组成新的字符串s，且新字符串s中的每个字符只出现一次
     * 2。解题思路-使用动态规划的思路
     * -递归遍历每个字符串，对待这个字符串有两种情况，添加or不添加，--如果添加需要判断是否满足组成新字符串s的条件
     * -遍历到最后查看最长字符串的结果
     *
     * @param arr
     * @return
     */
    String res = "";

    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        backtrack(arr, 0, "");
        return res.length();
    }

    private void backtrack(List<String> arr, int index, String str) {
        System.out.println("str:" + str);
        if (str.length() > res.length()) {
            res = str;
        }
        if (index == arr.size()) {
            return;
        }
        //添加 or 不添加
        //是否可以添加
        if (valid(str, arr.get(index))) {
            System.out.println("valid true:" + arr.get(index));
            backtrack(arr, index + 1, str + arr.get(index));
            backtrack(arr, index + 1, str);
        } else {
            System.out.println("valid false:" + arr.get(index));
            backtrack(arr, index + 1, str);
        }
    }

    //查看两个字符串中是否有相同的字符
    private boolean valid(String str, String item) {
        //item中不能有相同的字符
        int[] let = new int[26];
        for (int i = 0; i < item.length(); i++) {
            char ch = item.charAt(i);
            let[ch - 'a']++;
        }
        for (int i = 0; i < let.length; i++) {
            if (let[i] > 1) {
                return false;
            }
        }

        for (int i = 0; i < item.length(); i++) {
            char ch = item.charAt(i);
            if (str.indexOf(ch) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，
     * 那么它就是一个可行解。
     * 请返回所有可行解 s 中最长长度。
     *
     * 示例 1：
     * 输入：arr = ["un","iq","ue"]
     * 输出：4
     * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
     *
     * 示例 2：
     * 输入：arr = ["cha","r","act","ers"]
     * 输出：6
     * 解释：可能的解答有 "chaers" 和 "acters"。
     *
     * 示例 3：
     * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
     * 输出：26
     *
     * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
     */
}
