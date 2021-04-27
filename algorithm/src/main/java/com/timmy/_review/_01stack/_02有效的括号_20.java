package com.timmy._review._01stack;


import java.util.HashMap;
import java.util.Stack;

public class _02有效的括号_20 {

    public static void main(String[] args) {
        _02有效的括号_20 demo = new _02有效的括号_20();
//        boolean res = demo.isValid("()[]{}");
//        boolean res = demo.isValid("(]");
//        boolean res = demo.isValid("([)]");
        boolean res = demo.isValid("{[]}");
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -由多种括号组成的字符串，判断括号是否有效，括号有效需要相同类型的括号两辆匹配
     * 2。模拟运行
     * -遍历字符串中的字符，遇到左括号入栈，
     * -遇到右括号，判断栈顶元素是否是相同类型的左括号，是则出栈消除，否则非法字符
     * 3。边界和细节问题
     * -字符串数量-（奇数处理）
     * -出栈时需要判断栈是否为空
     * -使用hasnmap包括：key-vlaue 对应 右括号-左括号
     * 4。复杂度分析
     * -时间：需要遍历所有元素-O（n）
     * -空间：栈可能保存所有元素-O（n）
     * 5。总结
     * -边界问题：出栈前需要判断数据是否为空
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        //奇数
        if (s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put(')', '(');
        hashMap.put('}', '{');
        hashMap.put(']', '[');

        //遍历字符
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (!hashMap.containsKey(ch)) {  //左括号-入栈
                stack.push(ch);
            } else {
                //右括号,必须与栈顶元素（左括号）匹配
                if (stack.isEmpty() || stack.peek() != hashMap.get(ch)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
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
