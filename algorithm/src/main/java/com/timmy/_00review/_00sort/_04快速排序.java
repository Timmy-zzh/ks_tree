package com.timmy._00review._00sort;

import com.timmy.common.PrintUtils;

public class _04快速排序 {

    public static void main(String[] args) {
        _04快速排序 demo = new _04快速排序();
        int[] nums = {6, 2, 1, 7, 9, 5, 6, 8};
        PrintUtils.print(nums);
        demo.quickSort(nums);
        System.out.println("---");
        PrintUtils.print(nums);
    }

    /**
     * 快速排序：
     * 1。每次都选择区域的第一个元素作为基准点，然后与区域内的元素进行比较
     * 2。使的基准前面的值都小于基准，基准后面的值都大于基准，
     * 3。根据基准继续拆分下去
     */
    public void quickSort(int[] nums) {
        int n = nums.length;
        quickSort(nums, 0, n - 1);
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low;
        int j = high;
        //1.基准点
        int povit = nums[low];

        //分别从区域[low,high]两边进行比较
        while (i < j) {
            //2.从尾部j开始往前遍历，查找比基准小的值
            while (i < j && nums[j] >= povit) {
                j--;
            }
            //退出while循环，说明在尾部找到比基准小的值，放到前面去
            if (i < j) {
                nums[i++] = nums[j];
            }
            //3.从头部i开始往后遍历，查找比基准大的值，放到后面去
            while (i < j && nums[i] <= povit) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = povit;

        //4.继续拆分
        if (i > low) {
            quickSort(nums, low, i);
        }
        if (i + 1 < high) {
            quickSort(nums, i + 1, high);
        }
    }

}
