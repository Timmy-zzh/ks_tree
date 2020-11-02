package com.timmy.dmsxl._01array;

public class _01BinaryFunc {

    public static void main(String[] args) {
        _01BinaryFunc binaryFunc = new _01BinaryFunc();
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        int index = binaryFunc.searchInsert1(nums, target);
        int index2 = binaryFunc.searchInsert(nums, target);
        System.out.println("result:" + index);
        System.out.println("result2:" + index2);

    }

    /**
     * 编号35：搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * <p>
     * 实现思路：
     * 解法一：暴力破解法
     * 遍历有序数组，找到比target大的元素，然后返回下标,复杂度为O(n)
     */
    private int searchInsert1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 二分法 : O(log n)
     * left，mid，right
     * 边界处理
     */
    private int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right + 1;
    }
}
