package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _02字母大小写全排列_784 {

    public static void main(String[] args) {
        _02字母大小写全排列_784 demo = new _02字母大小写全排列_784();
//        List<String> res = demo.letterCasePermutation("a1b2");
//        List<String> res = demo.letterCasePermutation("3z4");
        List<String> res = demo.letterCasePermutation("12345");
        PrintUtils.printStr(res);
    }

    /**
     * 1.理解题意
     * -输入一个字符串，字符串由字母和数字组成，求字符串对应的全排列，其中字母有大小写两种情况进行输出
     * 2。解题思路
     * -回溯算法：
     * -使用StringBuilder保存单条路径下的组合，并注意回溯
     * -从字符i=0开始递归遍历，获取当前i遍历的字符，判断是否字母，是字母的话需要两次遍历
     * -当遍历到字符串最后位置时返回
     * 1核心 3条件
     * -核心：第i个字符如何选
     * -3条件：满足结果，终止条件，下层递归
     */
    public List<String> letterCasePermutation(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        letterR(s, 0, sb, ans);
        return ans;
    }

    private void letterR(String s, int i, StringBuilder sb, List<String> ans) {
        //1.是否满足结果，和终止条件
        if (i == s.length()) {
            ans.add(sb.toString());
            return;
        }

        char ch = s.charAt(i);
        if ('0' <= ch && ch <= '9') {
            sb.append(ch);
            letterR(s, i + 1, sb, ans);
//            sb.deleteCharAt(sb.length() - 1);
            sb.setLength(sb.length() - 1);
        } else {
            sb.append(Character.toLowerCase(ch));
            letterR(s, i + 1, sb, ans);
            sb.setLength(sb.length() - 1);

            sb.append(Character.toUpperCase(ch));
            letterR(s, i + 1, sb, ans);
            sb.setLength(sb.length() - 1);
        }
    }

    /**
     * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
     *
     * 示例：
     * 输入：S = "a1b2"
     * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
     *
     * 输入：S = "3z4"
     * 输出：["3z4", "3Z4"]
     *
     * 输入：S = "12345"
     * 输出：["12345"]
     *  
     * 提示：
     * S 的长度不超过12。
     * S 仅由数字和字母组成。
     * 链接：https://leetcode-cn.com/problems/letter-case-permutation
     */
}
