package com.timmy._00review._00binary_search;

public class _01二分查找_704 {

    public static void main(String[] args) {
        _01二分查找_704 demo = new _01二分查找_704();
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int res = demo.search(nums, 12);
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.迭代法，使用while循环不断缩小检索范围
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int middle;

        while (low <= high) {
            middle = (low + high) / 2;
            if (target == nums[middle]) {
                return middle;
            } else if (target < nums[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 1.理解题意
     * - 输入一个有序的数组nums，和一个目标值target，如果目标值在数组中存在，则返回其下表，否则返回-1
     * 2。解题思路
     * -因为给定的是有序数组，要查找其中的元素，可以使用二分搜索算法
     * -递归实现，三要素
     * --入参和返回值：搜索范围，与返回的下表结果
     * --终止条件：搜索范围为0，或者找到了目标值
     * --单层递归逻辑：在给定的范围内，取中位数，进行判断，然后搜索范围，继续查找
     */
    public int search_v1(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int low, int high) {
        //1.安全校验，范围
        if (low > high) {
            return -1;
        }
        //2.取中位数进行判断
        int middle = (high + low) / 2;
//        int middle = low + (high - low) / 2; //更安全，防止相加出现越界情况
        if (target == nums[middle]) {
            return middle;
        }

        //3.中位数不是目标值，缩小检索范围
        if (target < nums[middle]) {   //目标值在左边范围
            return search(nums, target, low, middle - 1);
        } else {
            return search(nums, target, middle + 1, high);
        }
    }

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     *
     * 示例 2:
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     *
     * 提示：
     * 你可以假设 nums 中的所有元素是不重复的。
     * n 将在 [1, 10000]之间。
     * nums 的每个元素都将在 [-9999, 9999]之间。
     *
     * 链接：https://leetcode-cn.com/problems/binary-search
     */
}
