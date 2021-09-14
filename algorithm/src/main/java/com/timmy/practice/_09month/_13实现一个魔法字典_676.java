package com.timmy.practice._09month;

import com.timmy.common.TrieNode;

public class _13实现一个魔法字典_676 {

    public static void main(String[] args) {
//        MagicDictionary demo = new MagicDictionary();
//        String[] dictionary = {"hello", "leetcode", "have"};
//        demo.buildDict(dictionary);
//        demo.print();
////        boolean search_com = demo.search_com("helll");
////        System.out.println("search:" + search_com);
//        boolean search = demo.search("hhllo");
//        System.out.println("search:" + search);

        _13MagicDictionary_V2 demo = new _13MagicDictionary_V2();
        String[] dictionary = {"hello", "leetcode", "abcde","have"};
        demo.buildDict(dictionary);
//        boolean search_com = demo.search_com("helll");
//        System.out.println("search:" + search_com);
        boolean search = demo.search("hhllo");
        System.out.println("search:" + search);
    }

    /**
     * 1.理解题意
     * -实现一个魔法字典树，输入很多单词也就是字符串，将每个字符串按照单个字符进行存储在字典树中
     * 2。解题思路
     * -创建字典树的根节点，遍历字符串中的字符，判断当前遍历到的字符在字典树中是否存在？
     * -如果不存在则创建一个节点，并将遍历节点转移到当前节点，如果存在，则只需要移动遍历节点到当前节点
     * -字符串遍历结束，最后节点的isEnd标记为true，说明从根节点到当前节点的路径为一个字符串
     */
    static class MagicDictionary {

        TrieNode root;

        public MagicDictionary() {
            root = new TrieNode();
        }

        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                buildDict(str);
            }
        }

        private void buildDict(String str) {
            TrieNode node = root;   //每个单词，都是从根节点开始遍历
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.isEnd = true;
            node.setEnd(str);
        }

        //打印，dfs
        private void print() {
            printNode(root);
        }

        private void printNode(TrieNode node) {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    if (node.children[i].isEnd) {
                        System.out.println(node.children[i].word);
                    }
                    printNode(node.children[i]);
                }
            }
        }

        /**
         * 1。理解题意
         * -输入一个字符串，搜索该字符串在字典树中是否存在
         * 2。解题思路
         * -从根节点开始遍历，并遍历字符串中的字符，判断当前遍历到的字符在字典树中的节点是否存在，
         * -字符串遍历结束后的节点是否是单词的结束位置？进行返回
         */
        public boolean search_com(String searchWord) {
            TrieNode node = root;
            for (int i = 0; i < searchWord.length(); i++) {
                char ch = searchWord.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return node.isEnd;
        }

        /**
         * 1.理解题意
         * -输入一个单词，在字典树中是否符合该字符串的单词，要求其中有一个字符不相等
         * 2。解题思路
         * -从字典树根节点开始，并开始遍历字符串，当遇到不一样的单个字符时，从下一个节点位置开始遍历，单词后面的字符串是否相同
         * todo 2.2.解法二
         * -构造方法buildDict时，使用hashmap保存每个单词长度对应的数据，Map<Integer,List<String>>
         * -search的时候，判断输入字符串的长度和Map中的集合单词比较，看是否存在只相差一个字符的单词，存在返回true
         */
        public boolean search(String searchWord) {
            TrieNode node = root;
            for (int i = 0; i < searchWord.length(); i++) {
                char ch = searchWord.charAt(i);
                for (int j = 0; j < 26; j++) {
                    //如果遇到相等的字符，或者不存在该节点 -->继续判断下一个节点
                    if (ch == (char) (j + 'a') || node.children[j] == null) {
                        continue;
                    }

                    //存在，且不想等，判断下一个节点和后面的字符串是否相等
                    if (searchPost(searchWord, i + 1, node.children[j])) {
                        return true;
                    }
                }
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return false;
        }

        private boolean searchPost(String searchWord, int index, TrieNode root) {
            TrieNode node = root;
            for (int i = index; i < searchWord.length(); i++) {
                char ch = searchWord.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return node.isEnd;
        }
    }

    /**
     * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，
     * 请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
     *
     * 实现 MagicDictionary 类：
     * MagicDictionary() 初始化对象
     * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
     * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
     * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
     *
     * 示例：
     * 输入
     * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
     * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
     * 输出
     * [null, null, false, true, false, false]
     *
     * 解释
     * MagicDictionary magicDictionary = new MagicDictionary();
     * magicDictionary.buildDict(["hello", "leetcode"]);
     * magicDictionary.search("hello"); // 返回 False
     * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
     * magicDictionary.search("hell"); // 返回 False
     * magicDictionary.search("leetcoded"); // 返回 False
     *  
     *
     * 提示：
     * 1 <= dictionary.length <= 100
     * 1 <= dictionary[i].length <= 100
     * dictionary[i] 仅由小写英文字母组成
     * dictionary 中的所有字符串 互不相同
     * 1 <= searchWord.length <= 100
     * searchWord 仅由小写英文字母组成
     * buildDict 仅在 search 之前调用一次
     * 最多调用 100 次 search
     *
     * 链接：https://leetcode-cn.com/problems/implement-magic-dictionary
     */
}
