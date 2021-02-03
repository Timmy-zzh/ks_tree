package com.timmy.lgsf._03tree._22segment_tree;


import com.timmy.common.PrintUtils;

public class SegTree {

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 4, 6};
        SegTree demo = new SegTree(nums);
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

        // //左右子节点，保存的值--是原始数组的下标
        int leftValue = values[2 * pos];
        int rightValue = values[2 * pos + 1];
        //节点值保存的是 原始数组范围内，数组元素最小的下标
        values[pos] = nums[leftValue] > nums[rightValue] ? rightValue : leftValue;
    }



    /**
     * 1。理解题意
     * -求解在原始数组nums中，范围[qleft,qright]中元素最小的下标
     * 2.解题思路
     * -构建线段树，在线段树节点中，保存在区间范围内nums数组元素最小值的下标
     * -然后根据检索范围求取该取键内最小值的下标
     *
     * @param pos
     * @param left
     * @param right
     * @param qleft
     * @param qright
     * @return
     */
    private int query(int pos, int left, int right, int qleft, int qright) {
        if (left > right) {
            return -1;
        }
        if (qleft == left && right == qright) {//区间刚好覆盖
            return values[pos];
        }
        int mid = (qleft + qright) / 2;
        int leftQ = query(2 * pos, left, Math.min(mid, right), qleft, mid);
        int rightQ = query(2 * pos + 1, Math.max(mid + 1, left), right, mid + 1, qright);
        return leftQ + rightQ;
    }

    public int query(int qleft, int qright) {
        return query(1, 0, nums.length - 1, qleft, qright);
    }
}
