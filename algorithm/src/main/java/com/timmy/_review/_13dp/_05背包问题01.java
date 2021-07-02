package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

import java.util.Random;

public class _05背包问题01 {

    public static void main(String[] args) {
        _05背包问题01 demo = new _05背包问题01();

        int N = 5;
        int S = 10;
        int[] v = new int[N];
        int[] w = new int[N];

        Random random = new Random();
        for (int i = 0; i < N; i++) {
            v[i] = random.nextInt(6) + 1;
            w[i] = random.nextInt(10);
        }
        System.out.println("物品数量 N:" + N + " ，背包大小 S:" + S);
        System.out.println("每个物品的大小");
        PrintUtils.print(v);
        System.out.println("每个物品的价值");
        PrintUtils.print(w);

        int res = demo.slove(N, S, v, w);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入N个物品，物品的大小和价值对应两个数组v[],w[],现在有个背包，背包的大小为S，
     * --现在要将物品存放到背包中（物品大小不能超过背包体积），且物品的总价值最高
     * 2。解题思路
     * 2。1。最后一步
     * -求背包中物品的总价值最高，
     * -因为要求的是S大小的背包中物品的总价值，所以定义一个数组大小为S+1 - [0,S],
     * -数组的下标值表示背包中的物品总量，数组的元素值表示背包中物品大小对应的总价值
     * 2。2。递推方程式
     * -int[]dp 表示背包中已有的物品的总价值，dp[0]=0
     * -数组的下标为背包的大小[0,S],数组的元素值为背包中物品的总价值
     * -便利所有的物品，可以拿到物品的大小和价值
     * --把物品放到背包中，背包中的物品的大小和总价值发生变化，
     */
    public int slove(int N, int S, int v[], int w[]) {
        int[] dp = new int[S + 1];
        dp[0] = 0;
        int ans = 0;

        for (int i = 0; i < N; i++) {       //不断遍历每个物品
            //将单个物品存放到背包中
            for (int space = S; space >= v[i]; space--) {
                int oldSpace = space - v[i];      //之前背包已经存在的物品
                int newSpace = space;           //添加i物品和背包的大小
                dp[newSpace] = Math.max(dp[newSpace], dp[oldSpace] + w[i]);

                System.out.println("newSpace:" + dp[newSpace]);
                ans = Math.max(ans, dp[newSpace]);
            }
        }

        return ans;
    }

    /**
     * （0/1 背包）有 N 件物品和一个容量是 V 的背包，每件物品只能使用一次。
     * 第 i 件物品的体积是 vi，价值是 wi。求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。
     */
}
