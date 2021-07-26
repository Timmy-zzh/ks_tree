package com.timmy.practice._07month;

import com.timmy.common.PrintUtils;

public class _26删除有序数组中的重复项_26 {

    public static void main(String[] args) {
        _26删除有序数组中的重复项_26 demo = new _26删除有序数组中的重复项_26();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        PrintUtils.print(nums);
        int res = demo.removeDuplicates(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个有序的数组，数组中的元素存在重复的元素，现需要删除重复出现的元素，使得每个元素只出现一次，最后返回数组的新长度
     * 2。解题思路：双指针解法
     * -遍历数组中的所有元素，并且与数组的目标指针元素进行比较，
     * -如果相等说明遇到了重复元素，原始数组正常往后遍历；不想等，说明遇到了不一样的数据，目标指针后移，并进行新值的赋值
     * 3.边界与细节问题
     * -数组遍历结束
     * -当出现不同元素值时，需要将新值赋值到目标指针的后面
     * -最后返回的是新数组长度，值为target指针+1
     */
    public int removeDuplicates(int[] nums) {
        int target = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[target] != nums[i]) {
                if (++target != i) {
                    nums[target] = nums[i];
                }
            }
        }
        return target + 1;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * 你可以想象内部操作如下:
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     *  
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 2：
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     *
     * 提示：
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     */
}
