package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _07颜色分类_75 {

    public static void main(String[] args) {
        _07颜色分类_75 demo = new _07颜色分类_75();
        int[] nums = {2, 0, 2, 1, 1, 0};
        PrintUtils.print(nums);
        demo.sortColors(nums);
        PrintUtils.print(nums);
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组元素由 0,1,2, 组成，现在要求按照按照 0，1，2，顺序排列，
     * 2。解题思路
     * -三路切分解法
     * --小于1
     * --等于1
     * --大于1
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = 0;
        int l = 0;
        int r = nums.length - 1;
        while (i <= r) {
            if (nums[i] < 1) {
                swapV(nums, i++, l++);
            } else if (nums[i] == 1) {
                i++;
            } else {
                swapV(nums, i, r--);
            }
        }
    }

    private void swapV(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 示例 1：
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     *
     * 示例 2：
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     *
     * 示例 3：
     * 输入：nums = [0]
     * 输出：[0]
     *
     * 示例 4：
     * 输入：nums = [1]
     * 输出：[1]
     *  
     * 提示：
     * n == nums.length
     * 1 <= n <= 300
     * nums[i] 为 0、1 或 2
     *
     * 进阶：
     * 你可以不使用代码库中的排序函数来解决这道题吗？
     * 你能想出一个仅使用常数空间的一趟扫描算法吗？
     * 链接：https://leetcode-cn.com/problems/sort-colors
     */
}
