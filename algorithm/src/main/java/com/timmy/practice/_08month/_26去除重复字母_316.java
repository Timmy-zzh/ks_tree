package com.timmy.practice._08month;

import com.timmy.common.PrintUtils;

import java.util.Stack;

public class _26去除重复字母_316 {

    public static void main(String[] args) {
        _26去除重复字母_316 demo = new _26去除重复字母_316();
//        String res = demo.removeDuplicateLetters("bcabc");
        String res = demo.removeDuplicateLetters("cbacdcbc");
        System.out.println("res:" + res);
    }

    /**
     * 2.贪心算法+栈 解法
     * -不断遍历字符串中的字母，我们要找到字典序最小的字母并保存在栈中，最后倒序输出栈中的字母
     * --栈为空，字符入栈
     * --新字符，与栈顶元素比较，新元素更大入栈，
     * --栈顶元素更大，则while循环不断将栈顶元素出栈，最后将新元素入栈
     * --原始字符串存在重复的字符，需要防止二次添加到栈中，使用一个boolean[26]数组保存字母的存储状态
     * -使用int[26]数组保存每个字母最后出现的位置
     * 3.总结：
     * -贪心算法：不断遍历字符，在已遍历的字符中找出字典序最小的字符
     * -使用栈存储字符，其实是个单调栈
     */
    public String removeDuplicateLetters(String s) {
        System.out.println(s);
        int[] letters = new int[26];
        boolean[] box = new boolean[26];

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            letters[chars[i] - 'a'] = i;
        }
        for (int i = 0; i < len; i++) {
            char ch = chars[i];
            if (!box[ch - 'a']) {  //当前字母没有在栈中
                //与栈顶元素进行比较，判断是否需要删除栈顶元素
                while (!stack.isEmpty() && stack.peek() > ch && letters[stack.peek() - 'a'] > i) {
                    Character pop = stack.pop();
                    box[pop - 'a'] = false;
                }
                stack.push(ch);
                box[ch - 'a'] = true;
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }

    /**
     * 1.理解题意
     * -输入一个字符串，字符串中存在重复的字母，现在要求去除重复的字母使得每个字母只出现一次，并且字母的相对位置不能打乱
     * 2。解题思路
     * -先遍历字符串中每个字符，记录每个字母在字符串中最后出现的位置，使用一个26位的int数组即可保存
     * -再次遍历字符串中的字符，
     * --判断当前遍历的字符是否是最后出现的字母，如果是该字母需要截取出来
     * --不是最后出现的字母，说明当前遍历的字母在后面的位置还存在该字母，那就需要找出字符串开头位置的字母到当前位置的字母
     * ---中这一串子字符串中，找出字典许最小的字母（也就是26个字母中最靠前的字母）
     * --如何找最小字母，使用一个变量pos保存已遍历过的最小字母位置，后面位置的字母与前面的字母进行大小判断，如果新遍历的字母更小的话
     * ---则需要更新pos的位置
     * 3。整理
     * -遍历字符串，记录每个字母在字符串中存在的最后一个位置
     * -遍历字符串，查找最小排序的字母，并记录（包含当前字母)
     * -如果遇到当前字母出现的位置是最后位置时，则取最小字母并进行保存，
     * --然后进行截取以同样方式继续从后面的字符串中查找出最小字母出来
     */
    public String removeDuplicateLetters_v1(String s) {
        System.out.println("s:" + s);
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] letters = new int[26];
        //1。先保存字符串中字母在数组中最后出现的位置
        for (int i = 0; i < len; i++) {
            char ch = chars[i];
            letters[ch - 'a'] = i;
        }

        //2.在字母最后出现的位置前，找到字典序最小的字母位置
        int pos = 0;
        for (int i = 0; i < len; i++) {
            char ch = chars[i];
            //找到
            if (chars[pos] > ch) {
                pos = i;
            }
            if (letters[ch - 'a'] == i) {
                break;
            }
        }

        //3.找到最小字母，并进行截取后面的字母
        char litter = chars[pos];
        String remain = s.substring(pos + 1);
        //将最小字母剔除
        remain = remain.replaceAll("" + litter, "");
        return litter + removeDuplicateLetters_v1(remain);
    }

    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
     *
     * 示例 1：
     * 输入：s = "bcabc"
     * 输出："abc"
     *
     * 示例 2：
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     *
     * 提示：
     * 1 <= s.length <= 104
     * s 由小写英文字母组成
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
     */
}
