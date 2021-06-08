package com.timmy._review._10greedy;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _06合并区间_56 {

    public static void main(String[] args) {
        _06合并区间_56 demo = new _06合并区间_56();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        PrintUtils.print(intervals);
        int[][] res = demo.merge(intervals);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个二维数组，数组元素表示区间范围，现在要将重叠的区间进行合并，不重叠的区间原样返回
     * 2。解题思路
     * -将所有区间根据开始值大小进行升序排序，遍历每个区间，对区间进行区分
     * --已添加处理的区间，和未处理的区间
     * -使用list保存已处理的区间，新处理的区间判断与list区间最后一个区间是否产生重合？
     * --如果有重合，处理后放入到集合中，没有重合的区间新增到集合中，集合中最后一个元素为拉伸区
     * 3。总结
     * -目标是合并，将区间根据开始位置进行升序排序
     * -也是分成两个部分，未处理的区间跟已处理区间集合的动态变量进行判断移动
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });
        list.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] itemRange = intervals[i];     //新区间
            int[] ints = list.get(list.size() - 1);
            if (itemRange[0] > ints[1]) {    //没有重合
                list.add(itemRange);
            } else if (itemRange[1] > ints[1]) {
//                list.remove(list.size() - 1);
//                ints[1] = itemRange[1];
//                list.add(ints);
                list.get(list.size() - 1)[1] = itemRange[1];
            }
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
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
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     */
}
