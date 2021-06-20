package com.timmy._review._12dfs_bfs;

import com.timmy.common.PrintUtils;

import java.util.HashMap;

public class _04零钱兑换_322 {

    public static void main(String[] args) {
        _04零钱兑换_322 demo = new _04零钱兑换_322();
        int[] coins = {1, 2, 5};
//        int[] coins = {2};
        PrintUtils.print(coins);
        int res = demo.coinChange(coins, 100);
        System.out.println("res:" + res);
    }

    /**
     * dp算法
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        return 0;
    }

    /**
     * 1.理解题意
     * -输入一个数组表示硬币的面额 和目标总金额amount，每种面额的硬币数量是无限的，求将总金额使用硬币进行兑换可以使用的最少硬币个数
     * --如果兑换不了的话，返回-1，可以兑换返回硬币数量
     * 2。解题思路：深度优先算法 + 搜索记忆法
     * -不断从硬币数组中取出不同面额的硬币，然后相对应的总结额进行减少，当总金额减少为0的时候说明刚好兑换成功，
     * 使用递归算法进行实现：
     * -入参硬币面额数组，和当前需要兑换的金额，返回值为兑换当前金额所需的硬币个数
     * -终止条件为：当前兑换的总金额小于等于0，等于0返回0，小于0返回int最大值 （根据返回值可以知道当前硬币是否可以进行兑换）
     * -单层递归的逻辑为从硬币数组中取出不同面额的硬币对当前总金额进行兑换减少
     * 搜索记忆法：
     * -不同总金额的面值兑换的硬币数量，使用hashMap进行保存，下次循环有同样面值的直接取value值返回
     */
    private static int INF = Integer.MAX_VALUE >> 2;
    //保存不同面额，对应的兑换硬币数量
    private HashMap<Integer, Integer> map = new HashMap<>();

    public int coinChange_v1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        for (int coin : coins) {
            map.put(coin, 1);
        }
        int res = dfs(coins, amount);
        return res == INF ? -1 : res;
    }


    private int dfs(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return INF;
        }
        if (map.containsKey(amount)) {
            return map.get(amount);
        }

        int ans = INF;
        for (int i = 0; i < coins.length; i++) {
            //在当前金额基础上，减少不同面额的硬币
            int val = dfs(coins, amount - coins[i]) + 1;
            if (val != INF) {
                ans = Math.min(ans, val);
            }
        }
        map.put(amount, ans);
        return ans;
    }

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     *
     * 示例 1：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     *
     * 示例 2：
     * 输入：coins = [2], amount = 3
     * 输出：-1
     *
     * 示例 3：
     * 输入：coins = [1], amount = 0
     * 输出：0
     *
     * 示例 4：
     * 输入：coins = [1], amount = 1
     * 输出：1
     *
     * 示例 5：
     * 输入：coins = [1], amount = 2
     * 输出：2
     *
     * 提示：
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 231 - 1
     * 0 <= amount <= 104
     * 链接：https://leetcode-cn.com/problems/coin-change
     */
}
