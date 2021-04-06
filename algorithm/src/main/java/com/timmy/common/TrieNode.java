package com.timmy.common;

/**
 * 字典树节点结构定义
 * -假设字典树中的节点都是小写字母
 * --所以节点的子节点使用数组保存子节点集合，数组长度为26
 * --子节点数组，采用下标对应的值保存 字符'a'在数组下标0的位置
 * -使用一个boolean 值，标示当前节点是否一个单词的结尾
 */
public class TrieNode {

    private static final int SIZE = 26;
    public boolean isEnd;
    public TrieNode[] children;
    //到该节点时的字符串是什么
    public String word;

    public TrieNode() {
        isEnd = false;
        children = new TrieNode[SIZE];
        word = "";
    }

    public void setEnd(String newWord) {
        word = newWord;
    }
}
