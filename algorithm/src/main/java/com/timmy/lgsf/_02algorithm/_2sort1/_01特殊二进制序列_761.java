package com.timmy.lgsf._02algorithm._2sort1;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class _01特殊二进制序列_761 {

    public static void main(String[] args) {
        _01特殊二进制序列_761 demo = new _01特殊二进制序列_761();
        String result = demo.makeLargestSpecial("11011000");
        System.out.println("result:" + result);
    }

    /**
     * 特殊的二进制序列是具有以下两个性质的二进制序列：
     * <p>
     * 0 的数量与 1 的数量相等。
     * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
     * 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串
     * ，然后将它们交换。（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
     * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
     * <p>
     * 示例 1:
     * 输入: S = "11011000"
     * 输出: "11100100"
     * 解释:
     * 将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
     * 这是在进行若干次操作后按字典序排列最大的结果。
     * <p>
     * 链接：https://leetcode-cn.com/problems/special-binary-string
     */

    /**
     * 解题思路：
     * 1。理解题意：
     * -将二进制字符串进行切割成特殊的子串（0和1的数量相等）-如何分割？ 0和1的数量相等判断
     * -然后比较子串的大小，大的放在前面（排序），进行拼接
     * 2。数据结构和算法选择
     * -递归
     * -遍历字符串，采用盯梢的方式，计算0和1的数量相等时，1x0 -》 1 110010 0 递归U形最后返回的结构
     * --截取前后1和0的字符-101100，对中间字符串进行递归调用，得到满足的特殊子串[10,1100], --》 110010
     * --1100进行递归，截取1和0，得到10，得到特殊子串10，比较，返回为 --》1100
     */
    public String makeLargestSpecial(String S) {
        StringBuilder result = new StringBuilder();
        int start = 0;
        int count = 0;//记录0和1字符的数量
        //保存特殊字符子串，
        List<String> list = new ArrayList<>();

        for (int i = 0; i < S.length(); i++) {
            count += S.charAt(i) == '1' ? 1 : -1;
            if (count == 0) {
                //截取，再递归
                String subStr = S.substring(start + 1, i);
                String str = makeLargestSpecial(subStr);
                System.out.println("str:" + str);
                list.add("1" + str + "0");
                start = i + 1;
            }
        }

        //排序
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
        System.out.println("-----");
        PrintUtils.printStr(list);
        for (int i = list.size() - 1; i >= 0; i--) {
            result.append(list.get(i));
        }
        return result.toString();
    }

}
