package com.timmy._review._10greedy;

import com.timmy.common.PrintUtils;

/**
 * 贪心算法特点：
 * 1。只选局部最优解
 * 2。结果不可逆
 */
public class _01寻找数组中最大的元素 {

    public static void main(String[] args) {
        _01寻找数组中最大的元素 demo = new _01寻找数组中最大的元素();
        int[] nums = {1, 9, 8, 7, 3, 10, 6};
        PrintUtils.print(nums);
        int res = demo.maxValue(nums);
        System.out.println("res:" + res);
    }

    /**
     * 双指针解法：左右指针表示检索区间
     * -根据指针元素大小，指针元素值小的指针为止不断往中间移动，不断缩小检索区间范围
     */
    public int maxValue(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] > nums[r]) {
                r--;
            } else {
                l++;
            }
        }
        return nums[l];
    }

    /**
     * 1.理解题意
     * -输入一个数组，找出数组中元素值最大的元素
     * 2。解题思路
     * -预先设置一个最大值，遍历数组中的元素，将最大值与数组中的元素进行比较
     * --每次比较选出较大值作为最大值的变量值，
     */
    public int maxValue_v1(int[] nums) {
        int maxRes = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxRes) {
                maxRes = nums[i];
            }
        }
        return maxRes;
    }

}