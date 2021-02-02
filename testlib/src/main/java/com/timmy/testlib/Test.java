package com.timmy.testlib;

public class Test {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray demo = new NumArray(nums);
        int sum = demo.sumRange(0, 2);
        System.out.println("sum1:" + sum);
        demo.print();

        demo.update(1, 2);
        System.out.println("update");
        demo.print();

        sum = demo.sumRange(0, 2);
        demo.print();
        System.out.println("sum2:" + sum);
    }

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

        public void update(int index, int val) {
            update(0, 0, nums.length - 1, index, val);
        }

        private void update(int pos, int left, int right, int index, int newVal) {
            if (left == right) {
                if (left == index) {
                    values[pos] = newVal;
                    nums[index] = newVal;
                }
                return;
            }
            int mid = (left + right) / 2;
            update(2 * pos + 1, left, mid, index, newVal);
            update(2 * pos + 2, mid + 1, right, index, newVal);
            values[pos] = values[2 * pos + 1] + values[2 * pos + 2];
        }

        public int sumRange(int left, int right) {
            return sumRange(0, 0, nums.length - 1, left, right);
        }

        private int sumRange(int pos, int left, int right, int qleft, int qright) {
            if (qright < left || qleft > right) {
                return 0;
            }
            if (qleft <= left && qright >= right) {
                return values[pos];
            }
            int mid = (left + right) / 2;
            int leftSum = sumRange(2 * pos + 1, left, mid, qleft, qright);
            int rightSum = sumRange(2 * pos + 2, mid + 1, right, qleft, qright);
            return leftSum + rightSum;
        }

        public void print() {
            PrintUtils.print(values);
        }
    }
}
