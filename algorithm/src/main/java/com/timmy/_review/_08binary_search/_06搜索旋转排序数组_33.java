package com.timmy._review._08binary_search;

public class _06搜索旋转排序数组_33 {

    public static void main(String[] args) {
        _06搜索旋转排序数组_33 demo = new _06搜索旋转排序数组_33();
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {1, 3};
        int res = demo.search(nums, 0);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个旋转后的数组 和一个目标值，求出目标值在数组中的下标位置
     * 2。解题思路
     * 2。1。暴力解法：遍历整个数组元素，判断是否与目标值相等
     * 2。2。二分搜索法
     * -取中位数，判断左侧范围是否有序，
     * --再判断目标值是否在左侧区域，在的话，搜索检索范围
     * -如果左侧范围无序，则右侧区域有序
     * --判断目标值是否在右侧区域范围
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            System.out.println("l:" + l + " ,r:" + r + " ,mid:" + mid);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] == target) {
                return l;
            }
            if (nums[r - 1] == target) {
                return r - 1;
            }

            if (nums[l] < nums[mid]) {     //左侧区域有序
                if (nums[l] < target && target < nums[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else {     //右侧区域有序
                if (nums[mid] < target && target < nums[r - 1]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
        }

        return -1;
    }

    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
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
     * 提示：
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums 中的每个值都 独一无二
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -10^4 <= target <= 10^4
     *
     * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     */
}
