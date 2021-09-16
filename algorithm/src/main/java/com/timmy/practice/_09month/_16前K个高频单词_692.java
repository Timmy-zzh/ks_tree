package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class _16前K个高频单词_692 {

    public static void main(String[] args) {
        _16前K个高频单词_692 demo = new _16前K个高频单词_692();
//        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        //["the","day","is","sunny","the","the","the","sunny","is","is"]
        //4
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
//        List<String> res = demo.topKFrequent_v1(words, 2);
        List<String> res = demo.topKFrequent(words, 4);
        PrintUtils.printStr(res);
    }

    /**
     * 2。topK问题，使用优先级队列解法
     * -使用小顶堆保存单词，有比堆顶元素出现次数多的单词入队列，
     * -插入单词后，如果队列最后数量大于k，则堆顶元素出队
     * -最后将队列中的元素反序出队列返回
     */
    public List<String> topKFrequent(String[] words, int k) {
        final Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                wordMap.put(word, 1);
            } else {
                wordMap.put(word, wordMap.get(word) + 1);
            }
        }

        //小顶堆
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue().equals(e2.getValue()) ?
                        e2.getKey().compareTo(e1.getKey()) :
                        e1.getValue() - e2.getValue();
            }
        });

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (queue.size() < k || entry.getValue() >= queue.peek().getValue()) {
                queue.add(entry);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
//            System.out.println(queue.peek().getKey());
            res.add(0, queue.poll().getKey());
        }

        return res;
    }

    /**
     * 1.理解题意
     * -输入一个字符串数组，和数量k，字符串数组中的单词可能存在相同的单词（一个单词会出现多次），求出现次数最多的前k个单词
     * 2。解题思路：暴力解法
     * -先使用Map 计算得到每个单词出现的次数
     * -然后对map中的元素进行排序，先根据出现次数大的降序排序，出现次数相同的则按照字典序进行排序
     */
    public List<String> topKFrequent_v1(String[] words, int k) {
        final Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                wordMap.put(word, 1);
            } else {
                wordMap.put(word, wordMap.get(word) + 1);
            }
        }

        //获取map中的所有单词集合
        List<String> wordList = new ArrayList<>(wordMap.size());
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            wordList.add(entry.getKey());
        }
        //使用Collections对单词集合进行排序
        Collections.sort(wordList, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                //单词相同，按照字典序，不同，按照单词出现次数降序
                return wordMap.get(w1).equals(wordMap.get(w2)) ? w1.compareTo(w2) : wordMap.get(w2) - wordMap.get(w1);
            }
        });
        //获取排序好的前k个单词返回
        return wordList.subList(0, k);
    }

    /**
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * 示例 1：
     * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * 输出: ["i", "love"]
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     *     注意，按字母顺序 "i" 在 "love" 之前。
     *
     * 示例 2：
     * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * 输出: ["the", "is", "sunny", "day"]
     * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
     *     出现次数依次为 4, 3, 2 和 1 次。
     *
     * 注意：
     * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
     * 输入的单词均由小写字母组成。
     *
     * 扩展练习：
     * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-words
     */
}
