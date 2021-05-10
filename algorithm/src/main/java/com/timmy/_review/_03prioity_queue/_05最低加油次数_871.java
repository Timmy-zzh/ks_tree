package com.timmy._review._03prioity_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _05最低加油次数_871 {

    public static void main(String[] args) {
        _05最低加油次数_871 demo = new _05最低加油次数_871();
        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        int res = demo.minRefuelStops(100, 10, stations);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -从起始点开始驱车出发，要去距离target公里的目的地，开始车里有startFuel 升汽油，
     * -且沿途也有加油站station[i][i], 第i个加油站距离起始点station[i][0] 公里，有station[i][1]升汽油，
     * -汽车驶过加油站可以加油，问如果要到达目的地，最少需要加油多少次
     * 2。模拟运行
     * -从起始点到目的地，中途经过加油站，首先要保证车上的汽油能够到达中途的加油站，
     * --假设汽车有一个备用的副邮箱，该邮箱保存了沿途所有加油站的汽油，使用优先级队列（大顶推）保存，每次取最大油量，才能保证加油次数最少
     * -遍历加油站：
     * --先保证剩余的油量能够行驶到该加油站，如果油量不够，则不断从副邮箱中取出保存的汽油，进行加油，如果还是不够到达则失败
     * 实现步骤：
     * -先判断车上剩余的汽油是否能够到达当前加油站，如果能够到达，则当前加油站的汽油保存到副邮箱
     * --如果车上汽油不够，则从副邮箱中去除汽油加油
     * --并减少路程消耗的汽油
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int resNum = 0;
        int leftFuel = startFuel;//车上剩余的汽油
        //副邮箱保存的汽油(大顶堆)
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t2 - t1;
            }
        });

        int finishDir = 0;
        for (int i = 0; i < stations.length; i++) {
            //当前加油站，距离起始点的距离，和加油站存在的汽油
            int dir = stations[i][0] - finishDir;
            int fuel = stations[i][1];

            //如果汽车油量不够，则从副油箱加油
            while (leftFuel < dir) {
                if (queue.isEmpty()) {
                    return -1;
                }
                leftFuel += queue.poll();
                resNum++;
            }

            //减去路程消耗
            leftFuel -= dir;

            //如果剩下的油量可以到达目的地，则直接返回
            if (leftFuel >= target - stations[i][0]) {
                return resNum;
            }

            //本站汽油保存到副邮箱
            queue.offer(fuel);

            finishDir = stations[i][0];
        }

        //后面没有加油站了
        while (leftFuel < target - finishDir) {
            if (queue.isEmpty()) {
                return -1;
            }
            leftFuel += queue.poll();
            resNum++;
        }

        return resNum;
    }

    /**
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
     * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
     * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
     * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
     * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
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
