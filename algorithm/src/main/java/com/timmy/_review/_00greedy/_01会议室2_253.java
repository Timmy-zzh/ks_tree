package com.timmy._review._00greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _01会议室2_253 {

    public static void main(String[] args) {
        _01会议室2_253 demo = new _01会议室2_253();
        Interval[] intervals = new Interval[6];
        intervals[0] = new Interval(2, 6);
        intervals[1] = new Interval(3, 8);
        intervals[2] = new Interval(7, 9);
        intervals[3] = new Interval(4, 8);
        intervals[4] = new Interval(3, 4);
        intervals[5] = new Interval(8, 16);

        int res = demo.minMeetingRooms(intervals);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -有很多人预定了会议室，并且给出了约定的开始和结束时间，
     * -根据预定的会议室集合，求最少需要安排多少个会议室才能满足预定要求
     * 2。解题思路：贪心算法
     * -将所有的预定会议室根据预定的开始时间进行升序排序
     * --最先开始预定的会议室，分配一个会议室使用
     * -将分配了会议室的预定保存在一个优先级队列中，（小顶堆，根据最先结束时间判断）
     * -遍历预定会议室集合，每当要对预定会议室分配时，先判断使用中的会议室是否已经使用结束（根据预定结束时间判断）
     * -都遍历完后，返回使用中的会议室数量
     */
    public int minMeetingRooms(Interval[] intervals) {
        //1。按照预约开始时间排序
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval interval, Interval t1) {
                return (int) (interval.start - t1.start);
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(intervals[i].toString());
        }

        //2。按照预约结束时间创建小顶堆
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval interval, Interval t1) {
                return (int) (interval.end - t1.end);
            }
        });
        queue.add(intervals[0]);

        //3.遍历所有的会议预约，如果没有空闲的会议室，则新分配一间会议室，否则使用正在使用的会议室
        for (int i = 1; i < intervals.length; i++) {
            Interval poll = queue.poll();
            //查看当前这个最早结束的会议室，是否还在使用中
            if (poll.end <= intervals[i].start) {
                poll.end = intervals[i].end;
            } else {//开辟新的会议室
                queue.add(intervals[i]);
            }
            queue.offer(poll);
        }
        return queue.size();
    }

    /**
     *
     */
}
