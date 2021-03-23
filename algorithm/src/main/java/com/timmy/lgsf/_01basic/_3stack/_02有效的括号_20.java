package com.timmy.lgsf._01basic._3stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _02有效的括号_20 {

    public static void main(String[] args) {
        _02有效的括号_20 demo = new _02有效的括号_20();
//        boolean res = demo.isValid("()[]{}");
//        boolean res = demo.isValid("([)]");
        boolean res = demo.isValid("{[]}");
//        boolean res = demo.isValid("(]");
        System.out.println("res:" + res);
    }

    /**
     * 优化解法： 栈顶+HashMap
     * 1。括号个数必须成对出现，2*n
     * 2。使用HashMap预先保存，括号匹配k-v值
     * 3。遍历字符串，当进入的是左括号-入栈，如果是右括号，则必须进行比较
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (map.containsKey(ch)) {   //右括号，进行匹配
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                //左括号
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 1。理解题意
     * -输入一个由括号组成的字符串s，判断字符串括号是否有效
     * -括号字符串有效条件：
     * --左括号顺序正确； 左右括号闭合
     * 2.解题思路
     * -遍历字符串，使用栈数据结构保存括号，如果进入的是左括号，则保存对应的右括号，
     * --如果进入的是右括号，则判断栈顶的元素与进入的括号是否相同，如果相同则出栈
     *
     * @param s
     * @return
     */
    public boolean isValid_v1(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (stack.isEmpty()) {
                stack.push(ch);
            } else if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (ch == '}' && stack.peek() == '{') {
                stack.pop();
            } else if (ch == ']' && stack.peek() == '[') {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *  
     * 示例 1：
     * 输入：s = "()"
     * 输出：true
     *
     * 示例 2：
     * 输入：s = "()[]{}"
     * 输出：true
     *
     * 示例 3：
     * 输入：s = "(]"
     * 输出：false
     *
     * 示例 4：
     * 输入：s = "([)]"
     * 输出：false
     *
     * 示例 5：
     * 输入：s = "{[]}"
     * 输出：true
     *  
     * 提示：
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     *
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     */
}
