package com.timmy.lgsf._04graph._2shortest_path;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;

class _03概率最大的路径_1514 {

    public static void main(String[] args) {
        System.out.println("111");
        _03概率最大的路径_1514 demo = new _03概率最大的路径_1514();
//        int[][] edges = {
//                {0, 1},
//                {1, 2},
//                {0, 2}};
//        double[] succProb = {0.5, 0.5, 0.2};
//        double result = demo.maxProbability(3, edges, succProb, 0, 2);
        int[][] edges = {
                {0, 1},
                {1, 2},
                {0, 2}};
        double[] succProb = {0.5, 0.5, 0.3};
        double result = demo.maxProbability(3, edges, succProb, 0, 2);
        System.out.println("result:" + result);
    }

    /**
     * 迪杰斯特拉算法：求解开始节点start到目标节点end的最大成功率
     * 1。根据二维矩阵，构建图的邻接表
     * 2。从未遍历的节点集合中查找 最大成功率的下一个节点
     *
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        ArrayList<ArrayList<double[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<double[]>());
        }

        for (int i = 0; i < edges.length; i++) {
            //[0,1]
            int[] edge = edges[i];
            adj.get(edge[0]).add(new double[]{edge[1], succProb[i]});
            adj.get(edge[1]).add(new double[]{edge[0], succProb[i]});
        }
        for (int i = 0; i < adj.size(); i++) {
            ArrayList<double[]> list = adj.get(i);
            System.out.print("i:" + i + " -- ");
            for (double[] ints : list) {
                PrintUtils.print(ints);
            }
        }
        System.out.println();
        System.out.println("2222");
        //
        boolean[] visited = new boolean[n];
        double[] dists = new double[n];
        for (int i = 0; i < n; i++) {
            dists[i] = Integer.MIN_VALUE;
        }
        dists[start] = 1;
        int index = -1;
        double laggest = Integer.MIN_VALUE;

        while (true) {
            index = -1;
            laggest = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && dists[i] > laggest) {
                    index = i;
                    laggest = dists[i];
                }
            }
            System.out.println("index:" + index);

            if (index < 0) {
                break;
            }
            visited[index] = true;

            ArrayList<double[]> list = adj.get(index);
            for (int i = 0; i < list.size(); i++) {
                double[] nextNode = list.get(i);
                double nextNodeValue = nextNode[0];
                double nextNodeWeight = nextNode[1];

                dists[(int) nextNodeValue] = Math.max(dists[(int) nextNodeValue], dists[index] * nextNodeWeight);
            }
        }

        PrintUtils.print(dists);

        if (dists[end] == Integer.MIN_VALUE) {
            return 0;
        }
        return dists[end];
    }

    /**
     * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，
     * 其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
     *
     * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
     *
     * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
     *
     * 示例 1：
     * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
     * 输出：0.25000
     * 解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
     *
     * 示例 2：
     * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
     * 输出：0.30000
     * 示例 3：
     *
     * 输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
     * 输出：0.00000
     * 解释：节点 0 和 节点 2 之间不存在路径
     *
     * 提示：
     *
     * 2 <= n <= 10^4
     * 0 <= start, end < n
     * start != end
     * 0 <= a, b < n
     * a != b
     * 0 <= succProb.length == edges.length <= 2*10^4
     * 0 <= succProb[i] <= 1
     * 每两个节点之间最多有一条边
     *
     * 链接：https://leetcode-cn.com/problems/path-with-maximum-probability
     */
}
