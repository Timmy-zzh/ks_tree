package com.timmy.lgsf._01basic._9deque;

import com.timmy.common.PrintUtils;

public class _02旋转数组_189 {

    public static void main(String[] args) {
        _02旋转数组_189 demo = new _02旋转数组_189();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        demo.rotate(nums, 3);
        PrintUtils.print(nums);
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     *
     * 示例 2:
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     */

    /**
     * 解题思路：
     * k循环，每次循环都保留最后值在temp中，从后往前覆盖值，最后第一个元素使用最后元素的值
     */
    public void rotate(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        int temp;
        while (count < k) {
            temp = nums[len - 1];
            for (int i = len - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = temp;
            count++;
        }
    }
}
