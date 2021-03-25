package com.timmy.lgsf._01basic._3stack;

import java.util.Stack;

public class _05逆波兰表示法 {

    public static void main(String[] args) {
//        String[] strArr = new String[]{"2", "1", "+", "3", "*"};
        String[] strArr = new String[]{"4", "13", "5", "/", "+"};
        String result = evalRPN(strArr);
        System.out.println("result:" + result);
    }

    /**
     * 计算机的计算方式：逆波兰表示法
     * 解题思路：
     * 1。便利字符，使用栈结构保存
     * 2。遇到数字则入栈，遇到运算符，栈顶元素出栈两个元素，然后运算操作后结果再入栈
     */
    private static String evalRPN(String[] strArr) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                int num1 = Integer.valueOf(stack.pop());
                int num2 = Integer.valueOf(stack.pop());
                if (str.equals("+")) {
                    stack.push(String.valueOf(num1 + num2));
                } else if (str.equals("-")) {
                    stack.push(String.valueOf(num2 - num1));
                } else if (str.equals("*")) {
                    stack.push(String.valueOf(num1 * num2));
                }
                if (str.equals("/")) {
                    stack.push(String.valueOf(num2 / num1));
                }
            } else {
                stack.push(str);
            }
        }
        return stack.pop();
    }
}
