package com.timmy.lgsf._01basic._9deque;

import com.timmy.common.PrintUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _00翻转字符串里的单词_151 {

    public static void main(String[] args) {
        _00翻转字符串里的单词_151 demo = new _00翻转字符串里的单词_151();
//        String result = demo.reverseWords("the sky is blue");
//        String result = demo.reverseWords("  hello world!  ");
//        String result = demo.reverseWords("a good   example");
        String result = demo.reverseWords("  Bob    Loves  Alice   ");
        System.out.println("result:" + result);
    }

    /**
     * 解题思路：使用双端队列保存数据
     * 6c解题法：
     * -将不断遍历字符串中的字符，使用StringBuild作为缓冲区
     * -当遍历到不是空字符，则保存到缓冲区；当遍历到空字符，则说明遍历到分割点，需要将缓冲区数据添加到队列中
     */
    public String reverseWords(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                word.append(c);
            } else {
                if (word.length() != 0) {
                    System.out.println("word:" + word.toString());
                    //插入到队头
                    deque.offerFirst(word.toString());
                    word.setLength(0);
                }
            }
        }
        if (word.length() != 0) {
            deque.offerFirst(word.toString());
        }
        System.out.println(deque.toString());
        return String.join(" ", deque);
    }

    /**
     * 解题思路：
     * 6c解题法：
     * 1。理解题意，找到解题的思路
     * -因为要对输入的字符串中单词的位置进行翻转，而且还要处理空字符问题
     * -可以对字符串进行切割，然后将切割后的字符串放在集合中，最后组装成字符串
     * 2。数据结构与算法选择
     * -字符串，List，逆序输出
     * 3。编码
     * 4。思考更优解
     * 5。编码
     * 6。总结与变形
     */
    public String reverseWords_v1(String s) {
        System.out.println(s);
        if (s == null || (s = s.trim()).equals("")) {
            return "";
        }
        System.out.println(s);
        String[] split = s.split(" ");
        PrintUtils.print(split);
        List<String> list = new ArrayList<>();
        for (int i = split.length - 1; i >= 0; i--) {
            String subStr = split[i].trim();
            System.out.println("subStr:" + subStr);
            if (subStr.length() != 0) {
                list.add(subStr);
            }
        }
        return String.join(" ", list);
    }

    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * <p>
     * 说明：
     * 无空格字符构成一个 单词 。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *  
     * 示例 1：
     * 输入："the sky is blue"
     * 输出："blue is sky the"
     * <p>
     * 示例 2：
     * 输入："  hello world!  "
     * 输出："world! hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * <p>
     * 示例 3：
     * 输入："a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     * <p>
     * 示例 4：
     * 输入：s = "  Bob    Loves  Alice   "
     * 输出："Alice Loves Bob"
     * <p>
     * 示例 5：
     * 输入：s = "Alice does not even like bob"
     * 输出："bob like even not does Alice"
     * <p>
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
     */
}
