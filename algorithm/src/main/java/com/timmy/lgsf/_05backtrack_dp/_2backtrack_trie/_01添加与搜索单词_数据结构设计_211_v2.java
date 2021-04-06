package com.timmy.lgsf._05backtrack_dp._2backtrack_trie;

import com.timmy.common.TrieNode;

public class _01添加与搜索单词_数据结构设计_211_v2 {

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
     * 2。解题思路:使用字典树实现
     * -addWord方法 添加单词时，不断往字典树中新增字典树节点
     * -search方法的时候，需要从字典树中搜索
     * 3。边界和细节问题
     * -字典树每个节点保存的是字符
     * -保存完一个字符串后，最后一个节点需要设置表示为单词
     * -特殊通配符"。"处理，当检索到通配符时，需要遍历通配符后面的所有节点，是否与字符串相匹配
     */
    static class WordDictionary {
        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * 字典树新增字符
         * -遍历字符串中的单个字符，从跟节点开始不断遍历
         * -如果当前字符没有对应节点，则新增，新增后继续遍历下一个字符，需要使用一个变量保存每次遍历到的节点标示
         * -注意字符串最后的节点需要设置单词标示
         *
         * @param word
         */
        void addWord(String word) {
            char[] wordArr = word.toCharArray();
            TrieNode curr = root;
            //遍历单个字符，判断该字符是否在字典树中存在对应节点
            for (int i = 0; i < wordArr.length; i++) {
                int pos = wordArr[i] - 'a';
                if (curr.children[pos] == null) {
                    //不存在，则新建节点
                    curr.children[pos] = new TrieNode();
                }
                //不管存不存在，节点往下移动
                curr = curr.children[pos];
            }
            //最后一个单词
            curr.isEnd = true;
        }

        /**
         * 字典树检索
         *
         * @param word
         * @return
         */
        boolean search(String word) {
            return searchHelp(root, word);
        }

        private boolean searchHelp(TrieNode root, String word) {
            TrieNode curr = root;
            char[] wordArr = word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                int pos = wordArr[i] - 'a';
                if (wordArr[i] == '.') {   //通配符
                    //便利所有的子节点
                    for (int j = 0; j < 26; j++) {
                        if (curr.children[j] != null) {
                            //从。下个字符，和节点进行匹配
                            if (searchHelp(curr.children[j], word.substring(i + 1))) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                if (curr.children[pos] == null) {//不存在当前字符节点
                    return false;
                }
                //存在当前字符节点，往下移动，继续判断下一个字符
                curr = curr.children[pos];
            }
            return curr.isEnd;
        }
    }

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
