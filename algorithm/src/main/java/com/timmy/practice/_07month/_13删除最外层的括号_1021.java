package com.timmy.practice._07month;

import java.util.Stack;

public class _13删除最外层的括号_1021 {

    public static void main(String[] args) {
        _13删除最外层的括号_1021 demo = new _13删除最外层的括号_1021();
        String res = demo.removeOuterParentheses("(()())(())");
//        String res = demo.removeOuterParentheses("(()())(())(()(()))");
        System.out.println("res:" + res);
    }

    /**
     * 2。定义int index = 0;变量表示左右括号的数量
     * -遇到左括号index++, index>0 需要将遍历字符添加到结果集中
     * -遇到右括号index--, index>0 也是原语中的字符
     */
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '(') {
                if (index > 0) {
                    result.append(ch);
                }
                index++;
            } else {
                index--;
                if (index > 0) {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    /**
     * 2.使用栈进行数据保存
     * -遍历字符串，当遍历到左括号-入栈，遇到右括号-出栈，当栈为空时遍历位置为分解点，进行切分截取操作
     */
    public String removeOuterParentheses_v2(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int lastIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result.append(s.substring(lastIndex + 1, i));
                lastIndex = i + 1;
            }
        }
        return result.toString();
    }

    /**
     * 1.理解题意
     * -输入一个字符串，字符串由左右括号字符组成的有效括号字符串，现在对括号字符串进行原语分解，
     * --去除原语字符串的最外层括号，合并和返回
     * 2。解题思路
     * -先找到原始括号字符串分解的位置，得到原语字符串，去除外层括号后，进行拼接到StringBuild中
     * -分解位置的特点是左右括号相同，可以使用计数器记录左右括号的数量
     * --遍历到做括号计数器+1，遇到右括号计数器-1，当计数器为0时当前遍历位置为分解点
     * --分局分解位置进行字符串的截取，截取子串存放到sb中
     * 3。边界与细节问题
     */
    public String removeOuterParentheses_v1(String s) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        int lastIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                result.append(s.substring(lastIndex + 1, i));
                lastIndex = i + 1;
            }
        }
        return result.toString();
    }

    /**
     * 有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
     * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
     * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），
     * 其中 A 和 B 都是非空有效括号字符串。
     *
     * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，
     * 其中 P_i 是有效括号字符串原语。
     *
     * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
     *
     * 示例 1：
     * 输入：s = "(()())(())"
     * 输出："()()()"
     * 解释：
     * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
     *
     * 示例 2：
     * 输入：s = "(()())(())(()(()))"
     * 输出："()()()()(())"
     * 解释：
     * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
     *
     * 示例 3：
     * 输入：s = "()()"
     * 输出：""
     * 解释：
     * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
     * 删除每个部分中的最外层括号后得到 "" + "" = ""。
     *
     * 提示：
     * 1 <= s.length <= 105
     * s[i] 为 '(' 或 ')'
     * s 是一个有效括号字符串
     * 链接：https://leetcode-cn.com/problems/remove-outermost-parentheses
     */
}
