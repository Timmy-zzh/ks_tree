package com.timmy.lgsf._01basic._9deque;

import java.util.Stack;

public class _01反转字符串中的单词III {

    public static void main(String[] args) {
        _01反转字符串中的单词III demo = new _01反转字符串中的单词III();
        String result = demo.reverseWords_x("Let's take LeetCode contest");
        System.out.println("result:" + result);
    }

    /**
     * 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例：
     * <p>
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     * <p>
     * 提示：
     * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     * //使用栈数据结构存储，+ 遍历
     */
    public String reverseWords_x(String s) {
        //在此处写入代码
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == ' ') {
                //出栈
                while (!stack.empty()) {
                    sb.append(stack.pop());
                }
                sb.append(ch);
            } else {
                stack.push(ch);
            }
        }
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
