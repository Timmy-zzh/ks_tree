package com.timmy._review._09double_point;

import com.timmy.common.PrintUtils;

public class _06乘积小于K的子数组_713 {

    public static void main(String[] args) {
        _06乘积小于K的子数组_713 demo = new _06乘积小于K的子数组_713();
        int[] nums = {10, 5, 2, 6};
        PrintUtils.print(nums);
        int res = demo.numSubarrayProductLessThanK(nums, 100);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个正整数数组，求其中的子数组个数，要求子数组的元素乘积小于k
     * 2。解题思路
     * -双指针表示区间，右侧指针不断往后移动，使用一个变量保存区间范围的乘积，当乘积大于值k时，移动左侧指针，并且乘积值减少
     * -题目要求的是子数组的个数，满足条件的时候，新增一个子数组数量
     * 3.总结：
     * -右侧指针不断往后移动，左侧指针只有当区间乘积大于k时往右移动
     * -求区间范围中子数组的数量=righ -left，以为新增的子数组需要以 右指针为右边界，个数刚好是right - left的长度
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = -1;
        int res = 0;
        int sum = 1;

        for (int right = 0; right < nums.length; right++) {
            sum *= nums[right];

            while (sum >= k && left < right) {
                sum /= nums[++left];
            }
            System.out.println("left:" + left + " ,right:" + right);
            res += right - left;
        }

        return res;
    }

    /**
     * 给定一个正整数数组 nums。
     * 找出该数组内乘积小于 k 的连续的子数组的个数。
     *
     * 示例 1:
     * 输入: nums = [10,5,2,6], k = 100
     * 输出: 8
     * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
     *
     * 说明:
     * 0 < nums.length <= 50000
     * 0 < nums[i] < 1000
     * 0 <= k < 10^6
     * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
     */
}
