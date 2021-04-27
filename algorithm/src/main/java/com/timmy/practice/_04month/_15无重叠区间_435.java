package com.timmy.practice._04month;

import java.util.Arrays;
import java.util.Comparator;

public class _15无重叠区间_435 {

    public static void main(String[] args) {
        _15无重叠区间_435 demo = new _15无重叠区间_435();
//        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
//        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
//        int[][] intervals = {{1, 2}, {2, 3}};
//        int res = demo.eraseOverlapIntervals(intervals);
//        System.out.println("res:" + res);


        int[][] intervals = {{1, 9}, {1, 3}, {2, 5}, {4, 6}};
        int res = demo.eraseOverlapInt(intervals);
        System.out.println("res:" + res);

    }

    /**
     * 1.理解题意
     * -输入一个二维数组，每个数组元素是一个区间范围，现要移除某些区间，使剩下的区间不重叠，求最少移动几个区间
     * 2。解题思路
     * 2。1。暴力解法：使用回溯法找到所有的区间组合，然后判断组合是否满足区间不重叠，并计算移动了几个区间，
     * 2。2。贪心算法
     * -当遇到重叠区域时，删除结束值更大的区间，这样才能避免该区间与后面的区间重叠了
     * -将二维数组按照区间开始值进行升序排序
     * -遍历区间，判断新区间的开始值是否与老区间结束值，是否重叠，重叠的话，删除结束值更大的区间
     * -如果没有重叠，则区间结束值后移
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });

        int end = 0;
        int count = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] < end) {//开始值小于结束值，发生重叠
                count++;
                end = Math.min(end, intervals[i][1]);
            } else {
                //更新结束值
                end = intervals[i][1];
            }
        }
        return count;
    }


    /**
     * 区间问题变形
     * 1。题目描述
     * -在给定的区间中，最多有多少个区间相互之间是没有重叠的？
     * 2。解题思路
     * -按照区间结束值进行升序排序，比较区间开始值与结束值，判断是否存在重叠，如果重叠则取结束值小的
     * -否则更新结束值
     */
    public int eraseOverlapInt(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[1] - t1[1];
            }
        });
        int end = intervals[0][1];
        int count = 1;      //为没有重叠的区间
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        return count;
    }

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * 注意:
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     *
     * 示例 1:
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     *
     * 示例 2:
     * 输入: [ [1,2], [1,2], [1,2] ]
     * 输出: 2
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     *
     * 示例 3:
     * 输入: [ [1,2], [2,3] ]
     * 输出: 0
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     *
     * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
     */
}
