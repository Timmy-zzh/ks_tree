package com.timmy._review._10greedy;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _05划分字母区间_763 {

    public static void main(String[] args) {
        _05划分字母区间_763 demo = new _05划分字母区间_763();
        List<Integer> res = demo.partitionLabels("ababcbacadefegdehijhklij");
//        List<Integer> res = demo.partitionLabels("caedbdedda");
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，将字符串进行分片，尽量多分成不同的片段，且一个字母只能出现在一个片段中
     * 2。解题思路
     * -先遍历字符串中所有字符，并使用map记录每个字符最后出现的位置
     * -再次遍历单个字符，从遇到的第一个字符确定最远的路径，后面的字符有距离更远的，则更新右侧最远路径
     * --直到遍历到的字符位置和最远字符位置相同，则进入下一轮区间
     */
    public List<Integer> partitionLabels(String s) {
        System.out.println(s);
        List<Integer> res = new ArrayList<>();
        int[] indexs = new int[26];     //26个字母在字符串中的下标位置
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            indexs[chars[i] - 'a'] = i;
        }
        PrintUtils.print(indexs);

        int endIndex = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            int chIndex = indexs[chars[i] - 'a'];// 当前字符的最大距离
            System.out.println("i:" + i + " ,chars[i]:" + chars[i] + " ,chIndex:" + chIndex + " ,endIndex:" + endIndex);
            if (i == endIndex) {      //到达最远记录位置
                System.out.println("--:" + (i - last));
                res.add(i - last + 1);
                last = i;
            } else if (endIndex < chIndex) {    //存在更远的位置，更新
                endIndex = chIndex;
            }
        }

        return res;
    }

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 返回一个表示每个字符串片段的长度的列表。
     *
     * 示例：
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     *  
     * 提示：
     * S的长度在[1, 500]之间。
     * S只包含小写字母 'a' 到 'z' 。
     * 链接：https://leetcode-cn.com/problems/partition-labels
     */
}
