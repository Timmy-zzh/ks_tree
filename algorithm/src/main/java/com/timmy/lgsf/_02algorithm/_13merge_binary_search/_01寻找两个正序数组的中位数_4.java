package com.timmy.lgsf._02algorithm._13merge_binary_search;

import com.timmy.common.PrintUtils;

public class _01寻找两个正序数组的中位数_4 {

    public static void main(String[] args) {
        _01寻找两个正序数组的中位数_4 demo = new _01寻找两个正序数组的中位数_4();
        int[] nums1 = {1, 4};
        int[] nums2 = {2, 8};
//        int[] nums1 = {};
//        int[] nums2 = {2};
        double result = demo.findMedianSortedArrays(nums1, nums2);
        System.out.println("result:" + result);
    }

    /**
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     * <p>
     * 示例 1：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * <p>
     * 示例 2：
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * <p>
     * 示例 3：
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     * <p>
     * 示例 4：
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     * <p>
     * 示例 5：
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     * <p>
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     */

    /**
     * 二分查找法
     */
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }

    /**
     * 1。理解题意
     * -两个正序的数组，先合并两个数组为一个数组，然后查找新数组的中位数
     * 2。解题思路
     * -先合并，新建数组，遍历两个数组，将元素添加到新数组中
     * -然后找到中位数
     * 3。边界，细节问题
     * -遍历时，先将其中短的数组遍历完，然后接着遍历长数组
     */
    private double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        int m1 = nums1.length;
        int m2 = nums2.length;
        int[] newArr = new int[m1 + m2];
        int index = 0;

        int i = 0, j = 0;
        while (i < m1 && j < m2) {
            if (nums1[i] < nums2[j]) {
                newArr[index++] = nums1[i++];
            } else {
                newArr[index++] = nums2[j++];
            }
        }

        if (i < m1) {
            while (i < m1) {
                newArr[index++] = nums1[i++];
            }
        }

        if (j < m2) {
            while (j < m2) {
                newArr[index++] = nums2[j++];
            }
        }
        PrintUtils.print(newArr);
        if (newArr.length % 2 == 1) {
            return newArr[newArr.length / 2];
        } else {
            int mid = newArr.length / 2;
            return (newArr[mid - 1] + newArr[mid]) / 2.0;
        }
    }
}
