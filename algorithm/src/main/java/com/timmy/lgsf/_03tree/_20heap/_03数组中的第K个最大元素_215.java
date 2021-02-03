package com.timmy.lgsf._03tree._20heap;

import java.util.PriorityQueue;

public class _03数组中的第K个最大元素_215 {

    public static void main(String[] args) {
        _03数组中的第K个最大元素_215 demo = new _03数组中的第K个最大元素_215();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int result = demo.findKthLargest(nums, 2);
        System.out.println("result:" + result);
    }

    // 遍历数组，使用最小堆存储
    private int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() != k || nums[i] > queue.peek()) {
                if (queue.size() == k) {
                    queue.poll();
                }
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }

    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，
     * 而不是第 k 个不同的元素。
     *
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     *
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     *
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
     */
}
