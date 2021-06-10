package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _05组合总和2_40 {

    public static void main(String[] args) {
        _05组合总和2_40 demo = new _05组合总和2_40();
//        int[] candidates = {2, 3, 6, 7};
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        PrintUtils.print(candidates);
        List<List<Integer>> res = demo.combinationSum2(candidates, 8);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        PrintUtils.print(candidates);
        combinationR(candidates, target, 0, path, ans);
        return ans;
    }

    private void combinationR(int[] candidates, int target, int start,
                              List<Integer> path, List<List<Integer>> ans) {
        if (target <= 0) {
            if (target == 0) {
                ans.add(new ArrayList<Integer>(path));
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                // i>start 控制了当前元素可以选择，当前元素与前一个元素相等则避免了相等的结果集出现
                continue;
            }
            path.add(candidates[i]);
            combinationR(candidates, target - candidates[i], i + 1, path, ans);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     *
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     *
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     * 链接：https://leetcode-cn.com/problems/combination-sum-ii
     */
}
