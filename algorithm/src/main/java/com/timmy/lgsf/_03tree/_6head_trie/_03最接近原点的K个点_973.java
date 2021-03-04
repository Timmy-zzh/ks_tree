package com.timmy.lgsf._03tree._6head_trie;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _03最接近原点的K个点_973 {

    public static void main(String[] args) {
        _03最接近原点的K个点_973 demo = new _03最接近原点的K个点_973();
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        demo.kClosest(points, 2);
    }

    /**
     * 1.理解题意
     * -输入一组平面上的点，计算这些点距原点（0，0）的距离，然后找出距离最近的k个点（最小的几个点）
     * 2。解题思路
     * -遍历这些平面的点，并计算距离，
     * -使用大顶堆保存与原点的距离，然后返回
     * 3。边界与细节问题
     * -与原点的距离计算，欧几里得
     * -距离最近的点 -- 使用大顶堆
     */
    private int[][] kClosest(int[][] points, int k) {
        //大顶堆
        PriorityQueue<Point> queue = new PriorityQueue<>(k, new Comparator<Point>() {
            @Override
            public int compare(Point point, Point t1) {
                return t1.x * t1.x + t1.y * t1.y - point.x * point.x - point.y * point.y;
            }
        });
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];

            if (queue.size() != k || Math.pow(x, 2) + Math.pow(y, 2) < queue.peek().pow()) {
                if (queue.size() == k) {
                    queue.poll();
                }
                queue.offer(new Point(x, y));
            }
        }

        int[][] result = new int[queue.size()][2];
        int index = 0;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            result[index][0] = poll.x;
            result[index][1] = poll.y;
            index++;
        }

        return result;
    }

    static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int pow() {
            return (int) (Math.pow(x, 2) + Math.pow(y, 2));
        }
    }

    /**
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     * （这里，平面上两点之间的距离是欧几里德距离。）
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     *
     * 示例 1：
     *
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * 示例 2：
     *
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     *
     * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
     */
}
