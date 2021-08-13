package com.timmy.practice._08month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _11特殊的二进制序列_761 {

    public static void main(String[] args) {
        _11特殊的二进制序列_761 demo = new _11特殊的二进制序列_761();
        String res = demo.makeLargestSpecial("11011000");
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，字符串中的字符为0，1二进制，特殊的二进制序列有两个特点，（0，1的数量相等，前缀1的数量大于等于0的数量）
     * -原始字符串可以拆分成多个子串（且子串也是特殊的二进制序列），将这些子串从新排列最后生成的结果字典序最大返回
     * 2。解题思路：递归
     * -遍历字符串中的字符，通过判断1，0字符的个数查找特殊的子序列，并使用list进行存储
     * -存储好后，按照字典序进行排序，并返回
     */
    public String makeLargestSpecial(String s) {
        System.out.println("ss----:" + s);
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        List<String> list = new ArrayList<>();
        int start = 0;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            count += ch == '1' ? 1 : -1;
            if (count == 0) {  //遇到0，1字符个数相等的情况，截取该段子串
                String subStr = s.substring(start + 1, i);
                System.out.println("subStr:" + subStr);
                String itemStr = "1" + makeLargestSpecial(subStr) + "0";
                System.out.println("itemStr:" + itemStr);
                list.add(itemStr);
                start = i + 1;
            }
        }
        //将list集合中的子串按照字典序进行排序
        System.out.println("======");
        PrintUtils.printStr(list);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s1 : list) {
            sb.append(s1);
        }
        return sb.toString();
    }

    /**
     * 特殊的二进制序列是具有以下两个性质的二进制序列：
     * -0 的数量与 1 的数量相等。
     * -二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
     * 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，
     * 然后将它们交换。（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
     *
     * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
     *
     * 示例 1:
     * 输入: S = "11011000"
     * 输出: "11100100"
     * 解释:
     * 将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
     * 这是在进行若干次操作后按字典序排列最大的结果。
     *
     * 说明:
     * S 的长度不超过 50。
     * S 保证为一个满足上述定义的特殊 的二进制序列。
     * 链接：https://leetcode-cn.com/problems/special-binary-string
     */
}
