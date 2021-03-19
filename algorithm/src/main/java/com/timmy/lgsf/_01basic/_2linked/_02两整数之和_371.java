package com.timmy.lgsf._01basic._2linked;

public class _02两整数之和_371 {

    public static void main(String[] args) {
        _02两整数之和_371 demo = new _02两整数之和_371();
        int result = demo.getSum(3, -2);
        System.out.println("result:" + result);
    }

    /**
     * 371. 两整数之和
     * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
     * <p>
     * 示例 1:
     * 输入: a = 1, b = 2
     * 输出: 3
     * <p>
     * 示例 2:
     * 输入: a = -2, b = 3
     * 输出: 1
     * <p>
     * 解题思路：
     * 1。将整数转成二进制
     * 2。再将二进制字符串进行相加
     * 3。最后将二进制字符串转成数字
     *
     * TODO: 有问题不能计算负数
     * --使用位移操作
     */
    public int getSum(int a, int b) {
//        String aBinary = Integer.toBinaryString(a);
//        String bBinary = Integer.toBinaryString(b);
//        String sumBin = addBinary(aBinary, bBinary);
//        System.out.println("sumBin:" + sumBin);
//        int sum = Integer.parseInt(sumBin, 2);
//        return sum;

        while (b!=0){
            int temp = a^b;
            b = (a&b)<<1;
            a = temp;
        }
        return a;
    }

}
