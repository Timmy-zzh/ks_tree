package com.timmy.lgsf._03tree._19dictionary_tree;


/**
 * 字典树封城
 * -实现构造，遍历，搜索
 * 1。构造
 * -输入一些列字符串数组，然后将这些字符串都插入到字典树中
 * 2.搜索
 * -输入一个字符串，判断将字符串改变其中的一个字母就可以变成另一个字母
 */
public class MagicDictionary_v1 {

    TrieNode root;

    public MagicDictionary_v1() {
        root = new TrieNode();
    }

    /**
     * 遍历字符串数组，对每个字符串都进行插入字典树操作
     * -局部变量 head = root
     * -遍历字符串，遍历到字符在字典树中存在，则head 往下递归赋值
     * -不存在则新建节点，并往下递归赋值
     * <p>
     * 默认构造字典树有两个单词： ["hello", "leetcode"]
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
     * 字典树的单词字符串查找
     * -遍历字符数组，从字典树根节点开始遍历是否存在满足条件的字符
     * -是否是字符串结束标示
     * <p>
     * 从字典树中判断是否存在存在一个字符串，改变其中的一个字符，就可以和输入的字符串相同
     * 例如：字典树中存在字符串 hello，输入hhllo，
     * -其中第一个字符相等，第二个字符不想等，后面的自负又相等
     * <p>
     * 2。实现思路：
     * -遍历字符数组，从字典树根节点开始遍历，判断是否存在满足条件的字符
     * --如果存在相等的字符，则继续往下递归遍历
     * --如果存在不相等的字符，则以不想等的字符为根节点判断后面的字符是否相等
     */
    public boolean search(String searchWord) {
        TrieNode head = root;
        for (int i = 0; i < searchWord.length(); i++) {
            //判断根节点是否存在相等的字符
            char ch = searchWord.charAt(i);
            for (int j = 0; j < head.children.length; j++) {
                // 相等或子节点为空，继续往下遍历
                if (ch == j + 'a' || head.children[j] == null) {
                    continue;
                }
                // 子节点不为空，且节点字符不相登
                // -》则判断后面的字符串子串是否相等
                if (postChar(head.children[j], searchWord, i + 1)) {
                    return true;
                }
            }
            //字符相等才能往下遍历
            if (head.children[ch - 'a'] == null) {
                return false;
            }
            head = head.children[ch - 'a'];
        }
        return false;
    }

    private boolean postChar(TrieNode root, String word, int index) {
        for (int i = index; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (root.children[pos] == null) {
                return false;
            }
            root = root.children[pos];
        }
        return root.isWord;
    }

}
