package com.timmy._00review._00tree;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 优先级堆；大顶堆，小顶堆
 */
public class _06前K个高频元素_347 {

    public static void main(String[] args) {
        _06前K个高频元素_347 demo = new _06前K个高频元素_347();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] res = demo.topKFrequent(nums, 2);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -输入一个整数数组（有正/负整数），一个元素可能会重复出现多次，求元素出现频率靠前的k个元素
     * 2。解题思路
     * -遍历数组元素，使用hashmap保存某个元素出现的次数，
     * -根据元素出现次数作为判断条件，使用优先级队列进行保存，出现次数越多优先级越高
     */
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            } else {
                hashMap.put(nums[i], 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();
        //小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> entry, Map.Entry<Integer, Integer> t1) {
                return entry.getValue() - t1.getValue();
            }
        });

        for (Map.Entry<Integer, Integer> entry : entrySet) {
            queue.add(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] resArr = new int[queue.size()];
        int i = 0;
        while (!queue.isEmpty()) {
            resArr[i++] = queue.poll().getKey();
        }

        return resArr;
    }

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     *
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     * 提示：
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
     * 你可以按任意顺序返回答案。
     *
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
     */
}
