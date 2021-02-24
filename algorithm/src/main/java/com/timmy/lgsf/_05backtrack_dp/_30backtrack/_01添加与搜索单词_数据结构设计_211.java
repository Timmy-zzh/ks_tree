package com.timmy.lgsf._05backtrack_dp._30backtrack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _01添加与搜索单词_数据结构设计_211 {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        boolean res = wordDictionary.search("pad");// return False
        System.out.println("pad:" + res);
        res = wordDictionary.search("bad"); // return  True
        System.out.println("bad:" + res);
        res = wordDictionary.search(".ad"); // return True
        System.out.println(".ad:" + res);
        res = wordDictionary.search("b.."); // return True
        System.out.println("b..:" + res);
    }

    /**
     * 1.理解题意
     * -设计一个数据结构，要求可以添加单词到数据结构中去，并可以搜索单词，查看单词word是否在之前的集合中
     * --其中搜索单词存在通配符"."可以匹配任何字符
     * 2。解题思路
     * -添加单词使用HadhSet保存，搜索单词通过判断单词是否在集合中存在，特殊处理，如果搜索单词存在通配符"."，则判断单个字符是否相等
     * --上诉思路时间复杂度高，
     * 2。1。优化方案：根据字符串长度分桶保存
     * -使用HashMap作为底层数据结构，先判断单词长度，长度一致，则保存到同一个HashSet中
     */
    static class WordDictionary {
        Map<Integer, Set<String>> map;

        public WordDictionary() {
            map = new HashMap<>();
        }

        void addWord(String word) {
            int length = word.length();
            if (map.containsKey(length)) {
                map.get(length).add(word);
            } else {
                Set<String> set = new HashSet<>();
                set.add(word);
                map.put(length, set);
            }
        }

        boolean search(String word) {
            int length = word.length();
            if (!map.containsKey(length)) {
                return false;
            }

            Set<String> set = map.get(length);
            if (set.contains(word)) {
                return true;
            }
            if (word.contains(".")) {
                //遍历集合中所有的单词是否与搜索单词是否匹配
                for (String str : set) {
                    if (equal(str, word)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean equal(String str, String word) {
            if (str.length() != word.length()) {
                return false;
            }
            //单个自负匹配
            char[] strArr = str.toCharArray();
            char[] wordArr = word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                if (wordArr[i] == '.') {
                    continue;
                }
                if (wordArr[i] != strArr[i]) {
                    return false;
                }
            }
            return true;
        }
    }

//    static class WordDictionary {
//        Set<String> hashSet;
//
//        public WordDictionary() {
//            hashSet = new HashSet<>();
//        }
//
//        void addWord(String word) {
//            hashSet.add(word);
//        }
//
//        boolean search(String word) {
//            if (hashSet.contains(word)) {
//                return true;
//            }
//            if (word.contains(".")) {
//                //遍历集合中所有的单词是否与搜索单词是否匹配
//                for (String str : hashSet) {
//                    if (equal(str, word)) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        private boolean equal(String str, String word) {
//            if (str.length() != word.length()) {
//                return false;
//            }
//            //单个自负匹配
//            char[] strArr = str.toCharArray();
//            char[] wordArr = word.toCharArray();
//            for (int i = 0; i < wordArr.length; i++) {
//                if (wordArr[i] == '.') {
//                    continue;
//                }
//                if (wordArr[i] != strArr[i]) {
//                    return false;
//                }
//            }
//            return true;
//        }
//    }


    /**
     * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     *
     * 实现词典类 WordDictionary ：
     *
     * WordDictionary() 初始化词典对象
     * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
     * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；
     * 否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
     *  
     * 示例：
     *
     * 输入：
     * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
     * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
     * 输出：
     * [null,null,null,null,false,true,true,true]
     *
     * 解释：
     * WordDictionary wordDictionary = new WordDictionary();
     * wordDictionary.addWord("bad");
     * wordDictionary.addWord("dad");
     * wordDictionary.addWord("mad");
     * wordDictionary.search("pad"); // return False
     * wordDictionary.search("bad"); // return True
     * wordDictionary.search(".ad"); // return True
     * wordDictionary.search("b.."); // return True
     *  
     * 提示：
     * 1 <= word.length <= 500
     * addWord 中的 word 由小写英文字母组成
     * search 中的 word 由 '.' 或小写英文字母组成
     * 最多调用 50000 次 addWord 和 search
     *
     * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
     */
}
