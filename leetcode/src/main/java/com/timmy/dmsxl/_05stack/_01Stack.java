package com.timmy.dmsxl._05stack;

import java.util.Stack;

public class _01Stack {

    public static void main(String[] args) {
//        MyQueue myQueue = new MyQueue();
//        myQueue.push(1);
//        myQueue.push(2);
//        int peek = myQueue.peek();
//        System.out.println("peek:" + peek);
//        int pop = myQueue.pop();
//        System.out.println("pop:" + pop);
//        boolean empty = myQueue.empty();
//        System.out.println("isEmpty:" + empty);

//        MyStack myStack = new MyStack();
//        myStack.push(1);
//        myStack.push(2);
//        int pop = myStack.pop();
//        System.out.println("pop1:" + pop);
//        myStack.push(3);
//        myStack.push(4);
//
//        System.out.println("pop2:" + myStack.pop());
//        System.out.println("pop3:" + myStack.pop());
//        System.out.println("pop4:" + myStack.pop());
//
//        boolean empty = myStack.empty();
//        System.out.println("empty:" + empty);

        _01Stack demo = new _01Stack();
//        boolean valid = demo.isValid("()");
//        boolean valid = demo.isValid("()[]{}");
//        boolean valid = demo.isValid("(]");
//        boolean valid = demo.isValid("([)]");
//        System.out.println("result:" + valid);


        String result = demo.removeDuplicates("abbaca");
        System.out.println("result:" + result);
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 解题思路：
     * 1。使用栈进行存储，遍历字符串
     * 2。是左括号，就入栈对应右括号
     * 3。如果栈已经空了，说明之前的括号都匹配了，这是多出来的
     * 4。如果右括号相等，则出栈
     */
    private boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(')');
            } else if (chars[i] == '{') {
                stack.push('}');
            } else if (chars[i] == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || chars[i] != stack.peek()) {
                return false;
            } else {  //相等，出栈
                stack.pop();
            }
        }
        return stack.isEmpty();
    }


    /**
     * 1047. 删除字符串中的所有相邻重复项
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     * <p>
     * 示例：
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，
     * 这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，
     * 其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     * <p>
     * 解题思路：
     * 1。遍历字符串，使用栈进行保存
     * 2。与栈顶元素相同则出栈，不同则入栈
     * 3。返回结果需要栈元素反转
     */
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (!stack.isEmpty() && stack.peek() == chars[i]) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

}
