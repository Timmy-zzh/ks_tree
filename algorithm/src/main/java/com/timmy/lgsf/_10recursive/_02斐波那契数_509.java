package com.timmy.lgsf._10recursive;

public class _02斐波那契数_509 {

    public static void main(String[] args) {
        _02斐波那契数_509 demo = new _02斐波那契数_509();
        int result = demo.fib(3);
        System.out.println("result:" + result);
    }

    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，
     * 后面的每一项数字都是前面两项数字的和。也就是：
     * <p>
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n) 。
     * <p>
     * 示例 1：
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * <p>
     * 示例 2：
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     */

    /**
     * 解题思路：递归算法
     * 递归三要素：
     * 1。入参与返回值
     * 2。终止条件
     * 3。单层递归逻辑实现
     */
    public int fib_v1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 使用while实现
     * 0,1,1,2,3,5,8,
     */
    public int fib(int n) {
        int first = 0;
        int second = 1;
        while (n-- > 0) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return first;
    }
}
