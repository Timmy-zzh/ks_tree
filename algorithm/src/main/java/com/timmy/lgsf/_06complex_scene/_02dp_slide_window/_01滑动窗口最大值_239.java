package com.timmy.lgsf._06complex_scene._02dp_slide_window;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _01滑动窗口最大值_239 {

    public static void main(String[] args) {
        _01滑动窗口最大值_239 demo = new _01滑动窗口最大值_239();
//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int k = 3;
//        int[] nums = {9, 11, 2, 3, 5, 8};
//        int k = 2;
        int[] nums = {9, 10, 9, -7, -4, -8, 2, -6};
        int k = 5;
        PrintUtils.print(nums);
        List<Integer> res = demo.maxSlidingWindow(nums, k);
        PrintUtils.print(res);
    }

    //2.单调队列
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > queue.peekLast()) {
                queue.pollLast();
            }
            queue.add(nums[i]);
        }
        System.out.println("---" + queue.toString());
        res.add(queue.peek());

        for (int i = 1; i < nums.length - k + 1; i++) {
            //先删除前面存在队列的元素
            if (!queue.isEmpty() && queue.peek() == nums[i - 1]) {
                queue.poll();
            }
            //窗口最后的元素入队列
            while (!queue.isEmpty() && nums[i + k - 1] > queue.peekLast()) {
                queue.pollLast();
            }
            queue.add(nums[i + k - 1]);

            System.out.println("====" + queue.toString());
            res.add(queue.peek());
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return res;
    }

    //暴力解法
    public List<Integer> maxSlidingWindow_v1(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int windowMax;
        for (int i = 0; i < nums.length - k + 1; i++) {
            windowMax = nums[i];
            //遍历从i到k个元素，找到最大值
            for (int j = i + 1; j < i + k; j++) {
                windowMax = Math.max(windowMax, nums[j]);
            }
            res.add(windowMax);
        }
        return res;
    }


    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     *
     * 示例 1：
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * 示例 2：
     * 输入：nums = [1], k = 1
     * 输出：[1]
     *
     * 示例 3：
     * 输入：nums = [1,-1], k = 1
     * 输出：[1,-1]
     *
     * 示例 4：
     * 输入：nums = [9,11], k = 2
     * 输出：[11]
     *
     * 示例 5：
     * 输入：nums = [4,-2], k = 2
     * 输出：[4]
     *  
     * 提示：
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
     */
}
