package com.timmy.practice._07month;

import java.util.LinkedList;

public class _21最近的请求次数_933 {

    public static void main(String[] args) {
        RecentCounter demo = new RecentCounter();
        int ping = demo.ping(1);
        System.out.println("ping(1):" + ping);
        ping = demo.ping(100);
        System.out.println("ping(100):" + ping);
        ping = demo.ping(3001);
        System.out.println("ping(3001):" + ping);
        ping = demo.ping(3002);
        System.out.println("ping(3002):" + ping);
    }

    /**
     * 2.解法二：使用队列数据结构保存数据
     * -先进先出
     * -新的请求数据入队列到队尾
     * -不断获取队头数据，判断是否在t-3000范围内，如果小于则队头数据不断出队
     * -最后的结果是队列的大小
     */
    public static class RecentCounter {

        private LinkedList<Integer> queue = new LinkedList<>();

        public RecentCounter() {

        }

        public int ping(int t) {
            queue.addLast(t);
            while (queue.getFirst() < t - 3000) {
                queue.removeFirst();
            }
            return queue.size();
        }
    }

    /**
     * 1.理解题意
     * -设计一个类，在时间t的时候调用ping方法，添加一个新请求，并返回当前时间到过去3000毫秒内的所有请求数
     * 2。解题思路
     * -方式一：使用数组存储请求数据，数组大小为3002，并设置两个指针start，end，表示范围[t-3000,t]的下标值，
     * --当有新的请求到来时，使用end指针承载，end++，当超过数组大小时，end=0从头开始处理
     * --然后判断start指针的值是否在t-3000 范围内，如果小雨start++，并注意数组越界问题
     * -最后返回的结果是end - start的值，如果end<start;结果为 length+end - start
     */
    public static class RecentCounter_v1 {

        private int[] count = new int[3002];
        private int start = 0, end = 0;

        public RecentCounter_v1() {

        }

        public int ping(int t) {
            //保存新请求值t
            count[end++] = t;
            if (end >= count.length) {
                end = 0;
            }
            //start的下标值不断右移
            while (count[start] < t - 3000) {
                start++;
                if (start >= count.length) {
                    start = 0;
                }
            }
            if (start > end) {
                return count.length - (start - end);
            }
            return end - start;
        }
    }

    /**
     * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
     * 请你实现 RecentCounter 类：
     * RecentCounter() 初始化计数器，请求数为 0 。
     * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。
     * 确切地说，返回在 [t-3000, t] 内发生的请求数。
     * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
     *
     * 示例：
     * 输入：
     * ["RecentCounter", "ping", "ping", "ping", "ping"]
     * [[], [1], [100], [3001], [3002]]
     * 输出：
     * [null, 1, 2, 3, 3]
     *
     * 解释：
     * RecentCounter recentCounter = new RecentCounter();
     * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
     * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
     * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
     * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
     *
     * 提示：
     * 1 <= t <= 109
     * 保证每次对 ping 调用所使用的 t 值都 严格递增
     * 至多调用 ping 方法 104 次
     * 链接：https://leetcode-cn.com/problems/number-of-recent-calls
     */
}
