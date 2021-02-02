package com.timmy.testlib;


public class SegTree {

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 4, 6};
        SegTree demo = new SegTree(nums);
        int[] target = {3, 1, 5, 4, 2};
        int result = demo.min(target);
        System.out.println("result:" + result);
    }

    public int min(int[] target) {
        int res = target[0];
        for (int i = 1; i < target.length; i++) {
            res += Math.max(target[i] - target[i - 1], 0);
        }
        return res;
    }

    private int n;
    private int[] values;
    private int[] nums;

    public SegTree(int[] nums) {
        this.n = nums.length * 2;
        this.nums = nums;
        this.values = new int[n];
        build(nums, 1, 0, nums.length - 1);
        PrintUtils.print(values);
    }

    // build
    private void build(int[] nums, int pos, int left, int right) {
        if (left == right) {
            values[pos] = left;
            return;
        }
        int mid = (left + right) / 2;
        build(nums, 2 * pos, left, mid);
        build(nums, 2 * pos + 1, mid + 1, right);
        int leftValue = values[2 * pos];
        int rightValue = values[2 * pos + 1];
        values[pos] = nums[leftValue] > nums[rightValue] ? rightValue : leftValue;
    }
}
