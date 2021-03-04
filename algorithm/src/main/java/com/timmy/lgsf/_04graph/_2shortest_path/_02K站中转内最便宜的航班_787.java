package com.timmy.lgsf._04graph._2shortest_path;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;

/**
 * TODO 未完成
 * TODO 需要二次研究，使用优先级队列解法
 */
class _02K站中转内最便宜的航班_787 {
    //n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    //     * src = 0, dst = 2, k = 1
    public static void main(String[] args) {
        _02K站中转内最便宜的航班_787 demo = new _02K站中转内最便宜的航班_787();
//        int[][] flights = {
//                {0, 1, 100},
//                {1, 2, 100},
//                {0, 2, 500},
//        };
//        int result = demo.findCheapestPrice(3, flights, 0, 2, 1);
        //5
        //[[1,2,10],[2,0,7],[1,3,8],[4,0,10],[3,4,2],[4,2,10],[0,3,3],[3,1,6],[2,4,5]]
        //0
        //4
        //1
        int[][] flights = {
                {1, 2, 10},
                {2, 0, 7},
                {1, 3, 8},
                {4, 0, 10},
                {3, 4, 2},
                {4, 2, 10},
                {0, 3, 3},
                {3, 1, 6},
                {2, 4, 5},
        };
        int result = demo.findCheapestPrice(5, flights, 0, 4, 1);
        System.out.println("result:" + result);
    }

