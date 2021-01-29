package com.timmy.lgsf._22segment_tree;

import com.timmy.common.PrintUtils;

public class SegmentTree {

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 4, 6};
        SegmentTree demo = new SegmentTree(nums);
    }

    private int n;  //线段树数组大小
    private int[] values;//线段树数组保存

    public SegmentTree(int[] nums) {
        this.n = nums.length * 2;
        this.values = new int[n];
        build(nums, 0, 0, nums.length - 1);
        PrintUtils.print(values);
    }

    /**
     * 1。理解题意
     * -输入一个数组，根据该数组构建线段树
     * -线段树是一颗完全二叉树，可以使用数组保存元素数据，
     * --并且线段树的节点值是原始数组nums的区间值之和，左右孩子的子树表示更小区间的值之和
     * --完全二叉树特点：跟节点在数组中的下标为i，其左孩子的下表为2*i+1, 右孩子数组下标为:2*i+2
     * 2。解题思路
     * -递归实现，因为线段树的节点表示的是原始数组的区间值之和，通过递归不断分治减少区间范围
     * --最后递归到叶子节点，叶子节点的值 就是原始数组中某个元素下标的值，区间为【i，i】
     * --先递归遍历到子节点，再求取上层跟节点的值，该值等于左右孩子节点值之和。
     * 3。边界与细节问题
     * -线段树刚开始，跟节点表示的是[0,n] 的区间，不断递归左右孩子区间，其中左右孩子在数组values中的下标为2*i+1,和2*i+2
     * -区间也不断分治求取 mid = (left+right)/2 ;  左孩子区间-[left,mid]; 右孩子区间[mid+1,right]
     * -遍历到叶子节点结束，左右区间相同
     *
     * @param nums  原始数组
     * @param pos   线段树数组当前递归的下表值
     * @param left  原始数组左区间
     * @param right 右区间
     */
    private void build(int[] nums, int pos, int left, int right) {
        if (left == right) {
            values[pos] = nums[left];
            return;
        }
        //左右孩子便利
        int mid = (left + right) / 2;
        build(nums, 2 * pos + 1, left, mid);
        build(nums, 2 * pos + 2, mid + 1, right);
        //先递  --》 后归，根据左右叶子节点的值，求取上层根节点的值
        values[pos] = values[2 * pos + 1] + values[2 * pos + 2];
    }
}
