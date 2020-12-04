package com.timmy.leetcode._202008;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _0816_practice {

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        _0816_practice practice = new _0816_practice();
//        boolean result = practice.isValid("()[]{}");
//        boolean result = practice.isValid("([)]");
        boolean result = practice.isValid("((");
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");

    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 输入: "()[]{}"
     * 输出: true
     * <p>
     * 输入: "([)]"
     * 输出: false
     * <p>
     * 输入: "{[]}"
     * 输出: true
     * <p>
     * 解题思路：
     * 1.审题：输入时字符串，根据判定规则判断是否有效，输出boolean
     * 2.解题：使用两个栈结构进行数据保存，先将所有字符放入原始栈中，然后出栈，将右括号放入匹配栈中
     * 当遇到左括号时，两个栈结构都出栈进行比较，然后判断，知道两个栈为空代表成功
     * 3.异常考虑：输入为空
     */
    public boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }
        if (s.length() == 1) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> source = new Stack<>();
        Stack<Character> right = new Stack<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            source.push(chars[i]);
        }

        while (!source.isEmpty()) {
            Character popS = source.pop(); //出栈
            if (map.containsValue(popS)) {
                right.push(popS);
            } else {
                if (right.empty()) {
                    return false;
                }
                Character popR = right.pop();
                if (map.get(popS) != popR) {
                    return false;
                }
            }
        }
        return right.isEmpty();
    }
}
