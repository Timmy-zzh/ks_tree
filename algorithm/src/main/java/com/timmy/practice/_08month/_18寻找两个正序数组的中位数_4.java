package com.timmy.practice._08month;

import com.timmy.common.PrintUtils;

public class _18寻找两个正序数组的中位数_4 {

    public static void main(String[] args) {
        _18寻找两个正序数组的中位数_4 demo = new _18寻找两个正序数组的中位数_4();
        int[] nums1 = {};
        int[] nums2 = {2};
//        int[] nums1 = {1, 3};
//        int[] nums2 = {2};
//        int[] nums1 = {1, 2};
//        int[] nums2 = {3, 4};
        double res = demo.findMedianSortedArrays(nums1, nums2);
        System.out.println("res:" + res);
    }

    /**
     * 2.二分查找法
     * -利用数组有序的特性
     * -
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }

    /**
     * 2.双指针解法
     * -分别使用一个变量，标示两个数组中分别遍历到的元素
     * -因为两个数组都是有序的，所以遍历过程中，只需要遍历到中位数位置即可
     * -使用一个变量保存遍历到元素的值，（偶数个需要两个变量用户保存）
     * 3.时间复杂度：O((m+n)/2)
     */
    public double findMedianSortedArrays_v2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int m1 = 0, m2 = 0;
        int p1 = 0, p2 = 0;
        int len = len1 + len2;
        for (int i = 0; i <= len / 2; i++) {  // m1在前，m2在后
            m1 = m2;
            if (p1 < len1 && (p2 >= len2 || nums1[p1] < nums2[p2])) {
                m2 = nums1[p1++];
            } else {
                m2 = nums2[p2++];
            }
        }
        System.out.println("m1:" + m1 + " ,m2:" + m2);
        if (len % 2 == 1) {
            return m2;
        }
        return (m1 + m2) / 2.0;
    }

    /**
     * 1.理解题意
     * -输入两个有序数组，找出来两个有序数组合在一起的中位数
     * --如果数组个数是奇数则中位数为中间位置的元素值；如果为偶数，中位数为两个中间元素的平均值
     * 2。解题思路
     * 2。1。暴力解法
     * -将两个数组进行合并为一个大数组，然后取中位数返回
     * -区分大数组个数奇偶数
     * 3.时间复杂度：O(m+n)
     */
    public double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int i = 0, j = 0;
        int[] nums = new int[len];
        for (int h = 0; h < len; h++) {
            if (i >= len1) {
                nums[h] = nums2[j++];
            } else if (j >= len2) {
                nums[h] = nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                nums[h] = nums1[i++];
            } else {
                nums[h] = nums2[j++];
            }
        }
        PrintUtils.print(nums);
        if (len % 2 == 1) {
            return nums[len / 2];
        }
        return (nums[len / 2 - 1] + nums[len / 2]) / 2.0;
    }

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 示例 1：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     *
     * 示例 2：
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     *
     * 示例 3：
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     *
     * 示例 4：
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     *
     * 示例 5：
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     *
     * 提示：
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     */
}
