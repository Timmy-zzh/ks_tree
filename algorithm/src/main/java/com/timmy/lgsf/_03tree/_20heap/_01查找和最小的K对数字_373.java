package com.timmy.lgsf._03tree._20heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _01查找和最小的K对数字_373 {

    public static void main(String[] args) {
        _01查找和最小的K对数字_373 demo = new _01查找和最小的K对数字_373();
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 4, 6};
        List<List<Integer>> lists = demo.kSmallestPairs(nums1, nums2, 3);
        System.out.println(lists.toString());
    }

    /**
     * 最优解：
     * 因为两个数组都是升序，
     * 所以和最小值的组合，存在与两个数组迭代的最外层，再结合大顶堆进行入堆出堆，得出最后结果
     */


    /**
     * 解题思路：
     * 1。不实用sort排序，而使用大顶堆进行存储
     * -为什么使用大顶堆？
     * -大顶堆，根节点元素最大
     * -大顶堆如果已经有k个元素了，则新加入的元素与堆顶元素进行比较，比堆顶元素大，不加入，比堆顶元素小，先出堆再入堆
     */
    private List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return res;
        }

        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                list.add(new Pair(nums1[i], nums2[j]));
            }
        }

        System.out.println(list.toString());
        //保存到大顶堆
        PriorityQueue<Pair> queue = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair pair, Pair t1) {
                return t1.x + t1.y - pair.x - pair.y;
            }
        });

        for (Pair pair : list) {
            if (queue.size() != k || pair.x + pair.y < queue.peek().x + queue.peek().y) {
                System.out.println(pair.toString());
                if (queue.size() == k) {
                    queue.poll();
                }
                queue.add(pair);
            }
        }
        System.out.println("-------");
        System.out.println(queue.toString());

        //取前k个
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            Pair pair = queue.poll();
            List<Integer> item = new ArrayList<>();
            item.add(pair.x);
            item.add(pair.y);
            res.add(item);
        }
        return res;
    }

    /**
     * 1.理解题意
     * -输入两个数组，分别从两个数组中取出一个元素作为一个组合，求取组合中两个元素的和最小，
     * -一共有m*n个组合，取和最小的k个组合
     * 2。解题思路：暴力解法
     * -分别从两个数组中取元素，并用数组保存，保存后进行排序，然后取和最小的前k个组合
     * --定义一个数据结构：Pair(x,y) 其中x，y分别是数组中的元素
     */
    private List<List<Integer>> kSmallestPairs_v1(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return res;
        }

        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                list.add(new Pair(nums1[i], nums2[j]));
            }
        }

        System.out.println(list.toString());
        //排序，升序
        list.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair pair, Pair t1) {
                return pair.x + pair.y - t1.x - t1.y;
            }
        });
        System.out.println("---------");
        System.out.println(list.toString());

        //取前k个
        for (int i = 0; i < k && i < list.size(); i++) {
            Pair pair = list.get(i);
            List<Integer> item = new ArrayList<>();
            item.add(pair.x);
            item.add(pair.y);
            res.add(item);
        }
        return res;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    /**
     * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
     * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
     *
     * 示例 1:
     *
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * 示例 2:
     *
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * 示例 3:
     *
     * 输入: nums1 = [1,2], nums2 = [3], k = 3
     * 输出: [1,3],[2,3]
     * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}
