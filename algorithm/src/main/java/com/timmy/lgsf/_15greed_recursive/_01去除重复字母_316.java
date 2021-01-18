package com.timmy.lgsf._15greed_recursive;

import java.util.Stack;

public class _01去除重复字母_316 {


    public static void main(String[] args) {
        _01去除重复字母_316 demo = new _01去除重复字母_316();
//        String result = demo.removeDuplicateLetters("bcabc");
        String result = demo.removeDuplicateLetters("cbacdcbc");
        System.out.println("result:" + result);
    }

    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小
     * （要求不能打乱其他字符的相对位置）。
     * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
     * <p>
     * 示例 1：
     * 输入：s = "bcabc"
     * 输出："abc"
     * <p>
     * 示例 2：
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     * <p>
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
     */

    /**
     * 解题思路：贪心算法+栈
     * 根据局部最优解 得到 全局最优解
     * -使用栈保存数组中遍历到的字母，并保证栈中元素有序，从下到上的字母为升序
     * -遍历字符串
     * --如果当前栈为空，则字母入栈
     * --如果栈顶元素比遍历到的小，则字母入栈
     * --如果栈顶元素比遍历的字母大，则判断栈定元素的最后出现位置是否在后面还有出现，有出现则出栈
     * --如果遍历到的元素在栈中存在，则过滤掉
     */
    public String removeDuplicateLetters(String s) {
        //遍历保存每个字母最后出现的位置
        int length = s.length();
        int[] lastPos = new int[26];
        for (int i = 0; i < length; i++) {
            lastPos[s.charAt(i) - 'a'] = i;
        }
        //再次遍历数组，并用栈保存符合条件的字母
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(ch);
            } else if (!stack.contains(ch)) {        //该字母在栈中不存在
                //ch比栈顶元素大，入栈
                if (stack.peek() < ch) {
                    stack.push(ch);
                } else {
                    //ch比栈顶元素小，则判断是否需要出栈
                    while (!stack.isEmpty() && stack.peek() > ch && lastPos[stack.peek() - 'a'] > i) {
                        stack.pop();
                    }
                    //入栈
                    stack.push(ch);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        //反序输出栈中元素
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }


    /**
     * 1.理解题意
     * -输入一个字符串，去除字符串中重复字母是的每个字母出现一次，并且返回的字符串字典序最小，且不能改变顺序
     * -字典序最小，就是字母小的在前面出现
     * 2。解题思路：递归-每次获取到最小出现的字母返回（最后出现）
     * -遍历数组，记录每个字母在数组中出现的最后下表位置
     * -再次遍历数组，查找最小字母出现的位置，如果当前遍历的字母是最后出现的位置，则直接返回，不能再往后遍历了
     * -获取最小出现的字母，并截取后面的字符串进行递归获取后面的最小字母
     * 3。边界与细节问题
     * -获取最小字母出现的位置，遍历判断，并判断是否是最后出现的位置
     * -对最小字母后续的字符串进行截取，并进行递归操作，获取后面的最小字母
     */
    public String removeDuplicateLetters_v1(String s) {
        if (s.length() <= 1) {
            return s;
        }
        //1.获取每个字母最后出现的位置，定义int数组，大小为26，字母'a'在第一个位置，元素值对应该字母出现的最后下表位置
        int[] lastPos = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            lastPos[s.charAt(i) - 'a'] = i;
        }

        //2.获取最小字母出现的位置
        int litterPos = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(litterPos) > s.charAt(i)) {
                litterPos = i;
            }
            if (lastPos[s.charAt(litterPos) - 'a'] == i) {
                break;
            }
        }
        //3.获取最小字母，并进行截取
        String litterLetter = String.valueOf(s.charAt(litterPos));
        //后续用户递归的字符串
        String remindStr = s.substring(litterPos + 1).replace(litterLetter, "");
        return litterLetter + removeDuplicateLetters(remindStr);
    }

}
