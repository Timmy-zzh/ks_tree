package com.timmy._review._06graph;

public class _01并查集 {

    public static void main(String[] args) {
        _01Union union = new _01Union(10);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + " 的祖先节点为：" + union.find(i));
        }
        union.union(1, 2);
        union.union(2, 3);
        union.union(4, 5);
        union.union(6, 5);
        System.out.println("-------");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " 的祖先节点为：" + union.find(i));
        }
        System.out.println("区域个数：" + union.getCount());
    }
}
