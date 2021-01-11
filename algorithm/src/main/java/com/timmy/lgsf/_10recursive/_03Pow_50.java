package com.timmy.lgsf._10recursive;

public class _03Pow_50 {

    public static void main(String[] args) {
        _03Pow_50 demo = new _03Pow_50();
//        double result = demo.myPow(2, 10);
        double result = demo.myPow(2, 11);
//        double result = demo.myPow(2.0, -2);
        System.out.println("result:" + result);
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * <p>
     * 示例 1:
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * <p>
     * 示例 2:
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * <p>
     * 示例 3:
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     */

    /**
     * x的n次方
     * 解法：1。n次for循环
     */
    private double myPow_v1(double x, int n) {
        double result = 1;
        boolean sign = n >= 0;
        n = sign ? n : n * -1;
        for (int i = n; i > 0; i--) {
            result = result * x;
        }
        return sign ? result : 1 / result;
    }

    /**
     * 递归算法
     * 时间复杂度为n
     */
    private double myPow_v2(double x, int n) {
        boolean sign = n >= 0;
        n = sign ? n : n * -1;
        double result = powx(x, n);
        return sign ? result : 1 / result;
    }

    private double powx(double x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * powx(x, n - 1);
    }


    /**
     * 递归进阶：
     * x*x = x的平方
     * x*x*x*x = x平方的平方
     * 。。。。
     */
    private double myPow_v3(double x, int n) {
        boolean sign = n >= 0;
        n = sign ? n : n * -1;
        double result = powy(x, n);
        return sign ? result : 1 / result;
    }

    private double powy(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            return powy(x, n / 2) * powy(x, n / 2);
        }
        return x * powy(x, n / 2) * powy(x, n / 2);
    }


    /**
     * 时间复杂度:log2
     */
    private double myPow(double x, int n) {
        boolean sign = n >= 0;
        n = sign ? n : n * -1;
        double result = powz(x, n);
        return sign ? result : 1 / result;
    }

    private double powz(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double t = powz(x, n / 2);
        if (n % 2 == 0) {
            return t * t;
        }
        return x * t * t;
    }


}
