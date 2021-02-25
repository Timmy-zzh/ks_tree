package com.timmy.lgsf._05backtrack_dp._31backtrack_bit;

public class _bit2_汉明距离_461 {

    public static void main(String[] args) {
        _bit2_汉明距离_461 demo = new _bit2_汉明距离_461();
        int res = demo.hanmingDistance(1, 4);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -汉明距离指：两个数字的二进制表示，在在相同位置上位的值不同的个数
     * -可以通过 位运算异或^操作符得到结果n：然后求该结果n上位数为1的个数
     * 2。解题思路
     * -将结果n，不断往右移1位，然后判断移动的这一位是否是1
     *
     * @param x
     * @param y
     * @return
     */
    public int hanmingDistance(int x, int y) {
        int sum = x ^ y;
        int count = 0;
        while (sum != 0) {
            //求最后一位的值是否为1
            count += (sum & 1);
            sum = sum >> 1;
        }
        return count;
    }

    /**
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     *
     * 注意：
     * 0 ≤ x, y < 231.
     *
     * 示例:
     * 输入: x = 1, y = 4
     * 输出: 2
     *
     * 解释:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     *
     * 上面的箭头指出了对应二进制位不同的位置。
     *
     * 链接：https://leetcode-cn.com/problems/hamming-distance
     */
}
