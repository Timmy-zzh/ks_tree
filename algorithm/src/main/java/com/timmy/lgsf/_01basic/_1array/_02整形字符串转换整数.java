package com.timmy.lgsf._01basic._1array;

public class _02整形字符串转换整数 {

    public static void main(String[] args) {
        _02整形字符串转换整数 demo = new _02整形字符串转换整数();
//        int atoi = demo.myAtoi("3456");
        int result = demo.hammingWeight(-9);
        System.out.println("result:" + result);
    }

    /**
     * 实现简易版atoi函数，使其能够将整形字符串转换成整数
     * <p>
     * 示例：
     * 输入："42"
     * 输出： 42
     * 不考虑负数情况：
     */

    /**
     * 解题思路：
     * 1。字符串转成字符数组
     * 2。遍历字符数组，反序遍历，遍历到的字符转成数字，
     * 3。原来的值*10 + 新数字
     */
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        long result = 0;
        for (int i = 0; i < length; i++) {
            int num = chars[i] - '0';
            result = result * 10 + num;
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return (int) result;
    }

    /**
     * 位1的个数：
     * 输入是一个无符号整数，返回其二进制表达式中数字位数为"1"的个数
     * <p>
     * 示例：
     * 输入： 00000000000000000001011
     * 输出：3
     * <p>
     * 解题思路：
     * 1。先将整数转成对应的二进制表达式字符串
     * 2。再遍历字符数组，判断是否有"1"字符，存在则个数加1
     */
    public int hammingWeight(int n) {
        String str = Integer.toBinaryString(n);
        System.out.println("str:" + str);
        char[] chars = str.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                result++;
            }
        }
        return result;
    }
}
