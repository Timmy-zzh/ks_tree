package com.timmy.lgsf._3stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _01删除最外层的括号_1021 {

    public static void main(String[] args) {
        _01删除最外层的括号_1021 demo = new _01删除最外层的括号_1021();
//        String result = demo.removeOuterParentheses("(()())(())");
        String result = demo.removeOuterParentheses("(()())(())(()(()))");
        System.out.println("result:" + result);
    }

    /**
     * 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
     * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
     * 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
     * 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
     * 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
     *  
     * 示例 1：
     * 输入："(()())(())"
     * 输出："()()()"
     * 解释：
     * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
     * <p>
     * 示例 2：
     * 输入："(()())(())(()(()))"
     * 输出："()()()()(())"
     * 解释：
     * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
     * <p>
     * 示例 3：
     * 输入："()()"
     * 输出：""
     * 解释：
     * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
     * 删除每个部分中的最外层括号后得到 "" + "" = ""。
     * <p>
     * 提示：
     * S.length <= 10000
     * S[i] 为 "(" 或 ")"
     * S 是一个有效括号字符串
     */

    /**
     * 解题思路：
     * 根据括号字符串的特性，因为括号是成对出现的，使用一个计数器
     * 1。遍历字符数组，遇到左括号，计数器加1 -- 遇到右括号，计数器减1
     * 2。根据计数器的值，进行判断是否添加到sb中
     * <p>
     * (()())(())
     */
    public String removeOuterParentheses(String S) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                if (count > 0) {
                    sb.append(c);
                }
                count++;
            } else {
                count--;
                if (count > 0) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 解题思路：栈解法
     * --使用栈数据结构进行数据保存
     * 1。遍历字符数组，左括号入栈，右括号出栈
     * 2。当栈内容为空时，说明找到了分割点--》分割-合并
     */
    public String removeOuterParentheses_v3(String S) {
        Stack<Character> stack = new Stack<>();
        int startSplit = 0;//分割点开始
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(chars[i]);
            } else {
                stack.pop();
            }

            if (stack.isEmpty()) {
                String splitStr = S.substring(startSplit + 1, i);
                sb.append(splitStr);
                startSplit = i + 1;
            }
        }
        return sb.toString();
    }

    /**
     * 解题思路：优化解法
     * 找到分割点后，直接进行分割截取，
     */
    public String removeOuterParentheses_v2(String S) {
        int left = 0, right = 0;
        int startSplit = 0;//分割点开始
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                String splitStr = S.substring(startSplit + 1, i);
                sb.append(splitStr);
                startSplit = i + 1;
            }
        }
        return sb.toString();
    }

    /**
     * 解题思路：
     * --找到括号字符串 分割的位置-- 然后进行分割成单独的一个个字符串 -- 再对分割后的字符串去处头尾括号 -- 然后再拼接即可
     * -- 特性：字符串只有左右括号();且都是对应匹配的
     * 1。遍历字符串，记录左右括号的个数，当左右括号个数相等时，说明找到了分割点
     * 2。根据分割点进行字符串截取，截取后保存到集合中，
     * 3。遍历集合去除最外层括号，拼接，得到最终结果
     */
    public String removeOuterParentheses_v1(String S) {
        List<String> list = new ArrayList<>();
        int left = 0, right = 0;
        int startSplit = 0;//分割点开始
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                String splitStr = S.substring(startSplit, i + 1);
                list.add(splitStr);
                startSplit = i + 1;
            }
        }

        //遍历分割后的字符串集合，去除最外层括号
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            String subStr = s.substring(1, s.length() - 1);
            sb.append(subStr);
        }
        return sb.toString();
    }
}
