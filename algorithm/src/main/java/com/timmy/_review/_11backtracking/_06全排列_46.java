package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _06全排列_46 {

    public static void main(String[] args) {
        _06全排列_46 demo = new _06全排列_46();
        int[] nums = {1, 2, 3};
        PrintUtils.print(nums);
        List<List<Integer>> res = demo.permute(nums);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 不用path集合进行数据的存储，在原先数组中进行交换swap，处理完后再交换回来，保证进出的数组结构恢复
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteR2(nums, 0, ans);
        return ans;
    }

    private void permuteR2(int[] nums, int i, List<List<Integer>> ans) {
        int N = nums == null ? 0 : nums.length;
        if (i == N) {
            ans.add(new ArrayList<Integer>(N));
            for (int num : nums) {
                ans.get(ans.size() - 1).add(num);
            }
        }

        if (i >= N) {
            return;
        }

        for (int j = i; j < N; j++) {
            swapR(nums, i, j);
            permuteR2(nums, i + 1, ans);
            swapR(nums, i, j);
        }
    }

    private void swapR(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 1.理解题意
     * -输入一个数组，输出数组的全排列
     * 2。解题思路
     * 回溯算法：
     * -1个核心：第i个人如何选择？
     * --第i个人选择了数组中的任意一个A[i],则第i+1个人可以选择除了A[i]元素外的其他任意一个元素
     * -3个条件：
     * --满足条件的结果集 - path路径山的数量等于原始数组的个数
     * --终止条件
     * --第i个人选项的逻辑 - 遍历原始数组，然后选择一个之前未选择的元素
     */
    public List<List<Integer>> permute_v1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        permuteR(nums, 0, path, ans);
        return ans;
    }

    private void permuteR(int[] nums, int i, List<Integer> path, List<List<Integer>> ans) {
        int N = nums == null ? 0 : nums.length;
        if (path.size() == N) {
            ans.add(new ArrayList<Integer>(path));
        }
        if (i >= N) {
            return;
        }
        for (int j = 0; j < N; j++) {
            if (!path.contains(nums[j])) {
                path.add(nums[j]);
                permuteR(nums, i + 1, path, ans);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出：[[1]]
     *
     * 提示：
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums 中的所有整数 互不相同
     *
     * 链接：https://leetcode-cn.com/problems/permutations
     */
}
