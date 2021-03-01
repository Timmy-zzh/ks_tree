package com.timmy.lgsf._05backtrack_dp._33dynamic_programming;

import com.timmy.common.PrintUtils;

public class _03飞机座位分配概率_1227 {

    public static void main(String[] args) {
        _03飞机座位分配概率_1227 demo = new _03飞机座位分配概率_1227();
        double res = demo.nthPersonGetsNthSeat(5);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -n位乘客乘坐n个座位，第一位乘客的票丢了，所以他随机挑了一个座位坐下，他有可能坐在自己的座位上，也可能坐在其他人的座位上
     * -接下来的n-1个乘客：
     * --如果发现他的座位还空着，则坐到自己的座位上
     * --如果发现他自己的座位被占用了，则随机选择其他座位坐下
     * -求最后一个乘客n坐在自己座位上的概率是多少？
     * 2。解题思路
     * 分三种情况：
     * 1。如果第一个人坐到自己的位置（概率为1/n）,则后面的人都会正确入座（第n个人正确入座概率为1）
     * 2。如果第一个人坐到第n个人的座位（概率为1/n），则后面的人[2,n-1]个人也会正确入座，但是第n个人座位被占，正确入座的概率为0
     * 3。如果第一个人坐到第[2,n-1]个人的座位（概率为n-2/n）,
     * --后面还有n-1个人，在n-1个座位中查找，并且第n个坐在正确的位置，可以理解为n-1个人在n-1个座位中入座正确的概率
     * 状态转移方程：
     * dp[n] = 1/n + n-2/n * dp[n-1]
     * 其中：dp[1] = 1, dp[2]=0.5
     *
     * @param n
     * @return 题解：
     * https://leetcode-cn.com/problems/airplane-seat-assignment-probability/solution/dong-tai-gui-hua-fa-he-shu-xue-fa-by-virginiadb-y0/
     */
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1;
        }
        double[] dp = new double[n + 1];
        dp[1] = 1;
        dp[2] = 0.5;
        for (int i = 3; i <= n; i++) {
            dp[i] = 1.0 / i + (i - 2) / (i * 1.0) * dp[i - 1];
        }
        PrintUtils.print(dp);
        return dp[n];
    }

    /**
     * 有 n 位乘客即将登机，飞机正好有 n 个座位。第一位乘客的票丢了，他随便选了一个座位坐下。
     * 剩下的乘客将会：
     *  如果他们自己的座位还空着，就坐到自己的座位上，
     *  当他们自己的座位被占用时，随机选择其他座位
     *
     * 第 n 位乘客坐在自己的座位上的概率是多少？
     *
     * 示例 1：
     * 输入：n = 1
     * 输出：1.00000
     * 解释：第一个人只会坐在自己的位置上。
     *
     * 示例 2：
     * 输入: n = 2
     * 输出: 0.50000
     * 解释：在第一个人选好座位坐下后，第二个人坐在自己的座位上的概率是 0.5。
     *
     * 提示：
     * 1 <= n <= 10^5
     *
     * 链接：https://leetcode-cn.com/problems/airplane-seat-assignment-probability
     */
}
