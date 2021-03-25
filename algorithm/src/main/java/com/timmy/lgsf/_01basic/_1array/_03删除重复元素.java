package com.timmy.lgsf._01basic._1array;

import com.timmy.common.PrintUtils;

public class _03删除重复元素 {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 2};
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result = deleteRepeatElement(nums);
        System.out.println("result:" + result);

    }

    /**
     * 我们给出一道练习题。给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，
     * 返回移除后的数组和新的长度，你不需要考虑数组中超出新长度后面的元素。
     * 要求，空间复杂度为 O(1)，即不要使用额外的数组空间。
     * <p>
     * 例如，给定数组 nums = [1,1,2]，函数应该返回新的长度 2，并且原数组 nums 的前两个元素被修改为 1, 2。
     * 又如，给定 nums = [0,0,1,1,1,2,2,3,3,4]，函数应该返回新的长度 5，并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * <p>
     * 解题思路：双指针方法
     * 1。快指针正常遍历数组，慢指针判断与快指针元素值是否相同，相同continue，不同则慢指针保存新值，并++
     */
    private static int deleteRepeatElement(int[] nums) {
        int fast = 1, slow = 0;
        for (; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
        PrintUtils.print(nums);
        return slow + 1;
    }
}
