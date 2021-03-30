package com.timmy._00review._00binary_search;

import com.timmy.common.PrintUtils;

public class _02在排序数组中查找元素的第一个和最后一个位置_34 {

    public static void main(String[] args) {
        _02在排序数组中查找元素的第一个和最后一个位置_34 demo = new _02在排序数组中查找元素的第一个和最后一个位置_34();
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] res = demo.searchRange(nums, 8);
        PrintUtils.print(res);
    }

    /**
     * 二分搜索先找到目标值，然后往两边扩展找到左右边界下标值
     */
    public int[] searchRange(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int middle;
        int left = -1, right = -1;
        while (low <= high) {
            middle = (low + high) / 2;
            if (target == nums[middle]) {
                left = right = middle;
                //寻找左右边界
                while (left > 0 && nums[left - 1] == target) {
                    left--;
                }
                while (right < nums.length - 1 && nums[right + 1] == target) {
                    right++;
                }
                break;
            }
            if (target < nums[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return new int[]{left, right};
    }

    /**
     * 1.理解题意
     * -输入一个有序数组nums，和一个目标值target，查找目标值在数组中出现的开始和结束位置
     * 2。解题思路
     * -二分搜索法，分别查找目标值的左边界和右边界位置，
     * --其中左边界位置的值满足，当前值等于target，且左边一位的值小于目标值，右边界值采用同样的规则
     */
    public int[] searchRange_v1(int[] nums, int target) {
        return new int[]{getLeftBound(nums, target, 0, nums.length - 1),
                getRightBound(nums, target)};
    }

    /**
     * 求目标值在数组nums中的左边界--目标值在nums数组中的开始出现的位置
     * --递归法实现
     */
    private int getLeftBound(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = (low + high) / 2;
        if (target == nums[middle] && (middle == 0 || nums[middle - 1] < target)) {
            return middle;
        }
        if (target <= nums[middle]) {
            return getLeftBound(nums, target, low, middle - 1);
        } else {
            return getLeftBound(nums, target, middle + 1, high);
        }
    }

    /**
     * 求目标值在数组中右边界出现的位置，--迭代法实现
     */
    private int getRightBound(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int middle;
        while (low <= high) {
            middle = (low + high) / 2;
            if (target == nums[middle] && (middle == nums.length - 1 || nums[middle + 1] > target)) {
                return middle;
            }
            if (target >= nums[middle]) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
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
     *
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     */
}
