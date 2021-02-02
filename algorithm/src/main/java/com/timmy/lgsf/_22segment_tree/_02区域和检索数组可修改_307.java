package com.timmy.lgsf._22segment_tree;

public class _02区域和检索数组可修改_307 {

    public static void main(String[] args) {

    }

    /**
     * 线段树实现，线段树数组中保存着区间范围内的总和
     */
    static class NumArray {

        private final int n;
        private final int[] nums;
        private final int[] values;

        public NumArray(int[] nums) {
            this.n = nums.length * 2;
            this.nums = nums;
            this.values = new int[n];
            build(0, 0, nums.length - 1);
        }

        /**
         * @param pos   线段树节点在数组中的下标
         * @param left  当前节点在区间范围的表示
         * @param right
         */
        private void build(int pos, int left, int right) {
            if (left == right) {
                values[pos] = nums[left];
                return;
            }
            int mid = (left + right) / 2;
            build(2 * pos + 1, left, mid);
            build(2 * pos + 2, mid + 1, right);
            values[pos] = values[2 * pos + 1] + values[2 * pos + 2];
        }

        //跟新某个位置的值
        public void update(int index, int val) {
            update(0, 0, nums.length - 1, index, val);
        }

        private void update(int pos, int left, int right, int index, int newVal) {
            if (left == right) {   //叶子节点
                if (left == index) {
                    values[pos] = newVal;
                    nums[index] = newVal;
                }
                return;
            }
            int mid = (left + right) / 2;
            build(2 * pos + 1, left, mid);
            build(2 * pos + 2, mid + 1, right);
            values[pos] = values[2 * pos + 1] + values[2 * pos + 2];
        }

        // 求在在原始数组中 区间范围的总和
        public int sumRange(int left, int right) {
            return sumRange(0, 0, nums.length - 1, left, right);
        }

        /**
         * @param pos    线段树数组的下标
         * @param left   线段树当前节点区间范围 -左
         * @param right  --右
         * @param qleft  检索区间-左
         * @param qright --右
         * @return
         */
        private int sumRange(int pos, int left, int right, int qleft, int qright) {
            //不在检索区间范围内
            if (qright < left || qleft > right) {
                return 0;
            }
            // 检索区间覆盖 线段树区间
            if (qleft <= left && qright >= right) {
                return values[pos];
            }
            int mid = (left + right) / 2;
            int leftSum = sumRange(2 * pos + 1, left, mid, qleft, qright);
            int rightSum = sumRange(2 * pos + 2, mid + 1, right, qleft, qright);
            return leftSum + rightSum;
        }
    }

    /**
     * 给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
     *
     * 实现 NumArray 类：
     *
     * NumArray(int[] nums) 用整数数组 nums 初始化对象
     * void update(int index, int val) 将 nums[index] 的值更新为 val
     * int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + nums[left + 1], ..., nums[right]）
     *  
     *
     * 示例：
     *
     * 输入：
     * ["NumArray", "sumRange", "update", "sumRange"]
     * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
     * 输出：
     * [null, 9, null, 8]
     *
     * 解释：
     * NumArray numArray = new NumArray([1, 3, 5]);
     * numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
     * numArray.update(1, 2);   // nums = [1,2,5]
     * numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}
