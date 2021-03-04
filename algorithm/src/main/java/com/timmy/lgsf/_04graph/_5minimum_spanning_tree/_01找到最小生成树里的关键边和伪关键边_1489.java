package com.timmy.lgsf._04graph._5minimum_spanning_tree;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _01找到最小生成树里的关键边和伪关键边_1489 {

    public static void main(String[] args) {
        _01找到最小生成树里的关键边和伪关键边_1489 demo = new _01找到最小生成树里的关键边和伪关键边_1489();
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
    }

    /**
     * 1.理解题意
     * -输入n个点的带权无向联通图，二维数组 edges[i] = [fromi, toi, weighti}表示节点from到节点to是连通的，且权重为weight
     * -求最小生成树，最小生成树是权值和最小的一个子集，连接所有节点没有环，不止一个， 我们要从所有的最小生成树中找出关键边和伪关键边
     * -如何找关键边和伪关键边呢？？
     * 2。解题思路：克鲁斯卡尔(Kruskal) = 贪心算法 + 并查集
     * -2。1。先求最小生成树，得到其权值和，从权值最小的边开始合并
     * -2。2。求关键边-反证法 ：如果没有该边，则生成的最小生成树权值和增加
     * -2。3。伪关键边：有该边，最小生成树的权值和不变、
     * 3。边界和细节问题
     * -先通过贪心算法，将权值最小的边保存在一个集合中，并且使用并查集保证每次这条边两端的节点不在同一集合中才合并
     * -边的集合按照每条边权值大小进行升序排序，节点按照并查集合并，这样可以保证最后遍历到所有的节点，且节点的连接边权值和最小
     *
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        //注意要clone，直接相等只会将指针指向同样的地址
        sourEdges = edges.clone();
        PrintUtils.print(edges);
        System.out.println("排序后");
        //1.根据每条边的权重值排序
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[2] - t1[2];
            }
        });
        PrintUtils.print(edges);
        //2.生成最小生成树，并返回权重和
        int mstWeightSum = kruskal(n, edges, -1, false);
        System.out.println("mstWeightSum：" + mstWeightSum);
        //求关键边和伪关键边
        List<Integer> keyEdges = new ArrayList<>();
        List<Integer> osedEeges = new ArrayList<>();

        /**
         * 3.遍历所有的边
         * -如果这条边不参与生成最小生成树，并且生成树的总权值和增大，则为关键边
         */
        for (int i = 0; i < sourEdges.length; i++) {
            if (kruskal(n, edges, i, true) > mstWeightSum) {
                System.out.println("add:" + i);
                keyEdges.add(i);
            } else if (kruskal(n, edges, i, false) == mstWeightSum) {
                System.out.println("osedEeges add:" + i);
                osedEeges.add(i);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(keyEdges);
        res.add(osedEeges);
        System.out.println("=====");
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
        return res;
    }

    /**
     * 遍历排序好的边，并将节点进行合并
     *
     * @param n
     * @param edges
     * @param index 当前遍历到的原始图的第几条边
     * @param isKey 判断当前边是否是关键边
     * @return
     */
    private int kruskal(int n, int[][] edges, int index, boolean isKey) {
        int res = 0;
        //最小生成树的边树，必须要=n-1
        int edgeCount = 0;
        init(n);
        //如果是伪关键边的情况，则需要现将变的信息添加到集合中
        if (!isKey && index >= 0) {
            int[] edge = sourEdges[index];
            if (union(edge[0], edge[1])) {
                res += edge[2];
                edgeCount++;
            }
        }

        for (int i = 0; i < edges.length && edgeCount < n - 1; i++) {
            //如果是关键边情况，则不将这条边加入到 最小生成树中
            if (isKey && sourEdges[index] == edges[i]) {
                continue;
            }
            //[2, 3, 2]
            int[] edge = edges[i];
            //合并成功，返回最小生成树权值和
            if (union(edge[0], edge[1])) {
//                System.out.println("合并节点：" + edge[0] + " - " + edge[1]);
                res += edge[2];
                edgeCount++;
            }
        }
        if (edgeCount == n - 1) {
            return res;
        }
        //存在一种特殊的边，连接两个连通图，没有这条边组成不了最小生成树，所以一定要加入到关键边中去
        return Integer.MAX_VALUE;
    }

    //并查集
    //原始未排序的边
    int[][] sourEdges;
    /**
     * 保存每个节点指向的跟节点，默认跟节点是自己
     * 数量是节点的个数
     */
    int[] parent;

    public void init(int n) {
        if (parent == null) {
            parent = new int[n];
        }
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * 节点合并，判断根节点是否相同
     *
     * @param x
     * @param y
     * @return
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            //合并
            parent[rootX] = rootY;
            return true;
        }
        return false;
    }

    /**
     * 找到节点i的根节点，根节点的根节点是自己
     *
     * @param i
     * @return
     */
    private int find(int i) {
        while (parent[i] != i) {
            i = parent[i];
            parent[i] = parent[parent[i]];
        }
        return parent[i];
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
