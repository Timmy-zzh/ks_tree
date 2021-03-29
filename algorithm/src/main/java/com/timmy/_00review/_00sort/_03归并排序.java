package com.timmy._00review._00sort;

import com.timmy.common.PrintUtils;

//todo 明天再写一遍
public class _03归并排序 {

    public static void main(String[] args) {
        _03归并排序 demo = new _03归并排序();
        int[] nums = {2, 1, 7, 9, 5, 8};
        PrintUtils.print(nums);
        demo.mergeSort(nums);
        System.out.println("---");
        PrintUtils.print(nums);
    }

    /**
     * 归并排序：核心思想是分治
     * 1。先将元素根据长度进行拆分成两个子数组，然后继续拆分直到拆分成只有一个元素，然后该元素与另外一个元素进行合并
     * 2。每次合并的结果都是部分区间是有序的，最后全部元素有序
     */
    public void mergeSort(int[] nums) {
        int n = nums.length;
        sort(nums, 0, n - 1);
    }

    private void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        //1.继续拆分
        int mid = (low + high) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        //2.最后拆分的结果是只有[low,mid]有一个元素，[mid+1,high]只有一个元素
        //->将这两个元素进行合并
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        System.out.println("low:" + low + " ,mid:" + mid + " ,high:" + high);
        int[] clone = nums.clone();
        int k = low;
        //移动下标位置
        int i = low;
        int j = mid + 1;
        while (k <= high) {
            //先处理边界
            if (i > mid) {
                nums[k++] = clone[j++];
            } else if (j > high) {
                nums[k++] = clone[i++];
            } else if (clone[i] < clone[j]) {  //添加更小的元素
                nums[k++] = clone[i++];
            } else {
                nums[k++] = clone[j++];
            }
        }
    }
}
