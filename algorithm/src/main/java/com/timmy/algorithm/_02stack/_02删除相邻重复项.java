package com.timmy.algorithm._02stack;

import java.util.Stack;

public class _02删除相邻重复项 {

    public static void main(String[] args) {
        String result = removeDuplicates("abbaca");
        System.out.println("result:" + result);
    }

    /**
     * 删除相邻重复项
     * 解题思路：
     * 1。遍历字符串中字符，
     * 2。遍历到第一个数组元素时，入栈，
     * 3。遍历到后面的字符时，判断当前字符与栈顶元素是否相等，相等则出栈
     */
    private static String removeDuplicates(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (!stack.isEmpty() && chars[i] == stack.peek()) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        char[] charArr = sb.toString().toCharArray();
        for (int i = 0, j = charArr.length - 1; i < charArr.length / 2; i++, j--) {
            char temp = charArr[i];
            charArr[i] = charArr[j];
            charArr[j] = temp;
        }
        return new String(charArr);
    }
}
