package com.timmy._review._12dfs_bfs;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _07最安全的路径 {

    public static void main(String[] args) {
        _07最安全的路径 demo = new _07最安全的路径();
        int[] x = {0, 0, 1, 2, 2, 3};
        int[] y = {1, 2, 2, 3, 4, 4};
        int[] w = {1, 6, 2, 1, 5, 2};
        int res = demo.getMinRiskValue(5, 6, x, y, w);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入n个节点和m条边，还有三个数组表示节点到节点之间和边的权重，求从第1个点到第n个点走过的路径中危险系数最小的路径（每条边权重的最大值）
     * 2。解题思路：广度优先遍历 BFS
     * -从第1个节点开始往下层节点集合开始扩展，并记录到达当前节点的最小危险系数
     * -使用队列保存每层节点的坐标值
     * -默认到达下层节点是一个很大的数值，后面根据路径权重不断赋值，并且在其他路径到达该节点时，如果存在更小的危险系数，则进行替换更新
     */
    public int getMinRiskValue(int n, int m, int[] x, int[] y, int[] w) {
        //1。先创建图的邻接表
        List<List<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<int[]>());
        }

        for (int i = 0; i < m; i++) {
            int start = x[i];       //当前节点，后面的list是多个后继节点的数据
            int end = y[i];
            int weight = w[i];
            adj.get(start).add(new int[]{end, weight});
        }

        //保存每个节点默认的危险系数
        int[] risk = new int[n];
        for (int i = 1; i < n; i++) {
            risk[i] = Integer.MAX_VALUE;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            System.out.println("curr:" + curr);
            //当前poll节点，获取后继节点的集合
            List<int[]> nextPoints = adj.get(curr);
            for (int[] nextPoint : nextPoints) {
                int nextNode = nextPoint[0];
                int edgeWeight = nextPoint[1];
                System.out.println("-----next:" + nextNode + " ,risk[curr]:" + risk[curr] + " ,weight:" + edgeWeight);
                //上一个节点到后继点击的危险系数
                // risk[nextNode]
                int currNodeW = Math.max(risk[curr], edgeWeight);
                //在于已经存在的危险系数进行比较，如果更小则更新
                if (currNodeW < risk[nextNode]) {
                    risk[nextNode] = currNodeW;
                }
                queue.add(nextNode);
            }
        }
        PrintUtils.print(risk);

        return risk[n - 1];
    }

    /**
     * 【题目】在一个无向图上，给定点个数 n，编号从 [0~n]，再给定边的个数 m。
     * 其中每条边由x[i], y[i], w[i] 表示。x[i], y[i] 分别表示边上两点的编号而 w[i] 表示这边条的危险系数。
     * 现在我们想找到一条路径从 0~n，使得这条路径上最大危险系数最小。（注意：不是路径和最小，而是路径上的最大值最小）。
     *
     * 输入：n = 2, m = 2, x[] = [0, 1], y[] = [1,2], w[]=[1,2]
     * 输出：2
     * 解释：注意两条边的表示：边 1：(x[0]=0, y[0]=1, w[0]=1), 边 2：（x[1] = 1, y[1] =2, w[1]=2)。所以形成了下图：
     *
     * 例子2：
     * 输入：n=5,m=6, x[]={0,0,1,2,2,3,}, y[]={1,2,2,3,4,4,}, w[]={1,6,2,1,5,2,};
     * 输入：2
     */
}
