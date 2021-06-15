package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _08子集2_90 {

    public static void main(String[] args) {
        _08子集2_90 demo = new _08子集2_90();
        int[] nums = {1, 2, 2};
        PrintUtils.print(nums);
        List<List<Integer>> res = demo.subsetsWithDum(nums);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组中的元素存在重复，现要求该数组所有的子集，且不能出现重复的子集，
     * --例如输入数组[1,2,2] 存在子集[1,2(A[1])], [1,2(A[2])] 虽然第二个元素下标不同，但是值相同，任务两个子集重复
     * 2。解题思路
     * -先对数组进行升序排序处理，要求所有的子集，需要将所有的路径节点添加到结果集中
     * -1个核心：第i个用户选择了第A[j]元素，第i+1用户只能选择后面【j+1，N）中其他的一个元素
     * -3个条件：递归
     * --满足条件，
     * --终止条件
     * --第i个用户的选项
     * -去除重复项：排序+前后元素比较
     */
    public List<List<Integer>> subsetsWithDum(int[] nums) {
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        subsetsR(nums, 0, box, ans);
        return ans;
    }

    private void subsetsR(int[] nums, int start, List<Integer> box, List<List<Integer>> ans) {
        ans.add(new ArrayList<Integer>(box));
        if (start >= nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            //去除重复项
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            box.add(nums[i]);
            //第i+1 用户的选项为j+1
            subsetsR(nums, i + 1, box, ans);
            box.remove(box.size() - 1);
        }
    }

    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     * 示例 1：
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *
     * 提示：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * 链接：https://leetcode-cn.com/problems/subsets-ii
     */
}
