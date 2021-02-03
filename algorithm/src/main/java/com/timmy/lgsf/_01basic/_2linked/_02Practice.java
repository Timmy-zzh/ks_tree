package com.timmy.lgsf._01basic._2linked;

public class _02Practice {

    public static void main(String[] args) {
        _02Practice demo = new _02Practice();
//        String result = demo.addBinary("1010", "1011");
//        String result = demo.addBinary("11", "1");

        int result = demo.getSum(3, -2);
        System.out.println("result:" + result);
    }

    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * 输入为 非空 字符串且只包含数字 1 和 0。
     * <p>
     * 示例 1:
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * <p>
     * 示例 2:
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     * <p>
     * 解题思路：
     * 1。字符串转成字符数组，逆序遍历字符数组
     * 2。将两个数组的元素-字符，进行相加，并判断是否需要进位
     * 3。使用stringbuild 保存相加后的数值，最后将数值翻转输出即可
     */
    public String addBinary(String a, String b) {
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        int aLen = aArr.length - 1;
        int bLen = bArr.length - 1;
        int offset = 0;
        StringBuilder sb = new StringBuilder();
        //遍历两个字符串，并将两个字符数组的字符相加处理
        while (aLen >= 0 && bLen >= 0) {
            int aNum = aArr[aLen] - '0';
            int bNum = bArr[bLen] - '0';
            int sum = aNum + bNum + offset;
            int value = sum % 2;
            offset = sum / 2;
            sb.append(value);
            aLen--;
            bLen--;
        }
        //对长字符串处理
        if (aLen >= 0) {
            while (aLen >= 0) {
                int aNum = aArr[aLen] - '0';
                int sum = aNum + offset;
                int value = sum % 2;
                offset = sum / 2;
                sb.append(value);
                aLen--;
            }
        }
        if (bLen >= 0) {
            while (bLen >= 0) {
                int bNum = bArr[bLen] - '0';
                int sum = bNum + offset;
                int value = sum % 2;
                offset = sum / 2;
                sb.append(value);
                bLen--;
            }
        }
        if (offset > 0) {
            sb.append(offset);
        }
        System.out.println("sb:" + sb.toString());

        //字符串翻转
        char[] chars = sb.toString().toCharArray();
        for (int i = 0, j = chars.length - 1; i < chars.length / 2; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return String.valueOf(chars);
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
