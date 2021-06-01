package com.timmy._review._08binary_search;

import com.timmy.common.PrintUtils;

public class _01二分搜索 {

    public static void main(String[] args) {
        _01二分搜索 demo = new _01二分搜索();
        long[] nums = {1, 3, 4, 9, 11, 15, 19, 21};
        PrintUtils.print(nums);
        boolean res = demo.binarySearch(nums, 3);
        System.out.println("res:" + res);
    }

    public boolean binarySearch(long[] nums, long target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int l = 0;
        int r = nums.length;

        //开闭区间 [l,r) -- 左闭右开
        while (l < r) {
            System.out.println("left:" + l + " ,right:" + r);
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return true;
            }

            if (target < nums[mid]) {       // 左区间 [l,mid)
                r = mid;
            } else {
                l = mid + 1;        // 右区间，中间值不去要考虑[mid+1,r]
            }
        }
        return false;
    }

    /**
     * 1。理解题意
     * -输入一个有序数组，和要求查找的目标值，求该目标值target在数组中是否存在
     * 2。解题思路
     * 二分搜索法：一切有序数组都可以使用二分搜索法
     * -确定检索区域，求中位数，然后判断中位数与目标值的大小  mid = (left+right)/2
     * -目标值等于中位数，直接返回true
     * -如果目标值比中位数小，目标值在检索左半区间，right = mid -1
     * -如果目标值大于中位数，则目标值在检索右半区间，left = mid+1
     */
    public boolean binarySearch_v1(long[] nums, long target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int l = 0;
        int r = nums.length - 1;

        //开闭区间 [l,r] -- 左闭右闭
        while (l <= r) {
            System.out.println("left:" + l + " ,right:" + r);
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return true;
            }

            if (target < nums[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    /**
     * 二分搜索的目的是在一个有序的数组 A 里面，找到一个给定的数。比如我们想要在下面的数组里面查找 target=3。
     */
}
