package com.timmy.lgsf._15greed_recursive;

import java.util.Stack;

public class _02使括号有效的最少添加_921 {

    public static void main(String[] args) {
        _02使括号有效的最少添加_921 demo = new _02使括号有效的最少添加_921();
//        int result = demo.minAddTo("())");
//        int result = demo.minAddTo("(((");
        int result = demo.minAddTo("()");
//        int result = demo.minAddTo("()))((");
        System.out.println("result:" + result);
    }

    /**
     * 给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），
     * 以使得到的括号字符串有效。
     * <p>
     * 从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
     * <p>
     * 它是一个空字符串，或者
     * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
     * 它可以被写作 (A)，其中 A 是有效字符串。
     * 给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
     * <p>
     * 示例 1：
     * 输入："())"
     * 输出：1
     * <p>
     * 示例 2：
     * 输入："((("
     * 输出：3
     * <p>
     * 示例 3：
     * 输入："()"
     * 输出：0
     * <p>
     * 示例 4：
     * 输入："()))(("
     * 输出：4
     * <p>
     * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     */

    /**
     * 1.理解题意
     * -添加最少的左括号或者右括号，最后使得括号字符串有效
     * 2。解题思路
     * -遍历字符串，使用栈进行保存，当有匹配的括号时，出栈，然后计算最后留下来的无效括号
     * 3。边界与细节问题
     * -遍历，遇到左括号压栈右括号，遇到右括号压栈左括号
     * -相同括号时出栈
     */
    private int minAddTo(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (stack.isEmpty()) {
                stack.push(ch);
            } else if (ch == ')' && stack.peek() == '(') {  //如果当前括号是右括号，切栈顶是左括号，出栈
                stack.pop();
            } else {
                //左括号，入栈
                stack.push(ch);
            }
        }
        return stack.size();
    }


}
