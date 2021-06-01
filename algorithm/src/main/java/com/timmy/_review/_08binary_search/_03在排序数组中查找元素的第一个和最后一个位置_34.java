package com.timmy._review._08binary_search;

import com.timmy.common.PrintUtils;

public class _03在排序数组中查找元素的第一个和最后一个位置_34 {

    public static void main(String[] args) {
        _03在排序数组中查找元素的第一个和最后一个位置_34 demo = new _03在排序数组中查找元素的第一个和最后一个位置_34();
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 8;
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 6;
        int[] nums = {1};
        int target = 0;

        int[] res = demo.searchRange(nums, target);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -输入一个有序数组和目标值，求该目标值在数组中出现的最开始和结束的位置，没有出现返回-1
     * 2。解题思路
     * -二分搜索法，分开来查找
     * -先找到目标值最开始出现的位置，
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int startIndex = findStart(nums, target);
        startIndex = nums[startIndex] == target ? startIndex : -1;
        System.out.println("startIndex:" + startIndex);
        int lastIndex = findLast(nums, target) - 1;
        lastIndex = lastIndex < nums.length && nums[lastIndex] == target ? lastIndex : -1;
        System.out.println("lastIndex:" + lastIndex);
        return new int[]{startIndex, lastIndex};
    }

    private int findLast(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] <= target) {      //寻找右边区域，先处理左边区域
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int findStart(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {       // 寻找左边区域，先判断右边区域
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 进阶：
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     *
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     *
     * 示例 3：
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *  
     * 提示：
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     */
}
