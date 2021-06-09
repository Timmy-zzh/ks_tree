package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _03子集_78 {

    public static void main(String[] args) {
        _03子集_78 demo = new _03子集_78();
        int[] nums = {1, 2, 3};
        PrintUtils.print(nums);
        List<List<Integer>> res = demo.subsets(nums);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组中的元素互不相同，返回该数组所有可能的子集
     * 2。解题思路
     * 回溯算法
     * -1个核心：第i个元素怎么选择？由于不能选择相同的元素，所以使用set集合保存已经选中的元素，
     * --i选择了第j个元素，第i+1只能选择第j+1后面的元素
     * -3个条件：
     * --满足条件的集合，遍历到数组最后一个元素
     * --终止条件
     * --单层递归逻辑
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        subR(nums, 0, path, ans);
        return ans;
    }

    private void subR(int[] nums, int start, List<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));

        if (start >= nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            subR(nums, i + 1, path, ans);       //这里选择的下一层遍历的开始位置为：i+1
            path.remove(path.size() - 1);
        }
    }

    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *
     * 提示：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     * 链接：https://leetcode-cn.com/problems/subsets
     */
}
