package com.timmy.lgsf._03tree._6head_trie;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _02根据字符出现频率排序_451 {

    public static void main(String[] args) {
        _02根据字符出现频率排序_451 demo = new _02根据字符出现频率排序_451();
        String result = demo.frequencySort("Aabb");
        System.out.println("result:" + result);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，根据字符串里面的字符出现次数，进行降序排序输出
     * 2。解题思路
     * -遍历字符串中的字符，使用HashMap保存，key为字符，value为字符出现的次数，
     * -使用大顶堆保存HashMap的值，然后取出大顶堆中的元素，进行组装后输出
     * 3。边界和细节问题
     * -HashMap保存字符，和对应出现次数
     * -大顶堆保存entry值，需要设置自定义的Comparen
     * -最后使用StringBuild组装
     */
    private String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (hashMap.containsKey(ch)) {
                hashMap.put(ch, hashMap.get(ch) + 1);
            } else {
                hashMap.put(ch, 1);
            }
        }

        //大顶堆
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> entry, Map.Entry<Character, Integer> t1) {
                return t1.getValue() - entry.getValue();
            }
        });

        queue.addAll(hashMap.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> poll = queue.poll();
            Character key = poll.getKey();
            int count = poll.getValue();
            for (int i = 0; i < count; i++) {
                sb.append(key);
            }
        }

        return sb.toString();
    }


    /**
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     *
     * 示例 1:
     *
     * 输入:
     * "tree"
     *
     * 输出:
     * "eert"
     *
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     * 示例 2:
     *
     * 输入:
     * "cccaaa"
     *
     * 输出:
     * "cccaaa"
     *
     * 解释:
     * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     * 示例 3:
     *
     * 输入:
     * "Aabb"
     * 输出:
     * "bbAa"
     *
     * 解释:
     * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
     * 注意'A'和'a'被认为是两种不同的字符。
     *
     * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
     */
}
