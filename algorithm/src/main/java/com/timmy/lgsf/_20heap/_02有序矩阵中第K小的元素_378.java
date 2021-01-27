package com.timmy.lgsf._20heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _02有序矩阵中第K小的元素_378 {

    public static void main(String[] args) {
        _02有序矩阵中第K小的元素_378 demo = new _02有序矩阵中第K小的元素_378();
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}};
        int result = demo.kthSmallest(matrix, 8);
        System.out.println("result:" + result);
    }

    //-使用二维数组 横列都市升序的特性，查找第k小的元素，上指针方法
//    private int kthSmallest(int[][] matrix, int k) {
//        int res = Integer.MIN_VALUE;
//        int row = 0, col = 0;
//        int index = 1;
//        while (row < matrix.length && col < matrix[0].length) {
//            if (index == k) {
//                return matrix[row][col];
//            }
//
//
//            row++;
//            col++;
//        }
//        return res;
//    }

    /**
     * 1.理解题意
     * -在横，列 升序的情况下，找到第k小的元素
     * 2。解题思路
     * -遍历二维数组，将遍历后的元素存放在大顶堆中，最上面的元素就是我们需要的元素
     */
    private int kthSmallest(int[][] matrix, int k) {
        //大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (queue.size() != k || matrix[i][j] < queue.peek()) {
                    if (queue.size() == k) {
                        queue.poll();
                    }
                    queue.add(matrix[i][j]);
                }
            }
        }
        return queue.peek();
    }

    /**
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
     *
     * 示例：
     *
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     * 返回 13。
     *
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
     */
}
