package com.timmy._review._13dp;

import com.timmy.common.PrintUtils;

public class _04分割等和子集_416 {

    public static void main(String[] args) {
        _04分割等和子集_416 demo = new _04分割等和子集_416();
        int[] nums = {1, 5, 11, 5};
        PrintUtils.print(nums);
        boolean res = demo.canPartition(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，要求将数组中的所有元素分割成两个子集，每个子集的元素和相等
     * 2。解题思路：动态规划解法
     * 2。1。最后一步
     * -先求整个数组的元素和，如果和为偶数才有可能分割成两个子集，如果元素和为奇数，不能分割子集，直接返回false
     * -假设数组的元素和为 2V，则单个子集的元素和为V，则我们只需要寻找一个子集，其元素和为V
     * -所以我们可以求boolean[] dp的数组，个数为V+1；数组值为当前下标（元素和）时是否存在该子集
     * 2。2。推导方程式
     * -dp[0] = true;
     * -dp[V] = dp[V-num] (num为数组的元素)
     * 2。3。初始化和边界值
     * -
     * 2。4。执行顺序
     * -从大到小
     */
    public boolean canPartition(int[] nums) {
        int N = nums == null ? 0 : nums.length;
        if (N == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }
        if ((sum & 0X01) == 1) {    //奇数
            return false;
        }
        int V = sum >> 1;
        boolean[] dp = new boolean[V + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int node = V; node - num >= 0; node--) {
                if (dp[node - num]) {
                    dp[node] = true;
                }
            }
        }
        PrintUtils.print(dp);
        return dp[V];
    }

    /**
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 示例 1：
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     *
     * 示例 2：
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     *
     * 提示：
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
     */
}
