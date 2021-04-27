package com.timmy._review._00tree;


import com.timmy.common.TrieNode;

import java.util.Stack;

/**
 * 字典树的实现：
 * -节点结构为TrieNode,
 * -根节点为空，
 * -输入一个字符串数组，然后遍历这个字符串数组，遍历字符串时，遍历字符串的每个字符，构建字典树
 */
public class _07DictionaryTree {

    TrieNode root;

    public _07DictionaryTree() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    /**
     * 字典树的构建：
     * -在栈中定义一个指向根节点的局部变量，用于节点移动
     * -遍历字符串数组，然后对字符串中的字符进行遍历，每次遍历都询问当前字符在字典树中是否已存在对应的节点
     * --如果存在，则继续遍历后面的字符，节点转移
     * --如果不存在，则新建节点，并进行节点转移
     * -遍历到字符串的末尾，isEnd标示设置为true
     */
    public void build(String[] words) {
        TrieNode node;
        for (String word : words) {
            node = root;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                //当前节点 在node节点的children[]数组的位置，判断当前位置index是否为空
                int index = chars[i] - 'a';
                if (node.children[index] == null) {
                    //新建节点
                    TrieNode newNode = new TrieNode();
                    node.children[index] = newNode;
                    newNode.setEnd(node.word + chars[i]);
                }
                node = node.children[index];        //节点转移
            }
            node.isEnd = true;
        }
    }

    /**
     * 判断单词word在字典树中是否存在
     * -遍历搜索单词的各个字符，并从根节点进行节点搜索移动
     * -在单个字符的遍历过程中，如果对应节点存在，则继续往下遍历
     * --如果对应节点不存在，则直接返回false，表示字典树中不存在该单词
     * -单词word遍历到结尾，需要判断字典树转移到的当前节点的isEnd是否为true
     */
    public boolean search(String searchWord) {
        TrieNode node = root;
        char[] chars = searchWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEnd;
    }

    /**
     * 在字典树中插入单词word
     * -遍历字符串中的字符，并判断遍历到的字符在字典树中的节点是否存在
     * --如果对应节点存在，则遍历下一个字符，并将节点转移
     * --如果不存在，则新建对应节点，--》节点转移
     * -最后节点的isEnd设置为true
     */
    public void insert(String word) {
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.children[index] == null) {
                TrieNode newNode = new TrieNode();
                node.children[index] = newNode;
                newNode.setEnd(node.word + chars[i]);
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public void print() {
        //深度优先遍历
        TrieNode node = root;
//        dfs(node);
        //迭代法
        Stack<TrieNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TrieNode pop = stack.pop();
            System.out.println(pop.word + ",");
            if (pop.isEnd) {
                System.out.println();
            }
            for (int i = 0; i < pop.children.length; i++) {
                if (pop.children[i] != null) {
                    stack.push(pop.children[i]);
                }
            }
        }
    }

    /**
     * 递归实现
     */
    private void dfs(TrieNode child) {
        for (int i = 0; i < child.children.length; i++) {
            if (child.children[i] != null) {
                System.out.print((char) (i + 'a') + " ,");
                dfs(child.children[i]);
                if (child.children[i].isEnd) {
                    System.out.println();
                }
            }
        }
    }
}
