package com.timmy.lgsf._03tree._4dictionary_tree;

import com.timmy.common.TrieNode;

public class _02实现Trie前缀树_208 {

    public static void main(String[] args) {

    }

    //字典树，创建，插入，搜索，startWith判断
    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            TrieNode head = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (head.children[index] == null) {
                    head.children[index] = new TrieNode();
                }
                head = head.children[index];
            }
            head.isWord = true;
        }

        boolean search(String word) {
            TrieNode head = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (head.children[index] == null) {
                    return false;
                }
                head = head.children[index];
            }
            return head.isWord;
        }

        boolean startsWith(String prefix) {
            TrieNode head = root;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if (head.children[index] == null) {
                    return false;
                }
                head = head.children[index];
            }
            return true;
        }
    }

    /**
     * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
     *
     * 示例:
     *
     * Trie trie = new Trie();
     *
     * trie.insert("apple");
     * trie.search("apple");   // 返回 true
     * trie.search("app");     // 返回 false
     * trie.startsWith("app"); // 返回 true
     * trie.insert("app");
     * trie.search("app");     // 返回 true
     * 说明:
     *
     * 你可以假设所有的输入都是由小写字母 a-z 构成的。
     * 保证所有输入均为非空字符串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}
