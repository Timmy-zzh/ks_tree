package com.timmy.practice._08month;

import java.util.ArrayDeque;

public class _03翻转字符串里的单词_151 {

    public static void main(String[] args) {
        _03翻转字符串里的单词_151 demo = new _03翻转字符串里的单词_151();
//        String res = demo.reverseWords("the sky is blue");
        String res = demo.reverseWords("  hello world  ");
        System.out.println("res:" + res);
    }

    /**
     * 2.双端队列解法
     * -遍历字符串中的字符，遍历到字符时将该字符保存到stringbuild中，
     * --如果便利到空字符了，说明前面的单词遍历结速了，将前面的单词保存到数据结构中（队列或栈）
     * --最后从保存所有单词的数据结构遍历出单词，并用空格进行拼接
     */
    public String reverseWords(String s) {
        if (s == null || "".equals((s = s.trim()))) {
            return "";
        }
        StringBuilder word = new StringBuilder();
        ArrayDeque<String> queue = new ArrayDeque<>();

        System.out.println(s);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch != ' ') {
                word.append(ch);
            } else {
                if (word.length() > 0) {
                    queue.offerFirst(word.toString());
                    word.setLength(0);
                }
            }
        }
        if (word.length() > 0) {
            queue.offerFirst(word.toString());
        }
        return String.join(" ", queue);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，字符串由连续的单词 和空格组成，现在需要将字符串中的单词翻转，
     * --使得最后位置的单词放到前面，并输出最后生成的新字符串（新字符串中的单词由空字符分割）
     * 2。解题思路
     * -对原始字符串进行split切割，按照空字符切分，切分结果为一个数组，反序遍历该数组
     * -过滤空字符串
     * -使用stringbuild保存新字符串的内容
     */
    public String reverseWords_v1(String s) {
        if (s == null || "".equals(s = s.trim())) {
            return "";
        }
        String[] splits = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = splits.length - 1; i >= 0; i--) {
            String str = splits[i].trim();
            if (str.length() != 0) {
                sb.append(" ").append(str);
            }
        }
        return sb.substring(1);
    }

    /**
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     *
     * 说明：
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     *
     * 示例 1：
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     *
     * 示例 2：
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
     *
     * 示例 3：
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
     *
     * 示例 4：
     * 输入：s = "  Bob    Loves  Alice   "
     * 输出："Alice Loves Bob"
     *
     * 示例 5：
     * 输入：s = "Alice does not even like bob"
     * 输出："bob like even not does Alice"
     *
     * 提示：
     * 1 <= s.length <= 104
     * s 包含英文大小写字母、数字和空格 ' '
     * s 中 至少存在一个 单词
     * 进阶：
     * 请尝试使用 O(1) 额外空间复杂度的原地解法。
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
     */
}
