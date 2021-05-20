package com.timmy._review._06graph._00graph;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class _02图的遍历 {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1}, {0, 3}, {0, 6},
                {1, 4}, {1, 5}, {1, 0},
                {2, 5}, {2, 7}, {3, 5},
                {3, 0}, {4, 1}, {4, 6},
                {5, 1}, {5, 2}, {5, 3},
                {6, 0}, {6, 4}, {7, 2},};
        _02图的遍历 demo = new _02图的遍历();
        demo.traverse(graph, 8);
    }

    /**
     * 2。广度优先遍历
     * 使用队列辅助
     */
    public void traverse(int[][] graph, int n) {
        //邻接矩阵
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            int[] edg = graph[i];
            adj.get(edg[0]).add(edg[1]);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        visited[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
                Integer poll = queue.remove();
                System.out.println("bfs:" + poll);
                List<Integer> linkNodes = adj.get(poll);
                for (int j = 0; j < linkNodes.size(); j++) {
                    Integer linkNode = linkNodes.get(j);
                    if (!visited[linkNode]) {
                        visited[linkNode] = true;
                        queue.add(linkNode);
                    }
                }
//            }
        }
    }

    /**
     * 2.深度优先遍历-迭代法
     * 使用Stack辅助数据结构
     * -遍历所有未访问的节点，添加到栈中，出栈的时侯，将出栈节点相邻的节点集合入栈，
     */
    public void traverse_v2(int[][] graph, int n) {
        //邻接矩阵
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            int[] edg = graph[i];
            adj.get(edg[0]).add(edg[1]);
        }
        for (List<Integer> list : adj) {
            System.out.println("-----");
            PrintUtils.print(list);
        }
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        visited[0] = true;
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            System.out.println("pop:" + pop);
            //相邻节点集合
            List<Integer> linkNodes = adj.get(pop);
            for (int i = 0; i < linkNodes.size(); i++) {
                Integer linkNode = linkNodes.get(i);
                if (!visited[linkNode]) {
                    visited[linkNode] = true;
                    stack.push(linkNode);
                }
            }
        }

    }

    /**
     * 1。理解题意
     * -输入一个二维矩阵，二维矩阵表示顶点到顶点的关系（Edge边），通过该关系遍历整个图中的顶点
     * 2。解题思路：深度优先遍历dfs
     * -将二维矩阵使用邻接矩阵进行表示图结构，从顶点0开始通过深度优先遍历
     * -递归实现
     * 3。细节处理
     * -使用boolean[] visited 表示顶点是否已经被访问过
     */
    public void traverse_v1(int[][] graph, int n) {
        //邻接矩阵
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            int[] edg = graph[i];
            adj.get(edg[0]).add(edg[1]);
        }
        for (List<Integer> list : adj) {
            System.out.println("-----");
            PrintUtils.print(list);
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(adj, visited, i);
            }
        }
    }

    /**
     * @param adj
     * @param visited
     * @param curr    当前遍历的节点
     */
    private void dfs(List<List<Integer>> adj, boolean[] visited, int curr) {
        System.out.println("dfs:" + curr);
        //与i相邻的节点
        List<Integer> linkNodes = adj.get(curr);
        for (int j = 0; j < linkNodes.size(); j++) {
            Integer linkNode = linkNodes.get(j);
            if (!visited[linkNode]) {
                visited[linkNode] = true;
                dfs(adj, visited, linkNode);
            }
        }
    }

    /**
     * 假设我们有这么一个图，里面有A、B、C、D、E、F、G、H 8 个顶点，点和点之间的联系如下图所示，
     * 对这个图进行遍历。
     */
}
