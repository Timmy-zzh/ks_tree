package com.timmy.lgsf._04graph._27minimum_spanning_tree;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _01找到最小生成树里的关键边和伪关键边_1489_v2 {

    public static void main(String[] args) {
        _01找到最小生成树里的关键边和伪关键边_1489_v2 demo = new _01找到最小生成树里的关键边和伪关键边_1489_v2();
        int[][] edges = {
                {0, 1, 1},
                {1, 2, 1},
                {2, 3, 2},
                {0, 3, 2},
                {0, 4, 3},
                {3, 4, 3},
                {1, 4, 6},};
        int n = 5;

//        int[][] edges = {
//                {0, 1, 1},
//                {1, 2, 1},
//                {0, 2, 1},
//                {2, 3, 4},
//                {3, 4, 2},
//                {3, 5, 2},
//                {4, 5, 2},};
//        int n = 6;

        List<List<Integer>> result = demo.findCriticalAndPseudoCriticalEdges(n, edges);
        System.out.println("=====");
        for (List<Integer> re : result) {
            PrintUtils.print(re);
        }
    }


    List<List<int[]>> adjList;

    private void initAdj(int n, int[][] edges) {
        //n个节点，每个节点都有一个集合对应相邻点
        adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<int[]>());
        }
        for (int i = 0; i < edges.length; i++) {
            //1, 2, 1
            int[] edge = edges[i];
            //无向图，邻接表需要两个顶点都设置为相互指向的边
            adjList.get(edge[0]).add(edge);
            adjList.get(edge[1]).add(edge);
        }
    }

    /**
     * 1.理解题意
     * -输入二维数组，表示顶点i到顶点j，和两个顶点之间的权重；先求最小生成树，因为生成树不止一个，求出关键边和伪关键边
     * 2。解题思路：普林姆算法（Prim）
     * -从顶点角度考虑
     * -2。1。根据二维矩阵求出 图的表示（邻接表/或邻接矩阵）
     * -2。2。使用一个数组boolean[] visited ;表示当前已经遍历的节点（统称为集合A），默认从0节点开始查找，
     * --然后查找跟0节点相邻的节点集合，从中选出权值最小的边，边对应的顶点添加到集合A中
     * -2。3。继续遍历集合A，从集合A相连的顶点中，再找出权值最小的边，将对应顶点添加到集合A中，不断循环操作，直至将所有的顶点都遍历了一遍
     * 3。边界和细节问题
     * -根据二维数组生成图的邻接表
     * -接着根据邻接表生成最小生成树
     * -求出关键边和伪关键边
     *
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        //1。创建邻接表
        initAdj(n, edges);

        //2。求最小生成树，并获取最小生成树的权值和
        int mstWeightSum = prim(n, edges, -1, false);
        System.out.println("mstWeightSum:" + mstWeightSum);

        //求关键边和伪关键边
        List<Integer> keyEdges = new ArrayList<>();
        List<Integer> osedEeges = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        res.add(keyEdges);
        res.add(osedEeges);

        //3.查找关键边和伪关键边
        for (int i = 0; i < edges.length; i++) {
            if (prim(n, edges, i, true) > mstWeightSum) {
                keyEdges.add(i);
            } else if (prim(n, edges, i, false) == mstWeightSum) {
                osedEeges.add(i);
            }
        }
        return res;
    }


    /**
     * 求最小生成树，prim算法：不断将遍历过的节点放到集合A中，然后从集合A中查找最小权重的边，和对应的节点，节点存放到集合A中
     *
     * @param n
     * @param edges
     * @param index 当前遍历到的原始图的第几条边
     * @param isKey 判断当前边是否是关键边
     * @return
     */
    private int prim(int n, int[][] edges, int index, boolean isKey) {
        //最小生成树的权重之和
        int mstWeightSum = 0;
        //最小生成树 的顶点个数
        int mstVertexCount = 0;

        //已经遍历过的顶点集合
        boolean[] visited = new boolean[n];

        //如果是求伪关键边,需要先将该条边上的两个节点进行遍历
        if (!isKey && index >= 0) {
            int[] edge = edges[index];
            visited[edge[0]] = true;
            visited[edge[1]] = true;
            mstVertexCount = 2;
            mstWeightSum += edge[2];
        } else {
            //默认从0节点开始查找最小权值边
            visited[0] = true;
            mstVertexCount++;
        }

        while (mstVertexCount < n) {
            //查找到的最小权重边
            int[] minEdge = null;
            int minWeightEdge = Integer.MAX_VALUE;//最小权重边

            //遍历所有顶点，从已查找的顶点集合中查找最小权重边
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    //顶点i，相邻的节点集合
                    List<int[]> adjNodeList = adjList.get(i);
                    for (int j = 0; j < adjNodeList.size(); j++) {
                        int[] edge = adjNodeList.get(j);
                        //如果是求关键边，则过滤continue
                        if (isKey && edge == edges[index]) {
                            continue;
                        }
                        //如果这条边上的两个顶点都遍历过了，则查找其他边
                        if (visited[edge[0]] && visited[edge[1]]) {
                            continue;
                        }
                        if (edge[2] < minWeightEdge) {
                            minWeightEdge = edge[2];
                            minEdge = edge;
                        }
                    }
                }
            }

            //查找到了，则 minEdge！=null
            if (minEdge != null) {
                mstVertexCount++;
                mstWeightSum += minEdge[2];
                if (visited[minEdge[0]]) {
                    visited[minEdge[1]] = true;
                } else {
                    visited[minEdge[0]] = true;
                }
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return mstWeightSum;
    }

    /**
     * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，
     * 其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。
     * 最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
     *
     * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，
     * 那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
     *
     * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
     *
     * 示例 1：
     *
     * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
     * 输出：[[0,1],[2,3,4,5]]
     * 解释：上图描述了给定图。
     * 下图是所有的最小生成树。
     *
     * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
     * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
     * 示例 2 ：
     *
     * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
     * 输出：[[],[0,1,2,3]]
     * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
     *
     * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
     */

}
