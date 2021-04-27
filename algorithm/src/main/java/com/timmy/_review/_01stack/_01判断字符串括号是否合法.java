package com.timmy._review._01stack;

import java.util.Stack;

public class _01判断字符串括号是否合法 {

    public static void main(String[] args) {
        _01判断字符串括号是否合法 demo = new _01判断字符串括号是否合法();
//        boolean res = demo.isValid("()");
//        boolean res = demo.isValid("()()");
        boolean res = demo.isValid("(())");
//        boolean res = demo.isValid(")(");
//        boolean res = demo.isValid("()(");
        System.out.println("res:" + res);
    }

    /**
     * 因为栈数据结构保存的元素都是做括号的入栈与出栈，
     * -完全可以不用栈数据结构，通过使用int变量来表示左括号的数量
     * -最后计算左括号的数量多少
     */
    private boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        //奇数
        if (s.length() % 2 == 1) {
            return false;
        }
        int leftNum = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftNum++;
            } else {
                if (leftNum <= 0) {     //右括号匹配时，发现没有左括号了
                    return false;
                }
                leftNum--;
            }
        }
        return leftNum == 0;
    }


    /**
     * 1。理解题意
     * -输入的字符串又左右括号组成，求括号组成的字符串是否匹配合法
     * 2。模拟运行
     * -左右括号匹配既两两消除
     * 3。解题思路
     * -遍历字符串中的字符，使用栈数据结构进行保存，遍历到做括号字符入栈，遇到右括号，且栈顶元素是左括号，则出栈
     * 4。细节与边界问题
     * -只要是左括号则入栈，遇到右括号，在出栈前需要判断栈是否为空，为空特殊处理
     * -空字符是合法括号
     * -字符串个数为奇数，不合法
     * 5。复杂度分析
     * -时间复杂度：需要遍历字符串的每个元素-O(n)
     * -空间复杂度：使用栈保存数据，最坏情况保存所有元素-O(n)
     */
    private boolean isValid_v1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        //奇数
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {    //左括号入栈
                stack.push(ch);
            } else {
                //右括号出栈
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 字符串中只有字符'(' 和 ')' ，判断字符串括号是否合法
     */
}
