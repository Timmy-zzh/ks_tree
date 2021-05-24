package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _02数组中的逆序对_Offer51 {

    public static void main(String[] args) {
        _02数组中的逆序对_Offer51 demo = new _02数组中的逆序对_Offer51();
//        int[] nums = {7, 5, 6, 4};
        int[] nums = {1, 3, 2, 3, 1};
        PrintUtils.print(nums);
        int res = demo.reversePairs(nums);
        PrintUtils.print(nums);
        System.out.println("res:" + res);
    }

    /**
     * 2.归并排序算法
     * -先将数组进行拆分，拆分成很小的两个部分，然后再将数组的两部分进行合并
     * -在合并过程中原先的两部分是有序的
     * --前面一部分范围为[l,mid] , 后面一部分范围[mid+1,r]
     * -当前面一部分的元素i（l<= i <= mid） , 比后面一部分元素j值小时，（mid+1 <= j <= r）
     * --可以推断元素i的值比范围[mid+1 , j）部分的值要大，这也就是所要求的逆序对个数
     */
    int res = 0;

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        mergerSort(nums, 0, nums.length - 1);
        return res;
    }

    /**
     * 1。先进性拆分
     * 2。再合并
     */
    private void mergerSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
//        int mid = l + (r - l) >> 1;
        int mid = l + (r - l) / 2;
        mergerSort(nums, l, mid);
        mergerSort(nums, mid + 1, r);

        merger(nums, l, mid, r);
    }

    private void merger(int[] nums, int l, int mid, int r) {
        int[] copy = nums.clone();      //这个复制的数组是不变的，可以用来做原始比较
        int i = l;
        int j = mid + 1;
        int index = l;

        while (i <= mid || j <= r) {
            if (j > r || i <= mid && copy[i] < copy[j]) {
                nums[index++] = copy[i++];
                res += j - mid - 1;
            } else {
                nums[index++] = copy[j++];
            }
        }
    }

    /**
     * 1。理解题意
     * -输入一个无序的数组，求数组中的逆序对的总数，
     * --逆序对的定义是数组中前面的元素比后面的元素值大
     * 2。解题思路
     * -要求数组中前面元素比后面元素大的个数
     * 暴力解法1：
     * -两层for循环，遍历每个元素，并且遍历后面的元素比当前元素小的值
     */
    public int reversePairs_v1(int[] nums) {
        int N = nums.length;
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nums[i] > nums[j]) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     *  在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     *  输入一个数组，求出这个数组中的逆序对的总数。
     *
     *  示例 1:
     *  输入: [7,5,6,4]
     *  输出: 5
     *
     *  限制：
     *  0 <= 数组长度 <= 50000
     */
}
