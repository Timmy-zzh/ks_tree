package com.timmy.testlib;

public class Test {

    public static void main(String[] args) {
        Test demo = new Test();
//        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = {0, 1, 0, 3, 2, 3};
        int res = demo.lengthOfLIS(nums);
        System.out.println("res:" + res);
    }

    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = 1;
        int res = 1;

        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        PrintUtils.print(dp);
        return res;
    }
}
