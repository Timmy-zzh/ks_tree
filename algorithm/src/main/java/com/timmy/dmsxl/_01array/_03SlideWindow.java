package com.timmy.dmsxl._01array;

public class _03SlideWindow {

    public static void main(String[] args) {
        _03SlideWindow demo = new _03SlideWindow();
        int s = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        int result = demo.minSubArrayLen1(nums, s);
        int result2 = demo.minSubArrayLen(nums, s);
        System.out.println("result:" + result);
        System.out.println("result2:" + result2);
    }

    /**
     * 题目209.长度最小的子数组
     * <p>
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0。
     * <p>
     * 示例：
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * <p>
     * 实现思路：
     * 从数组中找出连续的自数组，满足子数组的和大于等于目标值，求长度最小子数组
     * 暴力破解法：
     * 两次for循环，外层决定子数组开始位置，内层for循环找到和大于目标值的子数组
     */
    private int minSubArrayLen1(int[] nums, int s) {
        int result = Integer.MAX_VALUE;
        int sum;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    result = Math.min(result, (j - i + 1));
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 解法二：双指针，滑动窗口解法
     * 当子数组的和大于目标值时，需要求最小数组长度
     */
    private int minSubArrayLen(int[] nums, int s) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= s) {
//                result = Math.min(result, (j - i + 1));
                result = result > (j - i + 1) ? (j - i + 1) : result;
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

}
