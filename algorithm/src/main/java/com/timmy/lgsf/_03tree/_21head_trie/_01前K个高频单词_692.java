package com.timmy.lgsf._03tree._21head_trie;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class _01前K个高频单词_692 {

    public static void main(String[] args) {
        _01前K个高频单词_692 demo = new _01前K个高频单词_692();
//        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> list = demo.topKFrequent(words, 4);
        PrintUtils.printStr(list);
    }

    /**
     * 解题思路2：
     * -桶排序 + 字典树
     * --桶排序：根据单词出现的次数，次数相同，则把该单词存放在同一个标号的桶中
     * --桶中的单词使用字典树保存，（其中节点保存有word单词的String类型，只有最后的字符节点word才有值）
     */

    /**
     * 1。理解题意
     * -输入一串字符串集合，计算单词出现的次数，根据单词出现次数进行排序，求出现前k个出现次数最多的单词
     * 2。解题思路
     * -遍历字符串集合，使用HashMap保存单词出现的次数，key为单词，value为该单词出现的次数
     * -根据单词出现次数进行排序，拿到次数最多的前k个单词，
     * --使用小顶堆保存前k个次数，然后拿到对应的单词
     * 3。边界和细节问题
     * -HashMap保存单词和对应出现的次数
     * -小顶堆保存次数出现前k的单词
     * -因为是小顶堆，获取到的根节点单词次数最小，需要翻转
     */
    private List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        // 遍历单词次数，并用小顶堆保存
        PriorityQueue<HashMap.Entry<String, Integer>> queue = new PriorityQueue<>(k,
                new Comparator<HashMap.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> t1) {
                        return entry.getValue() - t1.getValue();
                    }
                });
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            if (queue.size() != k || entry.getValue() > queue.peek().getValue()) {
                if (queue.size() == k) {
                    queue.poll();
                }
                queue.offer(entry);
            }
        }

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            list.add(queue.poll().getKey());
        }
        Collections.reverse(list);

        return list;
    }

    /**
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * 示例 1：
     *
     * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * 输出: ["i", "love"]
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     *     注意，按字母顺序 "i" 在 "love" 之前。
     *  
     * 示例 2：
     *
     * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * 输出: ["the", "is", "sunny", "day"]
     * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
     *     出现次数依次为 4, 3, 2 和 1 次。
     *
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-words
     */
}
