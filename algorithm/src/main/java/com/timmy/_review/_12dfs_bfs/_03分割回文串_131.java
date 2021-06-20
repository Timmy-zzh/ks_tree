package com.timmy._review._12dfs_bfs;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _03分割回文串_131 {

    public static void main(String[] args) {
        _03分割回文串_131 demo = new _03分割回文串_131();
        List<List<String>> res = demo.partition("aab");
        for (List<String> re : res) {
            PrintUtils.printStr(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一个字符串，对该字符串进行字符分割，分割的子串要求是回文串，分割的位置有多种组合，求原始字符串分割后的子串组合情况
     * 2。解题思路
     * 动态规划 + 回溯算法
     * -因为要将字符串分割后需要是 回文串，所以通过动态规划算法先确定那些子串是回文串
     * -然后根据子回文串进行分割
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        build(s, dp);
        PrintUtils.print(dp);

        List<String> box = new ArrayList<>();
        parR(s, dp, box, res, 0);
        return res;
    }

    /**
     * 不断从开始位置start遍历所有的子串，判断 [start,N] 范围内的子串是否是回文串，如果是的话添加到box中保存
     *
     * @param s
     * @param dp
     * @param box
     * @param res
     * @param start
     */
    private void parR(String s, boolean[][] dp, List<String> box, List<List<String>> res, int start) {
        System.out.println("start:" + start);
        if (start == s.length()) {
            res.add(new ArrayList<String>(box));
        }
        if (start >= s.length()) {
            return;
        }
        for (int cur = start; cur < s.length(); cur++) {
            if (dp[start][cur]) {
                String word = s.substring(start, cur + 1);
                System.out.println(word);
                box.add(word);
                parR(s, dp, box, res, cur + 1);
                box.remove(box.size() - 1);
            }
        }
    }

    /**
     * 对字符串进行分割，并求子串是否是回文串
     * 其中dp[i][j] 表示字符串i～j位置，其中boolean值标示是否是回文串
     * -情况1：长度为1的子串，是回文串
     * -情况2：长度为2的子串，需要前后两个字符相等才是回文串
     * -情况3：长度大于2的子串，需要前后两个字符相等，且dp[i+1][j-1] 是回文串才行
     *
     * @param s
     * @param dp
     */
    private void build(String s, boolean[][] dp) {
        int N = s.length();
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i + 1 < N; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        //长度大于2的子串 -- 长度不断变化
        for (int len = 2; len < N; len++) {
            //不同长度下，开始位置从i 开始到 N-len  -->[i,N-len]
            for (int i = 0; i + len < N; i++) {
                int j = i + len;
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
    }

    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * 回文串 是正着读和反着读都一样的字符串。
     *
     * 示例 1：
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     *
     * 示例 2：
     * 输入：s = "a"
     * 输出：[["a"]]
     *
     * 提示：
     * 1 <= s.length <= 16
     * s 仅由小写英文字母组成
     *
     * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
     */
}
