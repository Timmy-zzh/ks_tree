package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _05合并两个有序数组_88 {

    public static void main(String[] args) {
        _05合并两个有序数组_88 demo = new _05合并两个有序数组_88();
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        demo.merge(num1, 3, num2, 3);
        PrintUtils.print(num1);
    }

    /**
     * 1。理解题意
     * -输入两个数组，其中数组1的个数足够大，现要求将数组2中的元素合并到数组1，中并且新数组还是保持有序性
     * 2。解题思路
     * -数组1的元素个数为m，数组2的元素个数为n，新数组的元素个数为m+n
     * -从m+n-1 的位置开始往前存放元素，先放元素值更大的元素，然后不断往前移动
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;

        while (i >= 0 || j >= 0) {
            if (j < 0 || i >= 0 && nums1[i] > nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，
     * 这样它就有足够的空间保存来自 nums2 的元素。
     *  
     * 示例 1：
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     *
     * 示例 2：
     * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
     * 输出：[1]
     *
     * 提示：
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -109 <= nums1[i], nums2[i] <= 109
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     */
}
