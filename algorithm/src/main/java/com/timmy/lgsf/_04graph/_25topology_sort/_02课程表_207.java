package com.timmy.lgsf._04graph._25topology_sort;

import com.timmy.common.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

class _02课程表_207 {

    public static void main(String[] args) {

        //[[1,0],[2,0],[3,1],[3,2]]
        _02课程表_207 demo = new _02课程表_207();
//        int[][] pre = {
//                {1, 0},
//                {2, 0},
//                {3, 1},
//                {3, 2},
//        };
//        boolean res = demo.canFinish(4, pre);
        int[][] pre = {
                {1, 0},
                {0, 1},
        };
        boolean res = demo.canFinish(2, pre);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入必修课的课程数量，和先修课程的二维矩阵，先修课程矩阵表示学习某门课程前，需要先学习其他的课程
     * 2。解题思路
     * -创建一个一维数组，表示该门课程的入度（也就是学习这门课程前需要先学习几门课？）
     * -如果有入度有0的课程，则先学习入度为0的课程
     * --遍历所有课程，找到入度为0的课程，将其入队列（Queue），然后出队列，表示该门课程学习结束
     * --该门课程学习结束后，则可以判断该门课程学习后，可以学习的其他课程，如果其他课程的入度也是0的话，入队列
     * --最后所有的课程度学习完之后，剩下的未学习课程为0
     * 3。边界和细节问题
     * -一维数组，学习课程的入度表示
     * -找到入度为0的课程入队列，
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //1。创建课程的入度数组
        int[] input = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            //[1,0]
            int[] ints = prerequisites[i];
            int nextN = ints[0];
            input[nextN] = input[nextN] + 1;
        }
        PrintUtils.print(input);

        //2.将入度为0的元素入队列
        // 队列保存的是课程
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer course = queue.poll();
                //找到这门课程的下一个课程，并将下一个课程的入度减1
                for (int h = 0; h < prerequisites.length; h++) {
                    //[1,0]
                    int[] ints = prerequisites[h];
                    int nextN = ints[0];
                    if (ints[1] == course) {
                        input[nextN] = input[nextN] - 1;

                        if (input[nextN] == 0) {
                            queue.offer(nextN);
                        }
                    }
                }
            }
        }

        PrintUtils.print(input);
        //3.看还有没有入度不是0的课程
        for (int in : input) {
            if (in != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
     * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     *
     * 示例 1：
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     *
     * 示例 2：
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     *
     * 链接：https://leetcode-cn.com/problems/course-schedule
     */
}
