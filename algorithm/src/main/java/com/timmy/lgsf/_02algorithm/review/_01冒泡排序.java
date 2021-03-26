package com.timmy.lgsf._02algorithm.review;

import com.timmy.common.PrintUtils;

public class _01冒泡排序 {

    public static void main(String[] args) {
        _01冒泡排序 demo = new _01冒泡排序();
        int[] nums = {2, 1, 7, 9, 5, 8};
        PrintUtils.print(nums);
        demo.bubble(nums);
        System.out.println("---");
        PrintUtils.print(nums);
    }

    /**
     * 冒泡排序：
     * -遍历数组元素，每一轮遍历都将最大元素移动到数组尾部
     * -两层for循环，外层循环控制当前是第几轮排序
     * -内层for循环，从下标0开始，比较j与j+1的大小，然后将更大值元素移动到后面
     */
    public void bubble(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
