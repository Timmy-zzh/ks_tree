package com.timmy.lgsf._04graph._23graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class _01节点间通路 {

    public static void main(String[] args) {
        _01节点间通路 demo = new _01节点间通路();
//        int[][] graph = {
//                {0, 1},
//                {0, 2},
//                {1, 2},
//                {1, 2}};
        int[][] graph = {{0, 1}, {0, 2}, {0, 4}, {0, 4}, {0, 1}, {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3, 4}};
        boolean result = demo.findWhetherExistsPath(5, graph, 2, 4);
        System.out.println("result:" + result);

    }

    /**
     * 深度优先遍历
     * 2。解题思路
     * -构建表示图结构的邻接表
     * -递归
     * --从start节点开始，不断获取相连的目标节点（集合），接着从目标节点开始继续递归
     * --如果递归的开始节点 与target值相等，则返回true，上层递归获取到返回值进行不同逻辑判断
     * 3。边界与细节问题
     * -回溯
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
//    ArrayList<ArrayList<Integer>> obj;
//    boolean[] visited;
    private boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        ArrayList<ArrayList<Integer>> obj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            obj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            obj.get(graph[i][0]).add(graph[i][1]);
        }
        System.out.println(obj.toString());

        //广度优先遍历
        //使用boolean[]数组记录已访问节点
        boolean[] visited = new boolean[n];
        //使用队列保存
        visited[start] = true;

        return dfs(obj, visited, start, target);
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> obj, boolean[] visited, int start, int target) {
        if (start == target) {
            return true;
        }
        //当前节点的目标节点集合
        ArrayList<Integer> nodeList = obj.get(start);
        for (Integer node : nodeList) {
            if (!visited[node]) {
                boolean exist = dfs(obj, visited, node, target);
                if (exist) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 1。理解题意
     * -输入一个图的数据（包括图的节点和图中节点的关系），判断从开始节点到目标节点是否存在路径？
     * 2。解题思路
     * -根据图中节点二维数组关系构建图的邻接表，其中每个节点都有一个集合用于保存该节点出发指向的下一节点
     * -广度优先遍历
     * --将遍历到的节点保存到队列中，然后从队头不断取出元素，获取元素的临界点入队列，判断临界点是否有存在目标值
     * 3。边界和细节问题
     * -注意检索过的节点不应该再次遍历，解决方法是 使用一个boolean[] 数组记录遍历过的节点
     *
     * @param n      图中节点的个数
     * @param graph  图中节点a到节点b的方向 --有向图
     * @param start  检索开始点
     * @param target 检索目标点
     * @return
     */
    private boolean findWhetherExistsPath_bfs(int n, int[][] graph, int start, int target) {
        // 根据图的节点个数，创建邻接表，每个节点都有一个集合用于保存他指向的节点
        ArrayList<ArrayList<Integer>> obj = new ArrayList<>(n);

        //根据graph数组构建邻接表
        for (int i = 0; i < n; i++) {
            obj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            obj.get(graph[i][0]).add(graph[i][1]);
        }
        System.out.println(obj.toString());

        //广度优先遍历
        //使用boolean[]数组记录已访问节点
        boolean[] visited = new boolean[n];
        //使用队列保存
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            // 从队列中取出节点
            Integer nodeIndex = queue.poll();
            // 获取节点所有指向的 目标节点
            ArrayList<Integer> nodeLists = obj.get(nodeIndex);
            for (Integer node : nodeLists) {
                if (node == target) {
                    return true;
                }
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
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
     *  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
     *  输出 true
     *
     * 链接：https://leetcode-cn.com/problems/route-between-nodes-lcci
     */

}
