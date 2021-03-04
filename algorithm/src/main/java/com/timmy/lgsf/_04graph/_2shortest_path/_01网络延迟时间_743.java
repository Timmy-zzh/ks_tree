package com.timmy.lgsf._04graph._2shortest_path;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;

public class _01网络延迟时间_743 {

    public static void main(String[] args) {
        _01网络延迟时间_743 demo = new _01网络延迟时间_743();
        int[][] times = {
                {2, 5, 8},
                {2, 6, 2},
                {6, 4, 2},
                {6, 1, 6},
                {4, 5, 1},
                {4, 1, 3},
                {4, 3, 1},
                {1, 3, 3},
                {5, 7, 3},
                {3, 7, 2},
        };
        int result = demo.networkDelayTime(times, 7, 2);
        System.out.println("result:" + result);
    }

    /**
     * 采用迪杰斯特拉 最短路径算法实现：该算法思路是 贪心+广度优先搜索
     * 2。解题思路
     * -从源节点开始，采用广度优先遍历方法，查找当前节点关联的所有目标节点，
     * --选取路径最短的那个节点，并将遍历到的节点保存到已遍历的集合D中，
     * --然后从集合D已遍历的节点遍历，找到所有的下层目标节点，并筛选出最短路径的那个节点
     * -找到下一次需要跳转的节点，遍历他的下层所有目标节点，
     * -当所有的目标节点都遍历到了，则结束
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    private int networkDelayTime(int[][] times, int n, int k) {
        //1。根据二维数组创建邻接表
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<int[]>());
        }
        for (int i = 0; i < times.length; i++) {
            int[] time = times[i];
            adj.get(time[0]).add(new int[]{time[1], time[2]});
        }
        for (ArrayList<int[]> arrayList : adj) {
            for (int[] ints : arrayList) {
                System.out.println(ints[0] + " , " + ints[1]);
            }
        }

        //2.迪杰斯特拉算法求解最短路径，从源节点开始找到所有节点的路径，并将结果保存到int[]数组中
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[k] = 0;

        boolean[] visited = new boolean[n + 1];
        //从未遍历的节点集合中找到路径最短的节点
        int nextNodeIndex = -1;
        int nextNodeShortestDist = Integer.MAX_VALUE;//最短路径
        while (true) {
            nextNodeIndex = -1;
            nextNodeShortestDist = Integer.MAX_VALUE;
            for (int i = 1; i < dist.length; i++) {
                if (!visited[i] && dist[i] < nextNodeShortestDist) {  //从未遍历过的节点集合中，找到最短路径的节点--贪心
                    nextNodeIndex = i;
                    nextNodeShortestDist = dist[i];
                }
            }

            //没有未遍历的节点
            if (nextNodeIndex < 0) {
                break;
            }
            System.out.println("nextNodeIndex:" + nextNodeIndex);
            visited[nextNodeIndex] = true;
            //拿着过滤后的节点，进行广度优先遍历，贪心算法 ，并记录当前节点所有目标节点的路径 -- 重要作用是记录
            ArrayList<int[]> nextNodeList = adj.get(nextNodeIndex);
            for (int i = 0; i < nextNodeList.size(); i++) {
                int[] nextNode = nextNodeList.get(i);   //下一个节点的值 和 权重
                //记录
                dist[nextNode[0]] = Math.min(dist[nextNode[0]], nextNode[1] + dist[nextNodeIndex]);
            }
        }

        PrintUtils.print(dist);
        //遍历已记录好的路径值
        int res = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, dist[i]);
        }
        return res;
    }

    /**
     * 1。理解题意
     * -输入一个二维矩阵，每个元素值为[ui, vi, wi]，表示从节点ui开始vi 节点，其中花费了多长时间wi
     * -一共有n个节点，求从k节点开始发出信息，到所有节点都收到信息，一共需要话费的最短时间
     * 2。解题思路
     * -根据输入数组创建邻接表，所有的邻接表元素保存在ArrayList中，
     * --其中每个节点的临界点有多个，也是使用ArrayList保存，其中花费时间wi，也放在集合中，和vi一起以数组方式保存[vi,wi]
     * -深度优先遍历所有节点，从k节点开始，不断遍历目标节点，并记录保存目标节点到当前节点的最短路径
     * --使用一个int[]数组进行记录，数组默认保存无穷大值，当遍历到最深层节点，或已存在更短路径时，返回
     * 3。边界与细节问题
     * -因为节点值从1开始，所以数组大小使用N+1
     * -邻接表需要保存当前节点到目标节点这条线的 目标节点值和 权重大小，所以需要保存一个int[]数组
     * -使用一个int[]数组保存起始节点到 各个节点的路径长度，沿着链路和上层节点的路径长度，推算出到下层节点的路径长度
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    private int networkDelayTime_v1(int[][] times, int n, int k) {
        //1。根据二维数组创建邻接表
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n + 1);
        for (int i = 1; i <= n; i++) {
            adj.add(new ArrayList<int[]>());
        }
        for (int i = 0; i < times.length; i++) {
            int[] time = times[i];//[ui, vi, wi]
            //adj.get(time[0]) -- 起始节点
            //关联起来： 起始节点到 目标节点time[1]，和之间的权重time[2]
            adj.get(time[0]).add(new int[]{time[1], time[2]});
        }
        System.out.println(adj.toString());

        //2.dfs 深度优先遍历，并将结果保存到int[]数组中
        int[] dist = new int[n + 1];
        for (int i = 1; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dfs(adj, dist, k, 0);

        PrintUtils.print(dist);
        //遍历已记录好的路径值
        int res = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, dist[i]);
        }
        return res;
    }

    /**
     * 深度优先遍历
     *
     * @param adj   链接表
     * @param dist  保存从开始节点k到其他节点的最短路径（默认最大值）
     * @param start 当前遍历到的节点
     * @param len   开始节点到当前节点的路径长度
     */
    private void dfs(ArrayList<ArrayList<int[]>> adj, int[] dist, int start, int len) {
        System.out.println("start:" + start + " --- len:" + len);
        if (len >= dist[start]) {
            return;
        }
        dist[start] = len;
        //获取当前节点start，所有的目标节点集合，并深度遍历
        ArrayList<int[]> nextList = adj.get(start);
        for (int i = 0; i < nextList.size(); i++) {
            int[] nextNode = nextList.get(i);// 其中一个节点
            dfs(adj, dist, nextNode[0], len + nextNode[1]);
        }
    }


    /**
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，
     * 其中 ui 是源节点，vi 是目标节点，
     * wi 是一个信号从源节点传递到目标节点的时间。
     *
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     *
     * 示例 1：
     * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * 输出：2
     *
     * 示例 2：
     * 输入：times = [[1,2,1]], n = 2, k = 1
     * 输出：1
     *
     * 示例 3：
     * 输入：times = [[1,2,1]], n = 2, k = 2
     * 输出：-1
     *
     * 链接：https://leetcode-cn.com/problems/network-delay-time
     */
}
