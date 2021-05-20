package com.timmy._review._06graph;

import com.timmy.common.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

public class _02最小生成树 {

    public static void main(String[] args) {
        _02最小生成树 demo = new _02最小生成树();
        int[][] conn = {{1, 2, 37}, {2, 1, 17}, {1, 2, 68}};
        long res = demo.kruskal(2, conn);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -有n个点集组成的图，使用二维数据表示两个节点相连和权重，求将图中点生成最小生成树的权重最小
     * 2。解题思路
     * -题目要求生成最小生成树，首先要保证所有的节点能够联通，然后在联通中选择连线权重值最小的的连线
     * 2。1。采用并查集合并和贪心算法
     * -将所有连线根据权重值进行升序排序，然后选择权重值最小的两个点进行合并，合并和两个点的祖先节点相同
     * -后面还有不同权重值的连线就不会被添加进来
     */
    public long kruskal(int n, int[][] conn) {
        init(n);

        //排序
        Arrays.sort(conn, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[2] - t1[2];
            }
        });
        PrintUtils.print(conn);

        for (int[] ints : conn) {
            union(ints[0], ints[1], ints[2]);
        }
        return cost;
    }

    //集合个数
    int count;
    //某个节点的父节点
    int[] parent;
    int cost;

    public void init(int n) {
        this.count = n;
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    /**
     * 合并x，y元素
     * -先获取x，y的祖先节点
     * -判断x，y的祖先节点是否相同
     * -不相同则合并，其中x节点的祖先节点指向y的祖先节点
     */
    public void union(int x, int y, int weight) {
        int xP = find(x);
        int yP = find(y);
        if (xP != yP) {
            parent[xP] = yP;
            count--;
            cost += weight;
        }
    }

    /**
     * 找到x元素的祖先节点
     */
    public int find(int x) {
        while (x != parent[x]) {
            //x指向 x的父节点，直到x的父节点为自己本身¬
            x = find(parent[x]);
        }
        return x;
    }

    public int getCount() {
        return count;
    }

    /**
     * 【题目】给定一个图的点集，边集和权重，返回构建最小生成树的代价。
     *
     * 输入：N = 2， conn = [[1, 2, 37], [2, 1, 17], [1, 2, 68]]
     * 输出：17
     * 解释：图中只有两个点 [1, 2]，当然是选择最小连接 [2, 1, 17]
     */
}
