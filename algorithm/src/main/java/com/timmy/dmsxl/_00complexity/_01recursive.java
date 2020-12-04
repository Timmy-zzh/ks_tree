package com.timmy.dmsxl._00complexity;

/**
 * 复杂度分析
 * <p>
 * --时间复杂度
 * --空间复杂度
 * <p>
 * 递归算法的时间复杂度
 *
 * n --》 log n  二分法与树操作
 */
public class _01recursive {

    public static void main(String[] args) {
        _01recursive practice = new _01recursive();
//        int result = practice.fuc1(2, 3);
//        int result = practice.fuc2(2, 3);
//        int result = practice.fuc3(2, 3);
        int result = practice.fuc4(2, 3);
        System.out.println("result:" + result);
    }

    /**
     * 求x的n次方
     * x*x*x*x.... 一共乘以n次
     * 方式一：for循环
     * 时间复杂度：O(n)
     */
    private int fuc1(int x, int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = result * x;
        }
        return result;
    }

    /**
     * 方式二：递归
     * 时间复杂度：O(n)
     * 递归算法的时间复杂度本质上是要看：递归的次数*每次递归中的操作次数
     */
    private int fuc2(int x, int n) {
        if (n == 0) {
            return 1;
        }
        return fuc2(x, n - 1) * x;
    }

    /**
     * 方式三：递归+满二叉树
     * 时间复杂度：O(n)
     * <p>
     * 假设n=8，需要
     * 8个X相乘
     * 4个X*X相乘
     * 2个X*X*X*X相乘
     * 1个X*X*X*X*X*X*X*X相乘
     * <p>
     * 递归过程：
     * n为偶数：fun(x,n/2)
     * n为奇数：fun(x,n/2)*X
     */
    private int fuc3(int x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 1) {
            return fuc3(x, n / 2) * fuc3(x, n / 2) * x;
        }
        return fuc3(x, n / 2) * fuc3(x, n / 2);
    }

    /**
     * 方式四：fun3的改进版
     */
    private int fuc4(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int t = fuc4(x, n / 2);
        if (n % 2 == 1) {
            return t * t * x;
        }
        return t * t;
    }
}
