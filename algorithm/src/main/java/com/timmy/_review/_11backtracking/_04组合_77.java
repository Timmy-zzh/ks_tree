package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _04组合_77 {

    public static void main(String[] args) {
        _04组合_77 demo = new _04组合_77();
        List<List<Integer>> res = demo.combine(4, 2);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -有1到n 一共n个数，从中选出k个数形成组合，求组合的集合
     * 2。解题思路
     * 回溯算法
     * -1个核心：第1个数可以选中n个数中的一个i，第2个数只能选择i+1到n后面的数字
     * -3个条件：
     * --满足结果条件：选中了2个元素
     * --终止条件
     * --第i个数如何选择
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combineR(n, 1, k, path, ans);
        return ans;
    }

    private void combineR(int n, int start, int k, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() >= k) {
            ans.add(new ArrayList<Integer>(path));
            return;
        }
        for (int j = start; j <= n; j++) {
            path.add(j);
            combineR(n, j + 1, k, path, ans);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 示例:
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * 链接：https://leetcode-cn.com/problems/combinations
     */
}
