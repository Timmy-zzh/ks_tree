package com.timmy.lgsf._05backtrack_dp._31backtrack_bit;

public class _bit1_只出现一次的数字_136 {

    public static void main(String[] args) {
        _bit1_只出现一次的数字_136 demo = new _bit1_只出现一次的数字_136();
        int[] nums = {4, 1, 2, 1, 2};
        int res = demo.singleNumber(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -数组中有很多数字，其中有一个数字只出现过一次，其他元素读出现过两次，找到这个只出现一次的数字
     * 2。解题思：位运算异或^ -- 不同为1，相同为0
     * 原理： n^n = 0  n^0 = n
     * --> 4^4
     * -0100
     * ^0100
     * -----
     * -0000
     * <p>
     * ---》n^0=n
     * --4^0=4
     * -0100
     * ^0000
     * ------
     * -0100
     * <p>
     * 2.1.假如数组元素如： [1,2,3,4,3,2,1]
     * 1^1 ^ 2^2 ^ 3^3 ^4 => 0^0^0^4 => 4
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     *
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * 链接：https://leetcode-cn.com/problems/single-number
     */
}
