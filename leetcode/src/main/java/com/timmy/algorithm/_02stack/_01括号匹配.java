package com.timmy.algorithm._02stack;

import java.util.Stack;

public class _01括号匹配 {

    public static void main(String[] args) {
//        String str = "()";
//        String str = "()[]{}";
//        String str = "(]";
        String str = "([)]";
        boolean result = isValid(str);
        System.out.println("result:" + result);
    }

    /**
     * 括号是否匹配
     * 解题思路：
     * 1。遍历字符串，顺序拿到字符数组中的字符
     * 2。因为合法的括号字符串，是成对匹配出现的，所以有两种处理方式：
     * 2.1.遍历到左括号，则入栈；遍历到右括号，则取出栈顶元素，看是否匹配，匹配则出栈;看最后栈是否为空？
     * 2.2.遍历到左括号，则入栈对应的右括号；遍历到右括号，取出栈顶元素看是否相等；相等则出栈
     * 3。注意遍历过程中栈为空和栈顶元素不匹配的情况
     */
    private static boolean isValid(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(')');
            } else if (chars[i] == '{') {
                stack.push('}');
            } else if (chars[i] == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || chars[i] != stack.peek()) {        //遍历到右括号，发现栈为空或不匹配
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
