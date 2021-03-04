package com.timmy.lgsf._03tree._4dictionary_tree;


import com.timmy.common.TrieNode;

/**
 * 字典树封城
 * -实现构造，遍历，搜索
 * 1。构造
 * -输入一些列字符串数组，然后将这些字符串都插入到字典树中
 */
public class MagicDictionary {

    TrieNode root;

    public MagicDictionary() {
        root = new TrieNode();
    }

    /**
     * 遍历字符串数组，对每个字符串都进行插入字典树操作
     * -局部变量 head = root
     * -遍历字符串，遍历到字符在字典树中存在，则head 往下递归赋值
     * -不存在则新建节点，并往下递归赋值
     */
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            TrieNode head = root;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                if (head.children[ch - 'a'] == null) {
                    head.children[ch - 'a'] = new TrieNode();
                }
                head = head.children[ch - 'a'];
            }
            head.isWord = true;
        }
    }

    /**
     * 字典树的前序遍历： 根 - 左 - 右
     * -遍历字典树的子节点children，子节点不为空，继续递归
     */
    public void preOrder(TrieNode root) {
        TrieNode[] children = root.children;
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                TrieNode child = children[i];
                char ch = (char) ('a' + i);
                System.out.println(ch);
                if (child.isWord) {
                    System.out.println("---end---");
                }
                preOrder(child);
            }
        }
    }

    /**
     * 插入一个字符串到字典树中
     * -遍历字符数组，遍历到单个字符
     * -并从字典树根节点开始查找子节点数组中是否存在该自负对应下表的节点，
     * -不存在则新增，存在则往下递归
     */
    public void insrt(String word) {
        TrieNode head = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (head.children[index] == null) {
                head.children[index] = new TrieNode();
            }
            head = head.children[index];
        }
        head.isWord = true;
    }

    /**
     * 字典树的单词字符串查找
     * -遍历字符数组，从字典树根节点开始遍历是否存在满足条件的字符
     * -是否是字符串结束标示
     */
    public boolean search(String searchWord) {
        TrieNode head = root;
        char[] chars = searchWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (head.children[index] == null) {
                return false;
            }
            //存在该元素，往下递归遍历
            head = head.children[index];
        }
        return head.isWord;
    }

    public void print() {
        preOrder(root);
    }
}
