package com.timmy._review._00graph;

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
         * 0  3  1
         */
        int[][] flights = {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}};
        int res = demo.findCheapestPrice(4, flights, 0, 3, 1);

        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入二维数组表示n个城市之间的m个航班组成的图结构，从城市节点src出发到城市dst，求路程的最少花销，
     * --要求航班中转次数最多为K次
     * 2。解题思路
     * -该问题可以转化为：图中节点src到dst节点的最短路径，但是有个条件是中转次数不能超过K次
     * 深度优先算法+回溯算法
     * -先使用邻接表表示图结构，从src开始深度优先遍历，从src出发的花费为0，
     * --dfs的写法参数中，找出src的相邻节点，继续深度优先遍历，并做好回溯工作，bool[] visited状态恢复
     * -当遍历到目标节点时记录当前路径的花费，
     * -如果当前深度优先遍历路径的中转次数达到K次，进行处理
     * 3。边界和细节问题
     * TODO ：核心思想--在每条路径上去寻找可以到达目标城市dst的路径，并求取路径的花费
     * --为防止存在环情况，需要使用visited判断防止死循环
     * -中转次数K，表示需要经过的节点为K+1
     */
    int resPrice = Integer.MAX_VALUE;// 最终所求的最低价格，到达不了目标城市返回-1

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<int[]>());
        }
        for (int[] flight : flights) {
            int start = flight[0];
            int end = flight[1];
            int price = flight[2];
            adj.get(start).add(new int[]{end, price});
        }

        boolean visited[] = new boolean[n];
        visited[src] = true;
        dfs(adj, src, dst, K + 1, 0, visited);

        return resPrice == Integer.MAX_VALUE ? -1 : resPrice;
    }

    /**
     * @param adj     邻接表
     * @param src     上一次开始的节点
     * @param dst     最总需要达到的目标节点
     * @param k       中转节点
     * @param price   深度优先到src节点时，该条路径的花费
     * @param visited 防止出现环，并进行回溯控制
     */
    private void dfs(List<List<int[]>> adj, int src, int dst, int k, int price, boolean[] visited) {
        System.out.println("src:" + src + " ,k:" + k + " ,price:" + price);
        PrintUtils.print(visited);
        if (src == dst) {
            //1。该条路径到达目标节点，结束遍历，并保存这条路径上的花费
            resPrice = price;
            return;
        }
        if (k == 0) {  //2。中转次数的节点为0，路径不能再继续往下延伸了
            return;
        }
        //3。查找src节点的相邻节点集合，并继续遍历
        List<int[]> linkNodes = adj.get(src);
        for (int i = 0; i < linkNodes.size(); i++) {
            int[] linkNode = linkNodes.get(i);
            int node = linkNode[0];
            int edgePrice = linkNode[1];

            //4.1.判断该相邻节点是否遍历过
            if (visited[node]) {
                continue;
            }
            //4.2.剪枝--判断已走过的路径，在加上这段边edge的花费是否超过已知路径花费，超过的话，这条路径页没必要延伸了
            if (price + edgePrice > resPrice) {
                continue;
            }
            visited[node] = true;

            //5。路径往node相邻节点延伸
            dfs(adj, node, dst, k - 1, price + edgePrice, visited);

            //6。回溯处理
            visited[node] = false;
        }
    }

    /**
     * TODO 深度优先算法--失败
     * 使用深度优先算法
     */
    public int findCheapestPrice_v2(int n, int[][] flights, int src, int dst, int K) {
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
        dfs_v2(adj, K, prices, src, count);

        PrintUtils.print(prices);
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    private void dfs_v2(List<List<int[]>> adj, int K, int[] prices, int preNode, int count) {
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
            dfs_v2(adj, K, prices, edgNode, count + 1);
        }
    }


    /**
     * TODO 广度优先算法--失败
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
