package com.timmy.lgsf._03tree._7segment_tree;

import com.timmy.common.PrintUtils;

import java.util.Arrays;

public class SegmentTree {


    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 4, 6};
        SegmentTree demo = new SegmentTree(nums);
        int query = demo.query(4, 4);
        System.out.println("query:" + query);

        demo.update(1,3);
        System.out.println(demo.toString());
    }

    private int n;  //线段树数组大小
    private int[] values;//线段树数组保存
    private int[] nums; //原始数组

    public SegmentTree(int[] nums) {
        this.n = nums.length * 2;
        this.nums = nums;
        this.values = new int[n];
        build(nums, 0, 0, nums.length - 1);
        PrintUtils.print(values);
    }

    @Override
    public String toString() {
        return "SegmentTree{" +
                "n=" + n +
                ", values=" + Arrays.toString(values) +
                ", nums=" + Arrays.toString(nums) +
                '}';
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


    /**
     * 1。理解题意
     * -获取原始数组 int[] nums = {0, 1, 3, 4, 6}中，在区间[left,right]范围内的元素之和
     * 2。解题思路
     * -构造线段树，因为线段树中的节点值，是原始数组区间范围内的元素之和
     * --所以可以通过递归调用，并计算区间番位内线段树的值
     * 3。边界与细节问题
     * -线段树根节点的值，表示的是原始数组整个范围的元素和。
     * -然后不断往下递归，如果范围相同，则直接返回当前节点的值
     * -如果超过搜索范围，返回0值，如果出现重叠范围，取交集
     * -最后查询结果，是左子树和右子树检索值之和
     *
     * @param pos    线段树节点下标值
     * @param left   线段树当前节点表示的区间范围 --左
     * @param right  。。 -右
     * @param qleft  -检索区域-左
     * @param qright -检索区域-右
     * @return
     */
    private int query(int pos, int left, int right, int qleft, int qright) {
        //1。超过检索区域
        if (qright < left || right < qleft) {
            return 0;
        }
        //2。检索区域覆盖线段树区域，直接返回线段树的范围值
        if (qleft <= left && right <= qright) {
            return values[pos];
        }
        //继续往下检索
        int mid = (left + right) / 2;
        int leftQ = query(2 * pos + 1, left, mid, qleft, qright);
        int rightQ = query(2 * pos + 2, mid + 1, right, qleft, qright);
        return leftQ + rightQ;
    }

    public int query(int qleft, int qright) {
        return query(0, 0, nums.length - 1, qleft, qright);
    }

    /**
     * 1.理解题意
     * -更新原始数组中某个元素的值
     * --原始数组中的某个元素变化了，那整个线段树的值都需要改变
     * 2。解题思路
     * -递归实现
     * --递，在根节点开始分支，进入index存在的区间范围，遍历到叶子节点时，更新线段树上的值
     * --归，阶段则，跟新上层节点的值，上层节点的值是左右子节点的和
     *
     * @param pos
     * @param left
     * @param right
     * @param index
     * @param newValue
     */
    private void update(int pos, int left, int right, int index, int newValue) {
        if (left == right) {
            if (left == index) {
                values[pos] = newValue;
            }
            return;
        }
        //左右子树查找，更新
        int mid = (left + right) / 2;
        update(2 * pos + 1, left, mid, index, newValue);
        update(2 * pos + 2, mid + 1, right, index, newValue);
        values[pos] = values[2 * pos + 1] + values[2 * pos + 2];
    }

    public void update(int index, int newValue) {
        update(0, 0, nums.length - 1, index, newValue);
    }
}
