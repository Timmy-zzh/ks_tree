package com.timmy.lgsf._01basic._4queue;

import com.timmy.common.PrintUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _05前K个高频元素 {

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
     *  2。根据数字出现的次数进行排序，取次数出现前2位的元素，使用优先级队列-小顶堆，堆顶元素值最大
     *  3。根据小顶堆个数，数量大于k了，则出队列，保存元素出现次数最多的数据
     */
    private static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> t1, Map.Entry<Integer, Integer> t2) {
                return  t1.getValue() > t2.getValue() ? 1 : -1;
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            priorityQueue.add(entry);
            System.out.println("key:" + entry.getKey() + " ,value:" + value+" ,map:"+priorityQueue.toString());
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        int size = priorityQueue.size();
        int h = 0;
        for (int i = 0; i < size; i++) {
            Map.Entry<Integer, Integer> entry = priorityQueue.poll();
            System.out.println("priorityQueue key:" + entry.getKey() + " ,value:" + entry.getValue());
            result[h++] = entry.getKey();
        }
        return result;
    }
}
