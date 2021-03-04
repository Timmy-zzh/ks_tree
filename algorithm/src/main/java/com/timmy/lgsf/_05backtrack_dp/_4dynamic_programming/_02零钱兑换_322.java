package com.timmy.lgsf._05backtrack_dp._4dynamic_programming;

import com.timmy.common.PrintUtils;

import java.util.Arrays;

public class _02零钱兑换_322 {

    public static void main(String[] args) {
        _02零钱兑换_322 demo = new _02零钱兑换_322();

    }

    /**
     * 1.
     * coins = [1, 2, 5], amount = 11
     * 2.解题思路：动态规划
     * -状态转移数组：dp[m] = dp[m-coin] + 1
     * --意思是 总金额11的硬币面值个数= 总金额6（11-5）的面值个数 + 1张
     * -因为硬币面额有三种情况，所以状态转移存在三种情况：
     * --dp(11) = dp(6=11-5)+1
     * --dp(11) = dp(9=11-2)+1
     * --dp(11) = dp(10=11-1)+1
     * 取其中的最小值，其中dp(0) = 0
     * 3.边界和细节问题
     * -总金额，我们从面值1开始遍历，
     * --总金额为1的情况，只需要硬币面值1既可以，dp(1) = dp(0)+1 = 1
     * ---...
     * 当总金额为6的情况，dp(6） = dp(1=6-5)+1
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int m = 0; m <= amount; m++) {
            for (int c = 0; c < coins.length; c++) {
                if (m >= coins[c]) {
                    dp[m] = Math.min(dp[m], dp[m - coins[c]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 1.理解题意
     * -输入不同面额的硬币coins数组和一个总金额，每种硬币的数量无限，
     * --要求根据给出的硬币面额凑成总金额所需最少硬币个数
     * 2.解题思路：暴力解法
     * -只要总金额>0,且在硬币组合中存在比总金额小的面额，则总金额的值减少面额值，循环，
     * --直到总金额等于0，兑换成功
     * --总金额不等于0，但是硬币数组中没有匹配的面额，则兑换失败
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange_v1(int[] coins, int amount) {
        int res = 0;
        //降序
        Arrays.sort(coins);
        for (int i = 0, j = coins.length - 1; i < coins.length / 2; i++, j--) {
            int temp = coins[i];
            coins[i] = coins[j];
            coins[j] = temp;
        }
        PrintUtils.print(coins);
        while (amount > 0) {
            int i = 0;
            for (; i < coins.length; i++) {
                if (amount >= coins[i]) {
                    System.out.println("amount:" + amount);
                    amount = amount - coins[i];
                    res++;
                    break;
                }
            }
            if (i == coins.length) {
                break;
            }
        }

        if (amount == 0) {
            return res;
        }
        return -1;
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
     *
     * 链接：https://leetcode-cn.com/problems/coin-change
     */
}
