package com.timmy._00review._00graph;

import com.timmy.common.PrintUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _01判断二分图_785 {

    public static void main(String[] args) {
        _01判断二分图_785 demo = new _01判断二分图_785();
        int[][] graph = {
                {1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}
        };
//        int[][] graph = {
//                {1, 3}, {0, 2}, {1, 3}, {0, 2}
//        };
        boolean bipartite = demo.isBipartite(graph);
        System.out.println("res:" + bipartite);

    }

    /**
     * 前面的广度优先遍历，存在一种情况，如果图中节点不是联通图时，会遍历不到，所有需要进行处理
     */
    public boolean isBipartite(int[][] graph) {
        int uncolor = 0;
        int red = 1;
        int green = 2;

        int n = graph.length;
        int[] color = new int[n];
        //默认都是未着色节点
        Arrays.fill(color, uncolor);

        for (int i = 0; i < n; i++) {
            if (color[i] == uncolor) {
                Queue<Integer> queue = new LinkedList<>();
                color[i] = red;
                queue.offer(i);
                while (!queue.isEmpty()) {
                    System.out.println("----");
                    //取出队列中的节点，并遍历与之相邻的节点，并给相邻的节点进行着色（未着色），有着色则判断是否是相对的颜色
                    Integer poll = queue.poll();
                    //根据已着色节点，求相邻节点希望着色的颜色
                    int wantColor = color[poll] == red ? green : red;

                    int[] linkNodes = graph[poll];
                    for (int node : linkNodes) {
                        if (color[node] == uncolor) {
                            System.out.println("颜色------：");
                            PrintUtils.print(color);
                            System.out.println();
                            System.out.println(queue.toString());
                            color[node] = wantColor;
                            queue.offer(node);
                        } else if (color[node] != wantColor) {
                            System.out.println("颜色：");
                            PrintUtils.print(color);
                            System.out.println();
                            System.out.println(queue.toString());
                            return false;
                        }
                    }
                }
            }
        }
        System.out.println("颜色======：");
        PrintUtils.print(color);
        System.out.println();
        return true;
    }

    /**
     * 1.理解题意
     * -输入一个二维数组，表示图中节点的关系，求这个图是否是二分图，二分图的判断条件是图中的节点分为两个子集A和B，
     * --每两条边上的两个节点，分别在集合A和集合B中，这个问题可以转换成图中节点的着色问题
     * 2。解题思路：节点着色+广度优先算法
     * -创建一个大小为n的int数组，该数组用于标示n个节点的着色情况，刚开始默认都是未着色的
     * -从0节点开始给它着色红色，入队列，-然后出队列，将与0号节点相连的节点着色绿色，并入队列，
     * -通过广度优先遍历，根据已经着色的节点，确定与之相连的节点的颜色：
     * --如果相连节点之前没有着色，则着色相对颜色，
     * --如果相邻节点已经着色了，则判断是否与希望着色的颜色一致，否则就不是一个二分图
     */
    public boolean isBipartite_v1(int[][] graph) {
        int uncolor = 0;
        int red = 1;
        int green = 2;

        int n = graph.length;
        int[] color = new int[n];
        //默认都是未着色节点
        Arrays.fill(color, uncolor);

        Queue<Integer> queue = new LinkedList<>();
        color[0] = red;
        queue.offer(0);

        while (!queue.isEmpty()) {
            //取出队列中的节点，并遍历与之相邻的节点，并给相邻的节点进行着色（未着色），有着色则判断是否是相对的颜色
            Integer poll = queue.poll();
            //根据已着色节点，求相邻节点希望着色的颜色
            int wantColor = color[poll] == red ? green : red;

            int[] linkNodes = graph[poll];
            for (int node : linkNodes) {
                if (color[node] == uncolor) {
                    color[node] = wantColor;
                    queue.offer(node);
                } else if (color[node] != wantColor) {
                    System.out.println("颜色：");
                    PrintUtils.print(color);
                    System.out.println();
                    System.out.println(queue.toString());
                    return false;
                }
            }
        }
        System.out.println("颜色：");
        PrintUtils.print(color);
        System.out.println();
        System.out.println(queue.toString());
        return true;
    }

    /**
     * 存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。
     * 给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。
     * 形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有以下属性：
     * 不存在自环（graph[u] 不包含 u）。
     * 不存在平行边（graph[u] 不包含重复值）。
     *
     * 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
     * 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
     * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合
     * ，一个来自 B 集合，就将这个图称为 二分图 。
     *
     * 如果图是二分图，返回 true ；否则，返回 false 。
     *
     * 示例 1：
     * 输入：graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
     * 输出：false
     * 解释：不能将节点分割成两个独立的子集，以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。
     *
     * 示例 2：
     * 输入：graph = [[1,3],[0,2],[1,3],[0,2]]
     * 输出：true
     * 解释：可以将节点分成两组: {0, 2} 和 {1, 3} 。
     *  
     *
     * 提示：
     * graph.length == n
     * 1 <= n <= 100
     * 0 <= graph[u].length < n
     * 0 <= graph[u][i] <= n - 1
     * graph[u] 不会包含 u
     * graph[u] 的所有值 互不相同
     * 如果 graph[u] 包含 v，那么 graph[v] 也会包含 u
     *
     * 链接：https://leetcode-cn.com/problems/is-graph-bipartite
     */
}
