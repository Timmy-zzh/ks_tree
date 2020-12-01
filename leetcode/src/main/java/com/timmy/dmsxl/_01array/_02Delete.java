package com.timmy.dmsxl._01array;

import com.timmy.common.PrintUtils;

public class _02Delete {

    public static void main(String[] args) {
        _02Delete demo = new _02Delete();
//        int[] nums = {3, 2, 2, 3};
//        int target = 3;
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int target = 2;
        int size = demo.removeElement1(nums, target);
        size = demo.removeElement(nums, target);

    }

    /**
     * 编号：27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，
     * 并返回移除后数组的新长度。
     * <p>
     * 给定 nums = [3,2,2,3], val = 3,
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * <p>
     * 「你不需要考虑数组中超出新长度后面的元素。」
     * <p>
     * 实现思路：
     * 暴力破解法，双层for循环  O(n2)
     * 第一层for循环查找与目标值相同的元素，内层for循环找到与目标值不相同的元素进行元素移动
     */
    private int removeElement1(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < size; j++) {    //相等则往前移动，i往前退一位从新比较，并舍弃最后一位元素size-1
                    nums[j - 1] = nums[j];
                }
                i--;        //
                size--;
            }
        }
        PrintUtils.print(nums);
        System.out.println("size:" + size);
        return size;
    }

    /**
     * 双指针方法
     * 快慢指针，快指针元素与目标值相同，则后移一位，
     * 不想等，则快慢指针都后移，并赋值给慢指针
     */
    private int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
//        while (fast < nums.length) {
//            if (nums[slow] == val) {
//                nums[slow] = nums[fast];
//            } else {
//                slow++;
//            }
//            fast++;
//        }

        for (fast = 0; fast < nums.length; fast++) {
            if (val != nums[fast]) {
                nums[slow++] = nums[fast];
            }
        }

        PrintUtils.print(nums);
        System.out.println("slow2:" + slow);
        return slow;
    }
}
