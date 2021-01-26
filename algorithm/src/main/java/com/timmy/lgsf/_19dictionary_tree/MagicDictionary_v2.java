package com.timmy.lgsf._19dictionary_tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * 解题思路2：HashMap
 * 1。使用HasnMap保存构建的元素 -- 切片思想
 * -其中key值为字符串元素的长度length，value为字符串的List集合
 * ---插入时先判断长度，长度存在，放到list集合中，不存在，则新建list集合
 * -判断条件
 * --先判断是否存在当前字符串的长度的key值，不存在，则直接返回false
 * --存在则判断list集合中是否存在相差一个自负的字符串
 */
public class MagicDictionary_v2 {

    private HashMap<Integer, ArrayList<String>> wordMap;

    public MagicDictionary_v2() {
        wordMap = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            wordMap.computeIfAbsent(word.length(), new Function<Integer, ArrayList<String>>() {
                @Override
                public ArrayList<String> apply(Integer integer) {
                    return new ArrayList<>();
                }
            }).add(word);
        }
    }

    public boolean search(String searchWord) {
        if (!wordMap.containsKey(searchWord.length())) {
            return false;
        }
        for (String word : wordMap.get(searchWord.length())) {
            if (1 == countDiff(word, searchWord)) {
                return true;
            }
        }
        return false;
    }

    private int countDiff(String word, String searchWord) {
        int diff = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != searchWord.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    public void print() {
        System.out.println(wordMap.toString());
    }


}
