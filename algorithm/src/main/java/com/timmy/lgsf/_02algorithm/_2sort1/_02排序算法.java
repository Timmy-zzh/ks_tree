package com.timmy.lgsf._02algorithm._2sort1;

import com.timmy.common.PrintUtils;

public class _02排序算法 {

    public static void main(String[] args) {
        _02排序算法 demo = new _02排序算法();
        int[] nums = new int[]{12, 8, 9, 32, 48, 16, 5};
//        demo.bubalSort(nums);
//        demo.insertSort(nums);
        demo.quickSort(nums);
        PrintUtils.print(nums);
    }

    /**
     * 冒泡排序
     * 两层for循环，内层循环，相邻元素不断比较，不断将最大的元素移动到数组后面
     */
    public void bubalSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * 将数组分成两部分，已排好序的一部分和原始数组部分
     * 遍历数组，新遍历到的元素需要插入到前面已排好序的数组中
     */
    public void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > temp) {
                    nums[j + 1] = nums[j];
                    if (j == 0) {
                        nums[j] = temp;
                    }
                } else {
                    nums[j + 1] = temp;
                    break;
                }
            }
        }
    }

    /**
     * 快速排序：分治思想
     * 基本思想：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两个部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
     * <p>
     * 实现思路：
     * 1。选取一个基准元素
     * 2。将数组分割成两部分，一部分数据都小于或等于基准元素，另一部分数据都大于基准元素
     * 3。对分割的子数组递归地执行步骤1，2，知道无法分割
     */
    public void quickSort(int[] nums) {
        realQuickSort(nums, 0, nums.length - 1);
    }

    private void realQuickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        //找到基准点，将数组分割成两部分
        int left = start;
        int right = end;
        int provit = nums[left];

        while (left < right) {
            // 先从右侧遍历，遇到小于基准的，则将该值赋值到左边
            while (left < right && nums[right] >= provit) {
                right--;
            }
            if (left < right) {
                nums[left++] = nums[right];
            }

            //遍历左侧元素，遇到大于基准的，则复制给右侧元素
            while (left < right && nums[left] < provit) {
                left++;
            }
            if (left < right) {
                nums[right--] = nums[left];
            }
        }
        nums[left] = provit;
        realQuickSort(nums, start, left - 1);
        realQuickSort(nums, left + 1, end);
    }


}
