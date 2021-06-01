package com.timmy._review._08binary_search;

import com.timmy.common.PrintUtils;

public class _05山脉数组的峰顶索引_852 {

    public static void main(String[] args) {
        _05山脉数组的峰顶索引_852 demo = new _05山脉数组的峰顶索引_852();
//        int[] arr = {0, 2, 1, 0};
//        int[] arr = {3,4,5,1};
//        int[] arr = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        PrintUtils.print(arr);
        int res = demo.peakIndexInMountainArray(arr);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个山脉数组，找出山峰的元素下标位置
     * 2。解题思路
     * -山峰元素具有的特点是，比左右两边的元素的值都要大，
     * --如果遍历整个数组元素，比较两侧的元素值可求出结果，则时间复杂度为O(n)
     * 2。2。采用二分搜索法
     * -取中位数位置，判断该元素在数组中的情况
     * --上升期： A[i-1] < A[i] < A[i+1]   -- -1
     * --山峰值： A[i-1] < A[i] > A[i+1]   -- 0
     * --下降期： A[i-1] > A[i] > A[i+1]   -- 1
     * 根据上面中位数得到的三种情况，不断缩小检索范围，采用lowerBound模版方法
     */
    public int peakIndexInMountainArray(int[] arr) {
        int l = 1;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            int index = getMouInt(arr, mid);
            if (index < 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int getMouInt(int[] arr, int mid) {
        if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
            return -1;
        }
        if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) {
            return 1;
        }
        return 0;
    }

    /**
     * 符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
     *
     * 示例 1：
     * 输入：arr = [0,1,0]
     * 输出：1
     *
     * 示例 2：
     * 输入：arr = [0,2,1,0]
     * 输出：1
     *
     * 示例 3：
     * 输入：arr = [0,10,5,2]
     * 输出：1
     *
     * 示例 4：
     * 输入：arr = [3,4,5,1]
     * 输出：2
     *
     * 示例 5：
     * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
     * 输出：2
     *
     * 提示：
     * 3 <= arr.length <= 104
     * 0 <= arr[i] <= 106
     * 题目数据保证 arr 是一个山脉数组
     *
     * 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
     * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
     */
}
