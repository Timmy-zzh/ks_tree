package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _15查找和最小的K对数字_373 {

    public static void main(String[] args) {
        _15查找和最小的K对数字_373 demo = new _15查找和最小的K对数字_373();
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        List<List<Integer>> res = demo.kSmallestPairs(nums1, nums2, 3);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 2.优先级队列解法
     * -因为要找到两个数组元素和的最小值，所以使用最大堆数据结构
     * -两个元素和比堆顶元素值更小的数据入队列
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return (o2.get(0) + o2.get(1)) - (o1.get(0) + o1.get(1));
            }
        });

        List<Integer> item;
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                if (queue.size() != k || (nums1[i] + nums2[j]) < (queue.peek().get(0) + queue.peek().get(1))) {
                    if (queue.size() == k) {
                        queue.poll();
                    }
                    item = new ArrayList<>();
                    item.add(nums1[i]);
                    item.add(nums2[j]);
                    queue.add(item);
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

    /**
     * 1.理解题意
     * -输入两个有序数组，和一个k值，将两个数组中的元素进行相加，得到的和最小，一共要求k对并返回
     * 2。解题思路
     * -2。1。暴力解法
     * --遍历两个数组，得到两两相应的数组，并且保存起来，然后进行排序，排序标准为数组的和，最后取前k个数组作为结果返回
     */
    public List<List<Integer>> kSmallestPairs_v1(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                list.add(new int[]{nums1[i], nums2[j]});
            }
        }
        //升序
        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] + o1[1]) - (o2[0] + o2[1]);
            }
        });

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> item;
        //取前k个
        for (int i = 0; i < Math.min(k,list.size()); i++) {
            item = new ArrayList<>();
            item.add(list.get(i)[0]);
            item.add(list.get(i)[1]);
            res.add(item);
        }
        return res;
    }

    /**
     * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
     * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
     *
     * 示例 1:
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     *
     * 示例 2:
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     *
     * 示例 3:
     * 输入: nums1 = [1,2], nums2 = [3], k = 3
     * 输出: [1,3],[2,3]
     * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
     *  
     *
     * 提示:
     * 1 <= nums1.length, nums2.length <= 104
     * -109 <= nums1[i], nums2[i] <= 109
     * nums1, nums2 均为升序排列
     * 1 <= k <= 1000
     * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
     */
}
