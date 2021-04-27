package com.timmy.practice._04month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 区间问题系列
 */
public class _15合并区间_56 {

    public static void main(String[] args) {
        _15合并区间_56 demo = new _15合并区间_56();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = demo.merge(intervals);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -输入二维数组，其中每个一维数组表示一个区间，现合并重叠的区间，并返回
     * 2。解题思路
     * -对二维数组进行排序，按照区间开始值进行升序排序，然后便利数组区间
     * -如果新加入的区间开始值，比已经结束值大，则新增
     * --如果新加入的区间开始值，比已加入结束值小，则进行合并
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0 || intervals[i][0] > list.get(list.size() - 1)[1]) {
                //开始值，比已加入的结束值大
                list.add(intervals[i]);
            } else {
                //合并
                list.get(list.size() - 1)[1] = Math.max(intervals[i][1], list.get(list.size() - 1)[1]);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 示例 2：
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *  
     * 提示：
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     *
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     */
}
