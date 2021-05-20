package com.timmy._review._06graph;

/**
 * 1。并查集模版代码实现：
 * 1.1.并查集这种数据结构的本质是：将一些不交集的元素进行合并，和查询操作
 * -主要操作：查询find，合并union
 * 2.
 * -刚开始时所有元素的帮主都是自己
 * -将两个元素合并时，两个元素的帮主变为同一个元素（父节点）
 * 2。1。查询操作：查询该元素的父节点，不断递归，直到该元素的父节点为自己
 * 2。2。合并操作：找到两个元素的父节点，将其中一个元素的父节点指向两外一个元素的父节点
 * 2。3。求某一个元素的父节点
 * 2。4。求一共有多少个集合，刚开始有n个元素，存在n个集合，每次合并后集合个数减少
 */
public class _01Union {
    //集合个数
    int count;
    //某个节点的父节点
    int[] parent;

    public _01Union(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * 合并x，y元素
     * -先获取x，y的祖先节点
     * -判断x，y的祖先节点是否相同
     * -不相同则合并，其中x节点的祖先节点指向y的祖先节点
     */
    public void union(int x, int y) {
        int xP = find(x);
        int yP = find(y);
        if (xP != yP) {
            parent[xP] = yP;
            count--;
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
}
