package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _12第k小的数 {

    public static void main(String[] args) {
        _12第k小的数 demo = new _12第k小的数();
        int[] A = {2, 4, 1, 5, 3};
        PrintUtils.print(A);
        int res = demo.kthNumber(A, 3);
        System.out.println("res:" + res);
    }

    /**
     * 1.求一个数组中的第k小的数
     * 2。解题思路：三路切分算法
     * -快速排序，取中位数，如果第k大的数在中位数范围内，则直接返回，
     * --如果小于，则区左侧范围区域，大于取右侧范围区域
     */
    public int kthNumber(int[] nums, int k) {
        return kthNumberR(nums, 0, nums.length - 1, k);
    }

    private int kthNumberR(int[] nums, int left, int right, int k) {
        System.out.println("----- left:" + left + " ,right:" + right);
        if (left >= right) {
            return nums[left];
        }

        int mid = left + ((right - left) >> 1);
        int povit = nums[mid];
        int index = left;
        int li = left;
        int rj = right;

        while (index <= rj) {
            if (nums[index] < povit) {   //小于锚点值，li++,index++
                swapV(nums, index++, li++);
            } else if (nums[index] == povit) {
                index++;
            } else {
                //大于
                swapV(nums, index, rj--);
            }
        }
        PrintUtils.print(nums);

        System.out.println("----- povit:" + povit);
        System.out.println("----- li:" + li + " ,index:" + index);
        //等于povit值的区域为 [li,index-1]
        if (k - 1 >= li && k <= index) {
            return nums[li];
        }
        if (k - 1 >= index) {
            return kthNumberR(nums, index, right, k);
        }

        return kthNumberR(nums, left, li - 1, k);
    }

    private void swapV(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 【题目】给定一个数组，请找出第 k 小的数（最小的数为第 1 小）。
     * 输入：A = [2, 4, 1, 5, 3], k = 3
     * 输出：3
     */
}
