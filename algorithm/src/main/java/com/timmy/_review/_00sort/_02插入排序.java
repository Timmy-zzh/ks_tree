package com.timmy._review._00sort;

import com.timmy.common.PrintUtils;

public class _02插入排序 {

    public static void main(String[] args) {
        _02插入排序 demo = new _02插入排序();
        int[] nums = {2, 1, 7, 9, 5, 8};
        PrintUtils.print(nums);
        demo.insert(nums);
        System.out.println("---");
        PrintUtils.print(nums);
    }

    //更优写法2
    public void insert(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int e = nums[i];
            int j = i;
            for (; j > 0 && nums[j - 1] > e; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = e;
        }
    }

    //更优写法1
    public void insert_v2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && nums[j - 1] > nums[j]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }

    /**
     * 插入排序：
     * -遍历数组元素，当前下标前的元素都是已经排好序的部分，后面是未排好序的部分
     * -往前遍历已经排好序的部分，寻找当前元素合适的插入位置，元素两两比较，大于当前元素的则交换，否则的话，退出本次轮序
     */
    public void insert_v1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j, j - 1);
                } else {
                    break;
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
