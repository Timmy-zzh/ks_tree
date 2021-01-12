package com.timmy.lgsf._11sort1;

import com.timmy.common.PrintUtils;

public class _03按奇偶排序数组_922 {

    public static void main(String[] args) {

        _03按奇偶排序数组_922 demo = new _03按奇偶排序数组_922();
//        int[] nums = new int[]{4, 2, 5, 7};
//        int[] nums = new int[]{1, 2, 5, 8};
        int[] nums = new int[]{3, 1, 6, 8};
        demo.sortArrayByParityII(nums);
        PrintUtils.print(nums);
    }

    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * 你可以返回任何满足上述条件的数组作为答案。
     * <p>
     * 示例：
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     * <p>
     * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
     */

    /**
     * 要实现下标i为奇数，nums[i]的值也是奇数
     * 要实现下标j为偶数，nums[j]的值也是偶数
     * <p>
     * 实现:双指针法
     * 奇位数上找偶数位的数据交换，否则相反
     * 1 2 5 8
     */
    public int[] sortArrayByParityII(int[] nums) {
        for (int i = 0, j = 1; i < nums.length; i += 2) {
            if (nums[i] % 2 == 1) {    // 偶数位等于奇数
                //找到第一个奇数位是偶数的，然后交换
                while (j < nums.length && nums[j] % 2 == 1) {
                    j += 2;
                }
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }
}
