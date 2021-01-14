package com.timmy.lgsf._13merge_binary_search;

public class _02二分查找_704 {

    public static void main(String[] args) {
        _02二分查找_704 demo = new _02二分查找_704();
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int search = demo.search(nums, 0);
        System.out.println("result:" + search);
    }

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，
     * 如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * <p>
     * 示例 2:
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     * <p>
     * 链接：https://leetcode-cn.com/problems/binary-search
     */

    /**
     * 1.理解题意
     * -升序数组中查找目标值target，
     * 2。解题思路
     * -定义两个指针left，right分别指向数组的头尾元素，
     * -取中间位置元素比较，相等则直接返回mid下表，不想等则移动左右指针位置
     * 3.边界细节问题
     * -左右指针范围
     * -mid中间指针判断后，左右指针移动控制
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
