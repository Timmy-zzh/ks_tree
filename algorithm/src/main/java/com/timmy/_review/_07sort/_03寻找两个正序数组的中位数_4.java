package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _03寻找两个正序数组的中位数_4 {

    public static void main(String[] args) {
        _03寻找两个正序数组的中位数_4 demo = new _03寻找两个正序数组的中位数_4();
//        int[] num1 = {1, 3, 5};
//        int[] num2 = {2, 4};
//        int[] num1 = {1, 2};
//        int[] num2 = {3, 4};

        int[] num1 = {1, 4};
        int[] num2 = {2, 3, 5, 6, 7};
        double res = demo.findMedianSortedArrays(num1, num2);
        System.out.println("res:" + res);
    }

    /**
     * 2.中位数排除法
     * 利用两个数组都有序的特性进行处理
     * -假设两个数组的总长度是9，那他的中位数就是第5大的数字，题目变成了求两个数组中的第5大的元素，
     * --可以采用排除法，要求合并数组中第5大的元素，需要将数组中前面4个小的元素删除掉（9-1）/2
     * -在对4进行分配，两个数组中每个数组取前面两个部分进行比较，p = (4-1)/2 = 1
     * --对A[p] 和 B[p] 两个元素进行比较，较小的元素部分，需要将该部分的元素进行删除
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;  //两数组总长度
        int i = 0, j = 0;       //i，j 分别为指向两个数组的位置

        //中位数前面的数字需要删除，k表示需要删除的元素个数
        int k = (len - 1) / 2;
        //每次取两部分的k/2的中间位置元素进行大小比较， 每次删除 k/2 个元素，
        while (k > 0) {
            //关键：k，i，j，p的含义
            int p = (k - 1) / 2;        // p相当于步长-1，每次比较后都往后移动p个位置，然后i，j也来到新的比较位置

            if (p + j >= len2 || p + i < len1 && nums1[i + p] < nums2[j + p]) {
                // i部分元素小，去要删除该部分
                i += p + 1;
            } else {
                j += p + 1;
            }
            k -= p + 1;
        }

        //如果len 为奇数,i,j位置的更小值
        int front = (j >= len2 || i < len1 && nums1[i] < nums2[j]) ? nums1[i++] : nums2[j++];
        if (len % 2 == 1) {
            return front;
        }
        int back = (j >= len2 || i < len1 && nums1[i] < nums2[j]) ? nums1[i] : nums2[j];
        return (back + front) / (2 * 1.0d);
    }

    /**
     * 1.理解题意
     * -输入两个有序数组，求这两个正序数组的中位数，中位数是两个数组合并后的中位数
     * -如果数组元素个数为奇数，则中位数为中间的元素；如果数组个数为偶数，则中位数是数组中两个中间元素的和平均数
     * 2。解题思路
     * 暴力解法1：
     * -先将两个有序数组进行合并，合并后求中位数
     */
    public double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] arr = new int[len1 + len2];
        int i = 0, j = 0, index = 0;
        while (i < len1 || j < len2) {
            if (j >= len2 || i < len1 && nums1[i] < nums2[j]) {
                arr[index++] = nums1[i++];
            } else {
                arr[index++] = nums2[j++];
            }
        }
        PrintUtils.print(arr);
        int len = arr.length;
        if (len % 2 == 1) { //奇数
            return arr[len / 2];
        }

        return (arr[len / 2 - 1] + arr[len / 2]) / (2 * 1d);
    }

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数 。
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
