package com.timmy.lgsf._01basic._4queue;

import com.timmy.common.PrintUtils;

public class _03滑动窗口最大值_239 {

    public static void main(String[] args) {
        //        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] result = demo.maxSlidingWindow(nums, 3);
//        int[] nums = {1, -1};
//        int[] result = demo.maxSlidingWindow(nums, 1);
        int[] nums = {1, 3, 1, 2, 0, 5};
        int[] result = maxSlidingWindow(nums, 3);
        PrintUtils.print(result);
    }

    /**
     * 239. 滑动窗口最大值
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * <p>
     * 进阶：
     * 你能在线性时间复杂度内解决此题吗？
     * <p>
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7      3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * <p>
     * 解题思路：
     * 1。创建单调队列，元素大小从大到小，实现入队列与出队列的逻辑
     * 2。每次取队列都是取队头的最大值元素
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonoQueue monoQueue = new MonoQueue();
        int[] result = new int[nums.length - k + 1];
        int h = 0;

        //1.先将前k个元素入单调队列
        for (int i = 0; i < k; i++) {
            monoQueue.push(nums[i]);
        }
        result[h++] = monoQueue.fornt();
        System.out.println(monoQueue.toString());

        //2.遍历k后面的元素，出前面的队列,入单调队列，取数据，
        for (int i = k; i < nums.length; i++) {
            monoQueue.pop(nums[i - k]);
            monoQueue.push(nums[i]);
            System.out.println(monoQueue.toString());
            result[h++] = monoQueue.fornt();
        }
        return result;
    }
}
