package com.timmy._review._02queue;

import com.timmy.common.PrintUtils;

import java.util.LinkedList;

public class _03滑动窗口最大值_329 {

    public static void main(String[] args) {
        _03滑动窗口最大值_329 demo = new _03滑动窗口最大值_329();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = demo.maxSlidingWindow(nums, 3);
        PrintUtils.print(res);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.getLast() < nums[i]) {
                queue.removeLast();
            }
            queue.add(nums[i]);
            //前k个入队列
            if (i < k - 1) {
                continue;
            }

            //先将队列中最大的元素保存到结果集中，然后出队列，再入队列
            res[index++] = queue.getFirst();
            //队头元素出队列
            if (i >= k && queue.getFirst() == nums[i - k]) {
                queue.removeFirst();
            }
        }
        return res;
    }

    /**
     * 1.理解题意
     * -输入一个数组，和一个窗口，这个窗口中可以看到k个数字，现在将该窗口放在数组上，每次移动一个数组，在移动过程中找出在窗口中的最大值
     * 2。模拟运行：单调递减队列
     * -将窗口内的元素使用单调队列进行保存，从队列头出队列，从队尾入队列，所以对头元素是窗口中的最大值
     * -入队与出队操作：
     * --入队：要保证单调递减，新入队元素比队尾元素小的直接入队；如果新入队元素比队尾元素大，则队尾元素出队，直到满足条件（while）
     * --出队：当窗口往后移动时，需要将滑出窗口的元素进行出队，该元素可能在之前就不存在，所以需要判断再出队（是否相等）
     * 3。边界与细节问题：
     * -滑动窗口移动的次数
     * -入队与出队操作的实现
     * 4。复杂度分析：
     * -
     */
    public int[] maxSlidingWindow_v1(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //前k个入队列
            if (i < k) {
                while (!queue.isEmpty() && queue.getLast() < nums[i]) {
                    queue.removeLast();
                }
                queue.add(nums[i]);
            } else {
                //先将队列中最大的元素保存到结果集中，然后出队列，再入队列
                res[index++] = queue.getFirst();
                //队头元素出队列
                if (queue.getFirst() == nums[i - k]) {
                    queue.removeFirst();
                }

                while (!queue.isEmpty() && queue.getLast() < nums[i]) {
                    queue.removeLast();
                }
                queue.add(nums[i]);
            }
        }
        res[index] = queue.getFirst();

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
