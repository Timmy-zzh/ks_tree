package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _09全排列2_47 {

    public static void main(String[] args) {
        _09全排列2_47 demo = new _09全排列2_47();
        int[] nums = {1, 1, 1, 2};
        PrintUtils.print(nums);
        List<List<Integer>> res = demo.permuteUnique(nums);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组元素可能重复，现在需要求该数组的全排列
     * 2。解题思路
     * -求全排列，需要将路径上的所有元素保存到结果集合中
     * 回溯：
     * -1个核心：第i个人选择了A[j]元素，第i+1个人可以选择除A[j]元素的其他元素
     * -3个条件：
     * --满足条件：路径上的元素个数等于数组元素个数
     * --终止条件
     * --第i个元素的选项：数组中已选中元素的其他元素（单条路径上的不重复，使用visited数组控制判断）
     * -不重复全排列：排序 + 当前选择元素的前后值比较
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteR(nums, 0, visited, box, ans);
        return ans;
    }

    private void permuteR(int[] nums, int i, boolean[] visited, List<Integer> box, List<List<Integer>> all) {
        if (box.size() == nums.length) {
            all.add(new ArrayList<Integer>(box));
        }
        if (box.size() >= nums.length) {
            return;
        }

        for (int j = 0; j < nums.length; j++) {
            if (visited[j]) {
                continue;
            }
            if (j > 0 && nums[j] == nums[j - 1] && !visited[j - 1]) {
                continue;
            }
            visited[j] = true;
            box.add(nums[j]);
            permuteR(nums, j + 1, visited, box, all);
            box.remove(box.size() - 1);
            visited[j] = false;
        }
    }

    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     *
     * 示例 2：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 提示：
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     * 链接：https://leetcode-cn.com/problems/permutations-ii
     */
}
