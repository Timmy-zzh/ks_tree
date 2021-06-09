package com.timmy._review._11backtracking;


import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _05组合总和_39 {

    public static void main(String[] args) {
        _05组合总和_39 demo = new _05组合总和_39();
        int[] candidates = {2, 3, 6, 7};
        PrintUtils.print(candidates);
        List<List<Integer>> res = demo.combinationSum(candidates, 7);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一个数组和目标值，数组元素都是正整数，现在从数组中选出一个组合使得组合元素的和等于目标值，数组中的元素可以重复使用
     * 2。解题思路
     * 回溯算法
     * -核心：结果中不能包含同样的集合
     * --第i个元素可以从数组中选取任意元素i，第i+1个元素同样可以选择数组中任意元素[i,n)
     * --第i+1的元素可以重复选择前面的元素，但是不能选择i之前的元素，否则会出现相同的集合
     * -三个条件：
     * --满足条件：组合之和等于目标值
     * --终止条件：和等于0或小于0了
     * --单层i选择逻辑
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combinationR(candidates, target, 0, path, ans);
        return ans;
    }

    private void combinationR(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> ans) {
        if (target <= 0) {
            if (target == 0) {
                ans.add(new ArrayList<Integer>(path));
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            combinationR(candidates, target - candidates[i], i, path, ans);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     *
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     *
     * 示例 2：
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * 提示：
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     *
     * 链接：https://leetcode-cn.com/problems/combination-sum
     */

}
