package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

public class _09只出现一次的数字_136 {

    public static void main(String[] args) {
        _09只出现一次的数字_136 demo = new _09只出现一次的数字_136();
        int[] nums = {4, 1, 2, 1, 2};
        PrintUtils.print(nums);
        int res = demo.singleNumber(nums);
        PrintUtils.print(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组中的其中一个元素出现一次，其他元素都是出现两次，找出只出现一次的元素
     * 2。解题思路
     * 2。1。暴力解法
     * -遍历所有的数组元素，然后使用Map保存每个数字出现的次数，最后找出次数为1的元素即可
     * 2。2。三路切分法
     * -以数组中间下标位置为锚点，遍历数组元素，与锚点元素进行比较交换，使得最终小于锚点的区域在左边，大于锚点的区域在右边
     * -然后判断等于锚点的区域数量是否为1，为1说明为所求
     * -然后判断左右区域的数量，继续遍历个数为奇数的区域
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return singleNum(nums, 0, nums.length - 1);
    }

    private int singleNum(int[] nums, int left, int right) {
        System.out.println("left:" + left + " ,right:" + right);
        if (left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        int povit = nums[mid];
        int l = left;
        int index = left;
        int r = right;

        while (index <= r) {
            if (nums[index] < povit) {
                swapV(nums, l++, index++);
            } else if (nums[index] == povit) {
                index++;
            } else {
                swapV(nums, index, r--);
            }
        }
        if (index - l == 1) {
            return nums[l];
        }
        if ((l - left) % 2 == 1) {
            return singleNum(nums, left, l - 1);
        }
        return singleNum(nums, index, right);
    }

    private void swapV(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     *
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * 链接：https://leetcode-cn.com/problems/single-number
     */
}
