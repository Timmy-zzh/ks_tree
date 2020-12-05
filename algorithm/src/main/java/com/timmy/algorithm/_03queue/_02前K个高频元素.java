package com.timmy.algorithm._03queue;

import com.timmy.common.PrintUtils;

public class _02前K个高频元素 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] result = topKFrequent(nums, 2);
        PrintUtils.print(result);
    }

    /**
     * 347. 前 K 个高频元素
     * * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * * <p>
     * * 示例 1:
     * * 输入: nums = [1,1,1,2,2,3], k = 2
     * * 输出: [1,2]
     *  解题思路：
     *  1。使用HashMap 保持每个元素的出现的次数
     *  2。根据数字出现的次数进行排序，取次数出现前2位的元素，使用优先级队列-小顶堆
     *  3。
     */
    private static int[] topKFrequent(int[] nums, int k) {
        return new int[0];
    }
}
