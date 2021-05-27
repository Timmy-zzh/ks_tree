package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _08移动零_283 {

    public static void main(String[] args) {
        _08移动零_283 demo = new _08移动零_283();
        int[] nums = {0, 1, 0, 3, 12};
        PrintUtils.print(nums);
        demo.moveZeroes(nums);
        PrintUtils.print(nums);
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组元素由 0 和 非0 组成，要求将元素0都移动到数组末尾，非0的元素还是保持原先的顺序
     * 2。解题思路
     * 2。1。双指针解法：
     * -i指向0的元素，j指向元素非0，
     */
    public void moveZeroes(int[] nums) {
        int N = nums.length;
        int i = 0, j = 0;
        while (j < N) {
            if (nums[j] == 0) {
                j++;
            } else {
                swapV(nums, i, j);
                i++;
                j++;
            }
        }
    }

    private void swapV(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     */
}