    /**
     * 迪杰斯特拉算法：贪心+广度优先遍历
     * 1。构建图形结构 - 邻接表
     * 2。从源节点开始，不断寻找最近的到已遍历节点集合的目标节点，
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 1.创建邻接表
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<int[]>());
        }
        for (int i = 0; i < flights.length; i++) {
            // [0,1,100]
            int[] edge = flights[i];
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        for (int i = 0; i < adj.size(); i++) {
            ArrayList<int[]> list = adj.get(i);
            System.out.print("i:" + i + " -- ");
            for (int[] ints : list) {
                PrintUtils.print(ints);
            }
        }
        System.out.println();

        boolean[] visited = new boolean[n];
        int[] dists = new int[n];
        for (int i = 0; i < n; i++) {
            dists[i] = Integer.MAX_VALUE;
        }
        dists[src] = 0;

        int nextNodeIndex = -1;
        int nextNodeShortestDist = Integer.MAX_VALUE;
        int count = 0;
        while (true) {
            nextNodeIndex = -1;
            nextNodeShortestDist = Integer.MAX_VALUE;
            //1.从未遍历的节点集合中，查找最新的节点
            for (int i = 0; i < n; i++) {
                if (!visited[i] && dists[i] < nextNodeShortestDist) {
                    nextNodeIndex = i;
                    nextNodeShortestDist = dists[i];
                }
            }

            if (nextNodeIndex < 0) {
                break;
            }
            if (count++ > K) {
                break;
            }

            visited[nextNodeIndex] = true;

            //2.从过滤出来的目标节点，并将该节点添加到已遍历节点集合中，并更新dist数组
            ArrayList<int[]> list = adj.get(nextNodeIndex);
            for (int i = 0; i < list.size(); i++) {
                int[] nextNode = list.get(i);
                //目标节点
                int nextNodeValue = nextNode[0];
                //目标节点权重
                int nextNodeWeight = nextNode[1];

                //更新目标节点的路径长度
                dists[nextNodeValue] = Math.min(dists[nextNodeValue], dists[nextNodeIndex] + nextNodeWeight);
            }
        }
        PrintUtils.print(dists);

        if (dists[dst] == Integer.MAX_VALUE) {
            return -1;
        }
        return dists[dst];
    }

    /**
     * 1.构建图结构-邻接表
     * 2。记录从源节点到其他所有节点的路径，-使用int[]数组保存
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice_v2(int n, int[][] flights, int src, int dst, int K) {
        // 1.创建邻接表
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<int[]>());
        }
        for (int i = 0; i < flights.length; i++) {
            // [0,1,100]
            int[] edge = flights[i];
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        for (int i = 0; i < adj.size(); i++) {
            ArrayList<int[]> list = adj.get(i);
            System.out.print("i:" + i + " -- ");
            for (int[] ints : list) {
                PrintUtils.print(ints);
            }
        }
        System.out.println();

        //使用深度优先
        int[] dists = new int[n];
        for (int i = 0; i < n; i++) {
            dists[i] = Integer.MAX_VALUE;
        }
        dists[src] = 0;
        dfs(adj, src, dst, K, dists, 0, 0);

        PrintUtils.print(dists);

        if (dists[dst] == Integer.MAX_VALUE) {
            return -1;
        }
        return dists[dst];
    }

    private void dfs(ArrayList<ArrayList<int[]>> adj, int src,
                     int dst, int k, int[] dists, int count, int len) {
        System.out.println("src:" + src + " ,dst:" + dst + " ,count:" + count + " , len:" + len);
        if (src == dst) {
            return;
        }
        if (count > k) {
            return;
        }
        ArrayList<int[]> list = adj.get(src);
        for (int i = 0; i < list.size(); i++) {
            int[] ints = list.get(i);
            int nextNodeValue = ints[0];
            int nextNodeWeight = ints[1];

            dists[nextNodeValue] = Math.min(dists[nextNodeValue], nextNodeWeight + len);
            dfs(adj, nextNodeValue, dst, k, dists, count + 1, dists[nextNodeValue]);
        }
    }

    /**
     * 1.理解题意：
     * -n个城市，通过m个航班连接，由m和n可以组成图，其中有n个节点，m条边
     * -每个航班从城市u到v，航班的价格为w。
     * -求从城市src出发到目的地dst，找到最便宜的航班，可以允许中转k次，返回最便宜价格
     * -src到达不了src，返回-1
     * 2。解题思路
     * -根据二维矩阵创建图结构-邻接表
     * --由n个节点和m个边组成的集合ArrayList，每个节点保存了当前节点到下一目标节点的值和航班价格（使用数组表示int[]）
     * -根据邻接表，和起始节点src和目标节点dst，先求出起始节点到目标节点所有路径，和每条路径经过的中转次数
     * -采用深度优先遍历
     * 3。边界和细节问题
     * -创建邻接表-包含权重 ArrayList<ArrayList<int[]>>
     * -深度优先遍历，查找起始节点到目标节点的路径之后，并记录中转次数 -- 使用ArrarList<int[]> 保存
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice_v1(int n, int[][] flights, int src, int dst, int K) {
        // 1.创建邻接表
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<int[]>());
        }

        for (int i = 0; i < flights.length; i++) {
            // [0,1,100]
            int[] edge = flights[i];
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        for (int i = 0; i < adj.size(); i++) {
            ArrayList<int[]> list = adj.get(i);
            System.out.print("i:" + i + " -- ");
            for (int[] ints : list) {
                PrintUtils.print(ints);
            }
        }
        System.out.println();

        ArrayList<int[]> list = adj.get(src);//起始节点的目标节点集合
        int size = list.size();
        //保存路径
        ArrayList<Integer>[] paths = new ArrayList[size];
        //存放权重
        //保存中转次数
        int[] transferCount = new int[size];
        ArrayList<Integer>[] weights = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            paths[i] = new ArrayList<>();
            weights[i] = new ArrayList<>();
            transferCount[i] = 0;
        }

        //2。深度优先求起始节点到目标节点的
        for (int i = 0; i < list.size(); i++) {
            int[] nextNode = list.get(i);
            //目标值
            int targetNode = nextNode[0];
            //权重
            int weight = nextNode[1];

            paths[i].add(targetNode);
            weights[i].add(weight);
//            transferCount[i] = transferCount[i] + 1;
            dfs_v1(adj, targetNode, dst, K, i, paths, weights, transferCount);
        }

        //经过了那些边
        System.out.println("经过了那些节点");
        for (int i = 0; i < paths.length; i++) {
            ArrayList<Integer> path = paths[i];
            PrintUtils.print(path);
        }

        System.out.println("经过了那些权重");
        for (int i = 0; i < weights.length; i++) {
            ArrayList<Integer> weight = weights[i];
            PrintUtils.print(weight);
        }

        System.out.println("中转次数");
        for (int i : transferCount) {
            System.out.println(i + " , ");
        }

        //求最后节点和最短权重和
        int res = -1;
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> path = paths[i];
            if (path.get(path.size() - 1) == dst) {
                // 求权重和
                int sum = getWeightSum(weights[i]);
                if (res == -1) {
                    res = sum;
                }
                res = Math.min(sum, res);
            }
        }
        return res;
    }

    private int getWeightSum(ArrayList<Integer> weight) {
        int sum = 0;
        for (Integer integer : weight) {
            sum += integer;
        }
        return sum;
    }

    private void dfs_v1(ArrayList<ArrayList<int[]>> adj, int start, int dst,
                        int k, int index,
                        ArrayList<Integer>[] paths,
                        ArrayList<Integer>[] weights,
                        int[] transferCount) {
        System.out.println("start:" + start + " ,dst:" + dst + " ,index:" + index);
        if (start == dst) {
            return;
        }

        //减枝，去除中转次数超过K的值
        if (transferCount[index] >= k) {
            return;
        }

        ArrayList<int[]> list = adj.get(start);//起始节点的目标节点集合
        for (int i = 0; i < list.size(); i++) {
            int[] nextNode = list.get(i);
            //目标值
            int targetNode = nextNode[0];
            //权重
            int weight = nextNode[1];

            paths[index].add(targetNode);
            weights[index].add(weight);
            transferCount[index] = transferCount[index] + 1;
            dfs_v1(adj, targetNode, dst, k, index, paths, weights, transferCount);
        }
    }

    /**
     * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
     *
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst
     * 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
     *
     * 示例 1：
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * 输出: 200
     * 解释:
     * 城市航班图如下
     * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
     *
     * 示例 2：
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 0
     * 输出: 500
     * 解释:
     * 城市航班图如下
     * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
     *
     * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
     */
}
