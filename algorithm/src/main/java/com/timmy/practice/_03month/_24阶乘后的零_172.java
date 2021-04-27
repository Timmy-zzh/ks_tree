package com.timmy.practice._03month;

public class _24阶乘后的零_172 {

    public static void main(String[] args) {
        _24阶乘后的零_172 demo = new _24阶乘后的零_172();
        int res = demo.trailingZeroes(25);
        System.out.println("res:" + res);
    }

    /**
     * 1。解题思路
     * -输入一个整数n，求n的阶层结果中含有0的个数
     * 2。解题思路
     * -能够生成0的结果，是因为相乘的因子有2*5，而每次存在5的因子的时候，一定前面存在2的因子
     * -所以有多少个0的个数，只要判断经历过多少次5的倍数
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

    /**
     * 给定一个整数 n，返回 n! 结果尾数中零的数量。
     *
     * 示例 1:
     * 输入: 3
     * 输出: 0
     * 解释: 3! = 6, 尾数中没有零。
     *
     * 示例 2:
     * 输入: 5
     * 输出: 1
     * 解释: 5! = 120, 尾数中有 1 个零.
     * 说明: 你算法的时间复杂度应为 O(log n) 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}
