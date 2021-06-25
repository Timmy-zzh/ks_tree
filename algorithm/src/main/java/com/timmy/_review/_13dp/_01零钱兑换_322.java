package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

public class _01零钱兑换_322 {

    public static void main(String[] args) {
        _01零钱兑换_322 demo = new _01零钱兑换_322();
        int[] coins = {1, 2, 5};
//        int[] coins = {2};
        PrintUtils.print(coins);
        int res = demo.coinChange(coins, 100);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组表示硬币的面额，和总金额amount，现在将总金额进行硬币兑换，求兑换所需的最少硬币个数
     * 2。解题思路：dp算法
     * 2。1。最后一步：
     * -11总金额的兑换硬币个数 = 6金额的兑换硬币个数 + 1（面额5）
     * -9金额的兑换硬币个数 = 7金额兑换硬币个数 + 1（面额2）
     * 2。2。子问题
     * -11总金额的兑换硬币个数，等于（11-1）（11-2）（11-5）更小规模的金额兑换硬币个数+1
     * 2。3。方程式
     * -dp[i] -- i表示需要兑换的金额，dp[i]的元素值为兑换金额i所需要的最少硬币个数
     * -传递关系： dp[i] = dp[i-coin] + 1
     * --其中coin的值为不同面额的硬币，上面的传递关系也可以转换为 dp[i+coin] = dp[i]+1
     * 2。4。初始值和边界条件
     * -当金额为0的时候需要0个硬币数量
     * 2。5。计算顺序
     * -从0金额开始往后求解
     * 3.总结
     * -最后一步
     * -子问题，原问题分解子问题
     * -传递关系，递推方程式
     * -初始值和边界条件
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int INF = Integer.MAX_VALUE >> 2;
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i] = INF;
        }
        dp[0] = 0;

        for (int i = 0; i < amount; i++) {
            for (int coin : coins) {
                if (i + coin <= amount) {
                    dp[i + coin] = Math.min(dp[i + coin], dp[i] + 1);
                }
            }
        }
        PrintUtils.print(dp);
        return dp[amount] == INF ? -1 : dp[amount];
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
