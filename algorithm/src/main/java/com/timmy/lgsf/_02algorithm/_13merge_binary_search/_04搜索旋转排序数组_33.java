package com.timmy.lgsf._02algorithm._13merge_binary_search;

public class _04搜索旋转排序数组_33 {

    public static void main(String[] args) {
        _04搜索旋转排序数组_33 demo = new _04搜索旋转排序数组_33();
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {};
        int result = demo.search(nums, 0);
        System.out.println("result:" + result);
    }

    /**
     * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7]
     * 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
     * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 示例 1：
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     *
     * 示例 3：
     * 输入：nums = [1], target = 0
     * 输出：-1
     *
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     */

    /**
     * 1。理解题意
     * -还是按照二分查找的思路进行实现
     * -将数组切割成两部分，其中有一部分肯定是有序的，判断target值是否在这部分有序子数组中
     * 2。解题思路
     * -二分查找，左右指针left，right，中间指针元素判断 mid
     * 3。边界与细节处理
     * -判断分割出来的子数组是否有序
     */
    public int search(int[] nums, int target) {
        if (nums == null && nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //左侧有序
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {   //目标值，在有序子数组中
                    right = mid - 1;
                } else {     //在右侧
                    left = mid + 1;
                }
            } else {  // 右侧有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
