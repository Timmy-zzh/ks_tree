package com.timmy._review._03prioity_queue;

import com.timmy.common.PrintUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _02最小的k个数 {

    public static void main(String[] args) {
        _02最小的k个数 demo = new _02最小的k个数();
        int[] arr = {3, 2, 1};
        int[] res = demo.getLeastNumbers(arr, 2);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，找到数组中最小的k个数，
     * 2。模拟运行
     * 2。1。先将数组进行升序排序，然后取前面的k个数即可，---复杂度为nlog n
     * 2.2. 采用优先级队列解法  -- nlog k
     * -遍历数组中所有元素，优先级队列采用大顶堆，将遍历到的元素加入队列中，这是堆heap的根节点元素最大，
     * -优先级队列的元素个数大于k时，根节点出队
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        //大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);

            if (queue.size() > k) {
                queue.poll();
            }
        }
        int i = 0;
        while (!queue.isEmpty()) {
            res[i++] = queue.poll();
        }
        return res;
    }

    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     * 示例 1：
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     *
     * 示例 2：
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     *
     * 限制：
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     *
     * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
     */
}
