package com.timmy._00review._00greedy;

/**
 * 会议室预定封装类：包含
 * 会议约定开始时间，和结束时间
 */
public class Interval {
    public long start;
    public long end;

    public Interval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
