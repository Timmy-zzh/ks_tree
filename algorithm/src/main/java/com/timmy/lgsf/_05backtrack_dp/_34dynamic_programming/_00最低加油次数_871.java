package com.timmy.lgsf._05backtrack_dp._34dynamic_programming;

import com.timmy.common.PrintUtils;

public class _00最低加油次数_871 {

    public static void main(String[] args) {
        _00最低加油次数_871 demo = new _00最低加油次数_871();
        int target = 90;
        int startFuel = 35;
        int[][] stations = {
                {10, 12},
                {20, 25},
                {30, 21},
                {40, 5},
                {50, 3},
        };
        int res = demo.minRefuelStops(target, startFuel, stations);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -汽车从起点出发驶向target距离远的目的地，刚开始汽车邮箱有startFuel油量，
     * --在沿途中存在加油站stations，[[20, 25]...],表示在多少公里处存在加油站，和加油站存在的油量
     * -条件：每次加油需要将加油站中所有的油量加去，求到达目的地沿途最少的加油次数，如果到达补了，则返回-1
     * 2。解题思路
     * 动态规划
     * -原问题拆分成子问题
     * -状态转移方程
     * ----
     * -状态dp[i]表示加油i次能够到达的最远距离
     * -在行驶过程中要求加油i次能够到达中途的某个加油站，经过所有加油站中途加油x次能够到达目的地，即为所求
     * dp[i] 可到行驶到当前加油站
     * dp[i+1] = max(dp[i+1], dp[i]+当前加油站油量 ）；
     *
     * @param target
     * @param startFuel
     * @param stations
     * @return TODO 使用堆和动态规划解答
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
        int[] dp = new int[N + 1];
        dp[0] = startFuel;

        for (int i = 0; i < N; i++) {
            for (int t = i; t >= 0; t--) {
                //加油t次，能够到达当前加油站
                if (dp[t] >= stations[i][0]) {
                    //状态转移
                    dp[t + 1] = Math.max(dp[t + 1], dp[t] + stations[i][1]);
                }
            }
        }

        PrintUtils.print(dp);
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，
     * 并且有 station[i][1] 升汽油。
     *
     * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
     * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
     *
     * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
     *
     * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，
     * 仍然认为它已经到达目的地。
     *
     * 示例 1：
     * 输入：target = 1, startFuel = 1, stations = []
     * 输出：0
     * 解释：我们可以在不加油的情况下到达目的地。
     *
     * 示例 2：
     * 输入：target = 100, startFuel = 1, stations = [[10,100]]
     * 输出：-1
     * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
     *
     * 示例 3：
     * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
     * 输出：2
     * 解释：
     * 我们出发时有 10 升燃料。
     * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
     * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
     * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
     * 我们沿途在1两个加油站停靠，所以返回 2 。
     *  
     * 提示：
     * 1 <= target, startFuel, stations[i][1] <= 10^9
     * 0 <= stations.length <= 500
     * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
     *
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-refueling-stops
     */
}
