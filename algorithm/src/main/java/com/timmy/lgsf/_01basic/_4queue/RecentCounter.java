package com.timmy.lgsf._01basic._4queue;

/**
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * <p>
 * 请你实现 RecentCounter 类：
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，
 * 并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * <p>
 * 示例：
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * <p>
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
 * 至多调用 ping 方法 10的4次方
 */
public class RecentCounter {

    public RecentCounter() {

    }

    /**
     * 解题思路：
     * 使用队列处理 - 队尾入队，队头出队
     * 1。队尾不断入队列
     * 2。判断队头元素值是否在 t-3000范围内，否则出队列，
     * 3。返回队列数量
     */
    MyQueue myQueue = new MyQueue();

    public int ping(int t) {
        myQueue.add(t);
        while (myQueue.peek() < (t - 3000)) {
            myQueue.poll();
        }
        return myQueue.size();
    }

    /**
     * 解题思路：
     * 双指针解法：定义3002的数组用于存储
     * start 与 end 值用于保存起始索引，
     * 求取end  与 start的差值就是所求个数
     * 注意end值边界问题
     */
//    private int[] times = new int[3002];
//    private int start = 0;
//    private int end = 0;
//    public int ping_v2(int t) {
//        //处理越界问题
//        if (end == times.length - 1) {
//            end = 0;
//        }
//        times[end++] = t;
//
//        //确定start，start的值为 t-3000 的范围值索引位置
//        while (times[start] < t - 3000) {
//            //防止越界
//            if (start == times.length - 1) {
//                start = 0;
//            } else {
//                start++;
//            }
//        }
//        int count = end - start;
//        if (start > end) {
//            count = times.length - (start - end);
//        }
//        System.out.println("count:" + count);
//        return count;
//    }

    /**
     * 解题思路：
     * 因为题目最多调用10000次ping方法，所以定义一个数组，容量为10000，用于保存每次请求的时间
     * 每次调用会传入请求时间的值，求当前值 到 t-3000 值范围的个数
     */
//    private int[] times = new int[10000];
//    public int ping_v1(int t) {
//        int count = 0;
//        int end = 0;
//        //对数组元素赋值，保存每次请求的时间,并保存当前值的下标索引
//        for (int i = 0; i < times.length; i++) {
//            if (times[i] == 0) {
//                times[i] = t;
//                end = i;
//                break;
//            }
//        }
//
//        //求数组中 t-3000 到 t 值范围的个数
//        while (end >= 0 && times[end] >= (t - 3000)) {
//            count++;
//            end--;
//        }
//        System.out.println("count:" + count);
//        return count;
//    }
}
