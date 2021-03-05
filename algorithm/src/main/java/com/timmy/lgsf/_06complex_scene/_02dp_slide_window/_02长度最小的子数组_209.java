package com.timmy.lgsf._06complex_scene._02dp_slide_window;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _02长度最小的子数组_209 {

    /**
     * 前缀和解法
     *
     * @param args
     */
    public static void main(String[] args) {
        _02长度最小的子数组_209 demo = new _02长度最小的子数组_209();
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        PrintUtils.print(nums);
        int res = demo.minSubArrayLen(target, nums);
        System.out.println("res:" + res);
    }

    /**
     * 滑动窗口优化版
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int N = nums.length;
        int res = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = 0;
        while (r < N) {
            //加上后面的元素
            sum += nums[r];
            //当大于目标值时，前面减少
            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 1.理解题意
     * -输入一个正整数数组，查找连续子数组，子数组的和>= 目标值，求长度最小的子数组
     * 2.解题思路：滑动窗口+双指针
     * 长度不固定,定义左右指针，求从左指针到右指针的和，
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen_v1(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int N = nums.length;
        if (nums[0] >= target) {
            return 1;
        }
        int l = 0, r = 1;
        int sum = nums[0];
        while (r < N) {
            while (sum < target) {
                if (r == N) {
                    break;
                }
                sum += nums[r];
                r++;
            }
            System.out.println("--- l:" + l + " ,r:" + r + " ,sum:" + sum);
            res = Math.min(res, r - l);

            //前面减少
            while (sum >= target) {
                System.out.println("==== l:" + l + " ,r:" + r + " ,sum:" + sum);
                res = Math.min(res, r - l);
                sum -= nums[l];
                l++;
            }
            System.out.println(">>>>>> l:" + l + " ,r:" + r + " ,sum:" + sum);
        }
        return res;
    }


    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     * 示例 1：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     *
     * 示例 2：
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     *
     * 示例 3：
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     *
     * 提示：
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     *  
     * 进阶：
     * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
     *
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
     */
}
