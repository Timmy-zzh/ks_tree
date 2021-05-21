package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _01合并排序 {

    public static void main(String[] args) {
        _01合并排序 demo = new _01合并排序();
        int[] nums = {9, 4, 6, 3, 1, 2, 1, 8, 7};
        PrintUtils.print(nums);
        demo.sort(nums);
        PrintUtils.print(nums);
    }

    /**
     * 1.排序：使用归并排序算法
     * 2。解题思路
     * -采用二叉树的后序遍历方式，先对数组进行拆分，拆分成最小单元（只剩下一个元素），
     * --然后将拆分后的小单元进行合并，并且合并后的部分是进行排好序的结构
     */
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        mergeSort(nums, 0, nums.length - 1);
    }

    /**
     * 不断拆分，从中间部分切割
     */
    private void mergeSort(int[] nums, int l, int r) {
        System.out.println("l:" + l + " ,r:" + r);
        if (l >= r) {  //只有一个元素，不用再进行拆分了
            return;
        }
        //取中间数字
//        int mid = (l + r) / 2;   //存在越界风险
        int mid = l + (r - l) / 2;
        //继续拆分
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);

        // 合并
        merge(nums, l, mid, r);
    }


    private void merge(int[] nums, int l, int mid, int r) {
        System.out.println("merge l:" + l + " ,mid:" + mid + " ,r:" + r);
        int[] copy = nums.clone();
        int index = l;
        int i = l;
        int j = mid + 1;

        while (i <= mid || j <= r) {
            if (j > r || i <= mid && copy[i] < copy[j]) {
                nums[index++] = copy[i++];
            } else {
                nums[index++] = copy[j++];
            }
        }
    }

    /**
     * 将[l,mid-1]区间 和 [mid,r] 区间进行合并，这部分区间的数据要求是有序的
     * -先复制一份完全相同的数组，然后将两个区间的数据添加到原始数字nums中
     */
    private void merge_v1(int[] nums, int l, int mid, int r) {
        System.out.println("merge l:" + l + " ,mid:" + mid + " ,r:" + r);
        int[] copy = nums.clone();
        int index = l;
        int i = l;
        int j = mid + 1;

        while (i <= mid || j <= r) {
            //左右边界处理
            if (i > mid) {
                nums[index++] = copy[j++];
            } else if (j > r) {
                nums[index++] = copy[i++];
            } else if (copy[i] < copy[j]) {
                nums[index++] = copy[i++];
            } else {
                nums[index++] = copy[j++];
            }
        }
    }
}
