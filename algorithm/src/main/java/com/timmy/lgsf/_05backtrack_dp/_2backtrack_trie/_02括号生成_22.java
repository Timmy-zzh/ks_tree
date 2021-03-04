package com.timmy.lgsf._05backtrack_dp._2backtrack_trie;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _02括号生成_22 {

    public static void main(String[] args) {
        _02括号生成_22 demo = new _02括号生成_22();
        List<String> res = demo.generateParenthesis(3);
        PrintUtils.printStr(res);
    }

    /**
     * -回溯算法
     * -在组合的过程中，动态的判断左右括号的数量，再进行添加，而不是全部添加完了，再进行判断
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, n, new StringBuilder(), 0, 0);
        return res;
    }

    /**
     * @param res
     * @param n     n对括号
     * @param sb    保存当前的括号组合
     * @param left  当前左括号的个数
     * @param right 当前右括号的个数
     */
    private void backtrack(List<String> res, int n, StringBuilder sb, int left, int right) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (left < n) {     //添加左括号
            sb.append("(");
            backtrack(res, n, sb, left + 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {    //添加右括号
            sb.append(")");
            backtrack(res, n, sb, left, right + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 1.理解题意
     * -输入数字n，生成n对括号，且生成的括号要是有效的括号组合
     * 2。解题思路 - 暴力法
     * -使用一个大小为2n的字符数组用于保存生成的括号组合，每次生成的时候，位置i可以填左/右括号，
     * --到最后填充满了2n时，判断生成的括号是否是有效的，有效的则保存到集合中
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_v1(int n) {
        List<String> res = new ArrayList<>();
        char[] chars = new char[2 * n];
        backtrack_v1(res, n, chars, 0);
        return res;
    }

    private void backtrack_v1(List<String> res, int n, char[] chars, int index) {
        if (index == 2 * n) {
            if (valid(chars)) {
                res.add(new String(chars));
            }
            return;
        }
        chars[index] = '(';
        backtrack_v1(res, n, chars, index + 1);
        chars[index] = ')';
        backtrack_v1(res, n, chars, index + 1);
    }

    /**
     * 判断括号组合是否是有效的？
     * 便利括号数组，使用一个int变量用户保存左右括号的总和
     * -如果是左括号++，是右括号--；
     * --在遍历的过程中，总和数<0;说明存在单个）右括号，不合法
     * -最后判断总和是否等于0
     *
     * @param chars
     * @return
     */
    private boolean valid(char[] chars) {
        int balance = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：["()"]
     *  
     * 提示：
     * 1 <= n <= 8
     *
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     */
}
