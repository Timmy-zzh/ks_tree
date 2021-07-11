package com.timmy.practice._07month;


public class _06整数反转_7 {

    public static void main(String[] args) {
        _06整数反转_7 demo = new _06整数反转_7();
        int[] nums = {123, -123, 120, Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int num : nums) {
            int res = demo.reverse(num);
            System.out.println(num + " --> " + res);
        }
    }

    /**
     * 1.理解题意
     * 2。解题思路
     * -根据数学特性进行解决，不断获取整数的最后一位，
     * --获取的最后一位数字，添加到原先累加的结果集中
     */
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        //保存符号标记，最后返回时使用
        int sign = x > 0 ? 1 : -1;
        //负数情况，转换成正数处理
        x = x * sign;

        long result = 0;
        int last = 0;
        while ((last = x % 10) != x) {
            //获取个位数
            result = result * 10 + last;
            x = x / 10;
        }
        result = result * 10 + last;    //最高位数字处理
        if (result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) (result * sign);
    }

    public int reverse_v2(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        //保存符号标记，最后返回时使用
        int sign = x > 0 ? 1 : -1;
        //负数情况，转换成正数处理
        x = x * sign;

        String val = String.valueOf(x);
        char[] chars = val.toCharArray();
        int len = chars.length;

        for (int start = 0, end = len - 1; start < len / 2; start++, end--) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
        }
        String reverseVal = String.valueOf(chars);

        long result = Long.valueOf(reverseVal);
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) (result * sign);
    }

    /**
     * 1.理解题意
     * -输入一个32位有符号的整数x（有符号表示数字是正数也可能是负数），现在要对数字部分进行反转，符号部分保留，返回整数反转后的结果
     * -整数反转后结果超过32位的整数范围，则返回0
     * 2。解题思路
     * -要将整数进行反转，可以先将整数转换成字符串，接着字符串转换为字符数组，对字符数组中的元素进行前后元素的调换
     * --元素调换可以使用一个新数组进行保存
     * --也可以在原数组中进行元素交换
     * -元素交换后的字符数组，转换成字符串，然后字符串通过Integer.valueof()方法转换成整数
     * -通过数学思维不断获取整数的最低位，并将最低位添加到结果中
     * 3。边界和细节问题
     * -字符串遍历数组越界控制
     * -符号问题
     * -转换过程中整数越界问题处理
     * 4.复杂度分析
     * -时间复杂度：O(n)
     * -空间复杂度：O(n)
     * 5.总结优化：
     * -多使用了一个新数组用于数组元素调换后的存储，可以优化为在原数组上进行元素调换
     */
    public int reverse_v1(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        //保存符号标记，最后返回时使用
        int sign = x > 0 ? 1 : -1;
        //负数情况，转换成正数处理
        x = x * sign;

        System.out.println("x:" + x);
        String val = String.valueOf(x);
        char[] chars = val.toCharArray();
        int len = chars.length;
        char[] newCh = new char[len];

        for (int i = 0; i < len; i++) {
            newCh[i] = chars[len - 1 - i];
        }
        String reverseVal = String.valueOf(newCh);
        System.out.println("reverseVal:" + reverseVal);

        long result = Long.valueOf(reverseVal);
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) (result * sign);
    }

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *  
     * 示例 1：
     * 输入：x = 123
     * 输出：321
     *
     * 示例 2：
     * 输入：x = -123
     * 输出：-321
     *
     * 示例 3：
     * 输入：x = 120
     * 输出：21
     *
     * 示例 4：
     * 输入：x = 0
     * 输出：0
     *
     * 提示：
     * -231 <= x <= 231 - 1
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     */
}
