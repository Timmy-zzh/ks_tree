package com.timmy.lgsf._03tree._19dictionary_tree;

public class _01实现一个魔法字典_676 {

    public static void main(String[] args) {
//        MagicDictionary magicDictionary = new MagicDictionary();
//        String[] words = {"abc", "abcd", "abd", "b", "bcd", "efg"};
//        magicDictionary.buildDict(words);
//        //插入abd
//        magicDictionary.insrt("abx");
////        magicDictionary.print();
//        boolean exist = magicDictionary.search("bcd");
//        System.out.println("exist:" + exist);

//        MagicDictionary_v1 demo = new MagicDictionary_v1();
//        String[] words = {"hello", "leetcode"};
//        demo.buildDict(words);
//        boolean result = demo.search("lettcode");
//        System.out.println("reslut:" + result);

        MagicDictionary_v2 demo = new MagicDictionary_v2();
        String[] words = {"hello", "leetcode"};
        demo.buildDict(words);
        demo.print();
        boolean result = demo.search("hhllo");
        System.out.println("reslut:" + result);

    }

    /**
     * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
     * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
     *
     * 实现 MagicDictionary 类：
     *
     * MagicDictionary() 初始化对象
     * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
     * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
     * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
     *  
     * 示例：
     *
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
     * 链接：https://leetcode-cn.com/problems/implement-magic-dictionary
     */
}
