package com.timmy.practice._03month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _30合并区间_56 {

    public static void main(String[] args) {
        _30合并区间_56 demo = new _30合并区间_56();
//        int[][] intervals = {
//                {1, 3},
//                {2, 6},
//                {8, 10},
//                {15, 18}
//        };
        int[][] intervals = {
                {1, 4}, {4, 5},
        };
        int[][] res = demo.merge(intervals);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入二维数组，每个元素中表示一个区间[start,end]，存在多个区间，将重叠的区间进行合并后返回
     * 2。解题思路
     * -首先根据区间的开始位置进行升序排序
     * -然后遍历排序后数组的所有区间，并将元素区间保存到集合中，如果后面数组元素的开始区间比之前保存的结束区间大，则添加一个新的区间
     * --如果存在重叠，则进行合并，而不是新增集合元素
     */
    public int[][] merge(int[][] intervals) {
        //1.根据区间开始位置升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });

        List<int[]> list = new ArrayList<>();
        //2.遍历排序后的数组元素，并将不重叠的区间保存到集合中，存在重叠的区间则合并
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0 || intervals[i][0] > list.get(list.size() - 1)[1]) {
                list.add(intervals[i]);
            } else {
                //合并
                list.get(list.size() - 1)[1] = Math.max(intervals[i][1], list.get(list.size() - 1)[1]);
            }
        }
        int[][] resArr = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            resArr[i] = list.get(i);
        }
//        list.toArray(new int[])
        return resArr;
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
