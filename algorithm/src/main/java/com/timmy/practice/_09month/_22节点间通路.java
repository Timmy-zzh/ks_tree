package com.timmy.practice._09month;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _22节点间通路 {

    public static void main(String[] args) {
        _22节点间通路 demo = new _22节点间通路();
//        int[][] graph = {{0, 1}, {0, 2}, {1, 2}, {1, 2}};
//        boolean res = demo.findWhetherExistsPath(3, graph, 0, 2);
        int[][] graph = {{0, 1}, {0, 2}, {0, 4}, {0, 4}, {0, 1}, {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3, 4}};
        boolean res = demo.findWhetherExistsPath(5, graph, 0, 4);
        System.out.println("res:" + res);
    }

    /**
     * 2.dfs 深度优先遍历
     * -创建图结构（临街表）
     * -从开始顶点start，不断递归，获取当前顶点的临接点集合，然后沿着这条路径不断往下遍历
     * --当遇到目标顶点，则返回true；整体结束
     * -否则需要遍历所有的路径
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            int[] edg = graph[i];
            adj.get(edg[0]).add(edg[1]);
        }
        boolean[] visited = new boolean[n];
        return dfs(adj, visited, start, target);
    }

    private boolean dfs(List<ArrayList<Integer>> adj, boolean[] visited, int start, int target) {
        visited[start] = true;
        if (start == target) {
            return true;
        }
        ArrayList<Integer> linkList = adj.get(start);
        for (int i = 0; i < linkList.size(); i++) {
            Integer nextNode = linkList.get(i);
            if (!visited[nextNode]) {
                boolean res = dfs(adj, visited, nextNode, target);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 1。理解题意
     * -输入一个图，图由n个顶点和边组成，使用临接表进行表示，求从start顶点到target顶点是否存在一条路径
     * 2。解题思路
     * -图的表示：将二维矩阵转换成临街表 ArrayList<ArrayList/>
     * -bfs：广度优先遍历，从start顶点开始遍历存放到队列中，并不断从队列中取出顶点，并且将临接点保存到队列中去
     * --在从队列取出顶点时，判断是否是目标target顶点，是的话返回true
     * -为防止出现环，采用标记法标示顶点是否已经遍历过
     */
    public boolean findWhetherExistsPath_v1(int n, int[][] graph, int start, int target) {
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            int[] edg = graph[i];
            adj.get(edg[0]).add(edg[1]);
        }
        boolean[] visited = new boolean[n];

        Deque<Integer> deque = new LinkedList<>();
        deque.push(start);

        while (!deque.isEmpty()) {
            Integer pop = deque.pop();
            visited[pop] = true;
            if (pop == target) {
                return true;
            }
            ArrayList<Integer> linkList = adj.get(pop);//临接集合
            for (Integer nextNode : linkList) {
                if (!visited[nextNode]) {
                    deque.push(nextNode);
                }
            }
        }

        return false;
    }

    /**
     * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
     *
     * 示例1:
     *  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
     *  输出：true
     *
     * 示例2:
     *  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]],
     *  start = 0, target = 4
     *  输出 true
     *
     * 提示：
     * 节点数量n在[0, 1e5]范围内。
     * 节点编号大于等于 0 小于 n。
     * 图中可能存在自环和平行边。
     * 链接：https://leetcode-cn.com/problems/route-between-nodes-lcci
     */
}
