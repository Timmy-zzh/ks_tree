package com.timmy._review._10greedy;

import com.timmy.common.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

public class _04无重叠区间_435 {

    public static void main(String[] args) {
        _04无重叠区间_435 demo = new _04无重叠区间_435();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        PrintUtils.print(intervals);
        int res = demo.eraseOverlapIntervals(intervals);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个二维数组，每个元素表示一个区间，现在要求移除最少个数个区间，使的剩下的区间互不重叠
     * -该题目可以转换为求最多个数的互不重叠的区间
     * 2。解题思路
     * -因为要保证区间不重叠，将所有的区间分成两部分，一部分是不重叠的区间集合，另一部分是未处理的区间；
     * -现在要从未处理的区间中取出一个区间，和不重叠区间进行比较（已知不重叠区间的结束值），
     * --如果新区间的开始值大于等于已处理区间的结束值，则新区间可以添加到不重叠区间的集合中去，并且赋值新的结束值
     * -问题？如何从未处理区间中取出一个区间呢？--按照区间的结束值进行升序排序
     * 3。总结
     * -按照区间结束位置进行所有区间的升序排序
     * -将区间分为两个部分，已处理区间集合，并记录变化的标示
     * --未处理的区间按照排序顺序进行单个处理，处理后添加到已处理集合中
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int resNum = 0; //标示不重叠区间的个数
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] == t2[1] ? 0 : (t1[1] > t2[1] ? 1 : -1);
            }
        });
        PrintUtils.print(intervals);

        int maxEnd = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            if (maxEnd <= intervals[i][0]) {        //intervals[i][0] 新区间开始位置
                resNum++;
                maxEnd = intervals[i][1];           //更新一处理集合的区间结束位置
            }
        }
        return resNum;
    }

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
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
     * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
     */
}
