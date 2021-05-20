package com.timmy._review._06graph;

import com.timmy.common.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

public class _04上网的最小费用 {

    public static void main(String[] args) {
        _04上网的最小费用 demo = new _04上网的最小费用();
        int[] cost = {1, 2, 3};
        int[][] es = {{1, 2, 100}, {2, 3, 3}};
        int res = demo.minCostToSupplyWater(3, cost, es);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -园区有n栋大楼，目的要让所有的大楼都通上网；大楼可以自己买路由器上网，费用为cost
     * -也可以从别的大楼拉一根网络上网，费用是edges 两个节点的权重为费用，要求让所有大楼都能上网的最小花费多少？
     * 2。解题思路
     * 虚拟节点 + 最小生成树（并查集+权重最小）
     * -如果没有自己买路由器上网，只能通过大楼之间拉线，就是最小生成树的题目了
     * -现在可以自己买路由器上网，可以假设存在一个0号节点，该节点可以连接到所有的大楼，并且费用为cost
     * -所以可以将新增节点0到所有大楼的连接，添加到原先的连线中，然后根据这些连线采用克鲁斯卡尔算法求最小生成树的代价
     * 3。
     */
    public int minCostToSupplyWater(int N, int[] cost, int[][] es) {
        init(N);

        //根据虚拟点0，新建虚拟连线
        int[][] conn = new int[es.length + cost.length][3];
        //再将虚拟点到各个大楼的连线添加
        for (int i = 0; i < cost.length; i++) {
            conn[i][0] = 0;
            conn[i][1] = i + 1;
            conn[i][2] = cost[i];
        }
        //先将已存在的连线复制到新的连接边中
        for (int i = cost.length; i < es.length + cost.length; i++) {
            int index = i - cost.length;
            conn[i][0] = es[index][0];
            conn[i][1] = es[index][1];
            conn[i][2] = es[index][2];
        }
        PrintUtils.print(conn);

        Arrays.sort(conn, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[2] - t2[2];
            }
        });
        PrintUtils.print(conn);

        for (int i = 0; i < conn.length; i++) {
            union(conn[i][0], conn[i][1], conn[i][2]);
        }

        return costPay;
    }

    int count = 0;
    int[] parent;
    int costPay = 0;

    /**
     * 并查集初始化
     */
    private void init(int n) {
        count = n + 1;
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        costPay = 0;
    }

    private void union(int x, int y, int i) {
        int xP = find(x);
        int yP = find(y);
        if (xP != yP) {
            count--;
            parent[xP] = yP;
            costPay += i;
        }
    }

    private int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    /**
     * 【题目】园区里面有很多大楼，编号从 1~N。第 i 大楼可以自己花钱买路由器上网，费用为 cost[i-1]，
     * 也可以从别的大楼拉一根网线来上网，
     * 比如大楼 a 和大楼 b 之间拉网线的费用为 c，表示为一条边 [a, b, c]。输入为每个大楼自己买路由器和拉网线的费用，
     * 请问，让所有大楼都能够上网的最小费用是多少？上网具有联通性，只要与能够上网的大楼连通，即可上网。
     *
     * 输入：N = 3, cost = [1, 2, 3], edges = [[1,2,100], [2,3,3]]
     * 输出：6
     *
     * 解释：最优方案是 1 号大楼买路由器 cost[0] = 1，2 号楼买路由器 cost[1] = 2，
     * 然后和 3 号楼之间可拉一根网线，费用为 3，所以一共花费 6 元。如图（红色部分标记为费用 ）：
     */

}
