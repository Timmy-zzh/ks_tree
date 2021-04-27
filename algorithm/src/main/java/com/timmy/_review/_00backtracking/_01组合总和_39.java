package com.timmy._review._00backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

class _01组合总和_39 {

    public static void main(String[] args) {
        _01组合总和_39 demo = new _01组合总和_39();
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> res = demo.combinationSum(candidates, 7);
        System.out.println("----");
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * - 输入一个整数数组和一个目标值，求将数组中的元素相加结果为目标值target
     * --数组中的元素可以无限重复使用
     * 2。解题思路：回溯算法
     * 递归实现：
     * -遍历数组中每个元素，单层递归逻辑中，将数组中的元素进行添加求和，并进行回溯
     * -递归三要素：
     * --入参与返回值
     * --终止条件，递归方法调用时target不断递减，知道小于0（剪枝），或target等于0（找到所求结果）
     * --单层递归逻辑实现：不断添加数组中其他元素，并进行回溯
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        PrintUtils.print(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> itemRes = new ArrayList<>();
        backtrack(candidates, res, itemRes, target, 0);
        return res;
    }

    private void backtrack(int[] candidates, List<List<Integer>> res, List<Integer> itemRes,
                           int target, int start) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            List<Integer> item = new ArrayList<>(itemRes);
            res.add(item);
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            itemRes.add(candidates[i]);
            backtrack(candidates, res, itemRes, target - candidates[i], i);
            itemRes.remove(itemRes.size() - 1);
        }
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
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
