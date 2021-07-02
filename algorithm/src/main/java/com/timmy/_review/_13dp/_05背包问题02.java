package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

import java.util.Random;

/**
 * 谜之之题
 */
public class _05背包问题02 {

    public static void main(String[] args) {
        _05背包问题02 demo = new _05背包问题02();

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
     *
     */
    public int slove(int N, int S, int v[], int w[]) {
        int[] dp = new int[S + 1];
        dp[0] = 0;
        int ans = 0;

        for (int i = 0; i < N; i++) {       //不断遍历每个物品
            //将单个物品存放到背包中
            for (int space = v[i]; space <= S; space++) {
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
     * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
     * 第 i 种物品的体积是 vi，价值是 wi。求解将哪些物品装入背包，
     * 可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。
     */
}
