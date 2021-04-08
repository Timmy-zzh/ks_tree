package com.timmy._00review._04month;

import com.timmy.common.PrintUtils;

class _08寻找两个正序数组的中位数_4 {

    public static void main(String[] args) {
        _08寻找两个正序数组的中位数_4 demo = new _08寻找两个正序数组的中位数_4();
//        int[] nums1 = {1, 3};
//        int[] nums2 = {2};
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double res = demo.findMedianSortedArrays(nums1, nums2);
        System.out.println("rs:" + res);
    }

    /**
     * 2.解法二：分治法
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * 1.理解题意
     * -输入有序数组，求两个正序数组的中位数
     * -是两个数组排序后的中位数
     * 2。解题思路
     * -先将两个有序数组归并成一个有序数组，然后再求新的有序数组的中位数
     * --中位数：区分数组大小是奇数还是偶数
     */
    public double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        PrintUtils.print(nums1);
        PrintUtils.print(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int left = 0, right = 0;

        int[] newArr = new int[len1 + len2];
        int index = 0;
        while (index < newArr.length) {
            if (left == len1) {
                newArr[index++] = nums2[right++];
            } else if (right == len2) {
                newArr[index++] = nums1[left++];
            } else if (nums1[left] < nums2[right]) {
                newArr[index++] = nums1[left++];
            } else {
                newArr[index++] = nums2[right++];
            }
        }
        PrintUtils.print(newArr);
        double res;
        int mid = newArr.length / 2;
        if (newArr.length % 2 != 0) {   //奇数
            res = newArr[mid];
        } else {
            res = (newArr[mid] + newArr[mid - 1]) / 2.0;
        }
        return res;
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
     *  
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     */
}
