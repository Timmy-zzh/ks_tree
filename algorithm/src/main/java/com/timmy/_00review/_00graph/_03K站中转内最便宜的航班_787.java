package com.timmy._00review._00graph;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class _03K站中转内最便宜的航班_787 {

    public static void main(String[] args) {
        _03K站中转内最便宜的航班_787 demo = new _03K站中转内最便宜的航班_787();
//        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
//        int res = demo.findCheapestPrice(3, flights, 0, 2, 1);
//        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
//        int res = demo.findCheapestPrice(3, flights, 0, 2, 0);

        /**
         * 4
         * [[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
         * 0
         * 3
         * 1
         */
        int[][] flights = {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}};
        int res = demo.findCheapestPrice(4, flights, 0, 3, 1);

        System.out.println("res:" + res);
    }

    /**
     * 使用深度优先算法
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<List<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<int[]>());
        }
        for (int i = 0; i < flights.length; i++) {
            int[] flight = flights[i];
            int start = flight[0];
            int end = flight[1];
            int price = flight[2];
            adj.get(start).add(new int[]{end, price});
        }

        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        int count = 0;  //中转次数
        dfs(adj, K, prices, src, count);

        PrintUtils.print(prices);
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    private void dfs(List<List<int[]>> adj, int K, int[] prices, int preNode, int count) {
        if (count > K) {
            return;
        }
        //遍历邻接节点集合
        List<int[]> linkNodes = adj.get(preNode);
        for (int i = 0; i < linkNodes.size(); i++) {
            int[] linkNode = linkNodes.get(i);
            int edgNode = linkNode[0];
            int edgPrice = linkNode[1];
            if (prices[preNode] + edgPrice < prices[edgNode]) {
                prices[edgNode] = prices[preNode] + edgPrice;
            }
            dfs(adj, K, prices, edgNode, count + 1);
        }
    }


    /**
     * 1.理解题意
     * -给定由n个城市和m个航班，组成的图结构，现在要求从src城市出发到达dst城市，费用最低
     * --并且只可以中转k值
     * 2.解题思路
     * 广度优先遍历
     * -将图使用邻接举证表示，
     * -使用数组int[] prices表示从出发城市到某个城市的花费的价格，
     * -在广度优先遍历的过程中，增加中转次数
     */
    public int findCheapestPrice_v1(int n, int[][] flights, int src, int dst, int K) {
        List<List<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<int[]>());
        }
        for (int i = 0; i < flights.length; i++) {
            int[] flight = flights[i];
            int start = flight[0];
            int end = flight[1];
            int price = flight[2];
            adj.get(start).add(new int[]{end, price});
        }

        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        int count = 0;      //中转次数
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                //查找当前节点poll，相邻的节点集合
                List<int[]> linkNodes = adj.get(poll);
                for (int j = 0; j < linkNodes.size(); j++) {
                    int[] linkNode = linkNodes.get(j);
                    int edgNode = linkNode[0];
                    int edgPrice = linkNode[1];

                    if (prices[poll] + edgPrice < prices[edgNode] && count + 1 <= K) {
                        prices[edgNode] = prices[poll] + edgPrice;
                    }
                    queue.add(edgNode);
                }
            }
            count++;
//            if (++count > K) {
//                break;
//            }
        }
        PrintUtils.print(prices);
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    /**
     * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
     * 你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
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
     *
     * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
     *
     * 提示：
     * n 范围是 [1, 100]，城市标签从 0 到 n - 1
     * 航班数量范围是 [0, n * (n - 1) / 2]
     * 每个航班的格式 (src, dst, price)
     * 每个航班的价格范围是 [1, 10000]
     * k 范围是 [0, n - 1]
     * 航班没有重复，且不存在自环
     *
     * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
     */
}
