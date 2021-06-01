package com.timmy._review._08binary_search;

import com.timmy.common.PrintUtils;

public class _04搜索插入位置_35 {

    public static void main(String[] args) {
        _04搜索插入位置_35 demo = new _04搜索插入位置_35();
        int[] nums = {1, 3, 5, 6};
//        int target = 5;
        PrintUtils.print(nums);
        int res = demo.searchInsert(nums, 0);
        System.out.println("res:" + res);
    }

    /**
     * 1。输入一个有序数组和目标值，返回该目标值插入数组的位置
     * 2。二分搜索法，找第一个大于等于目标值的位置
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     *
     * 示例 2:
     * 输入: [1,3,5,6], 2
     * 输出: 1
     *
     * 示例 3:
     * 输入: [1,3,5,6], 7
     * 输出: 4
     *
     * 示例 4:
     * 输入: [1,3,5,6], 0
     * 输出: 0
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     */
}
