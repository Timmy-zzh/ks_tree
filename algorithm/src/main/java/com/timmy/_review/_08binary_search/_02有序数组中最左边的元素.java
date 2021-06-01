package com.timmy._review._08binary_search;

import com.timmy.common.PrintUtils;

public class _02有序数组中最左边的元素 {

    public static void main(String[] args) {
        _02有序数组中最左边的元素 demo = new _02有序数组中最左边的元素();
        long[] nums = {1, 2, 2, 2, 2, 3, 3};
        PrintUtils.print(nums);
        int res = demo.lowerBound(nums, 2);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个有序数组和目标值，返回目标值在数组中出现的最左侧位置
     * 2。解题思路
     * -二分搜索法，取中位数与目标值进行比较
     * -开闭原则：左闭右开
     * --如果目标值小于等于中位数，左区间： r = mid
     * --如果目标值大于中位数，右区间： l = mid + 1
     * 3.总结
     * -返回的值，要么是等于target
     * -要么是第一个大于target的第一个值
     */
    public int lowerBound(long[] nums, long target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length;
        while (l < r) {
            System.out.println("left:" + l + " ,right:" + r);
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {       //中位数小于目标值，检索范围在右侧
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * 左闭右闭区间检索
     */
    public int lowerBound_err(long[] nums, long target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            System.out.println("left:" + l + " ,right:" + r);
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {
                l = mid + 1;
            } else {            // error：相等情况会出现死循环
                r = mid;
            }
        }
        return l;
    }

    /**
     * 给定一个有序数组，返回指定元素在数组的最左边的位置
     * 输入：A = [1, 2, 2, 2, 2, 3, 3], target = 2
     * 输出：1
     *
     * 解释：第一个出现的 2 位于下标 1，是从左往右看时，第一个出现 2 的位置。
     */
}
