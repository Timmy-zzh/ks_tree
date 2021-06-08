package com.timmy._review._11backtracking;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _01电话号码的字母组合_17 {

    private String[] letters = {
            "",       //号码0，没有字母对应
            "",       //号码1
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz",
    };

    public static void main(String[] args) {
        _01电话号码的字母组合_17 demo = new _01电话号码的字母组合_17();
//        List<String> list = demo.letterCombinations("23");
        List<String> list = demo.letterCombinations("2");
        PrintUtils.printStr(list);
    }

    /**
     * 1。理解题意
     * -输入一个字符串，字符串由电话号码的纯数字组成，每个号码数字都对应着一个或多个字母，根据给到的电话号码，可以得到多少字母组合
     * 2。解题思路
     * 回溯
     * -最后的结果是一个集合List，所以需要一个集合对象保存满足条件的字母组合
     * -每次回溯路径都会产生一个组合，所以需要一个变量保存单条路径上的字母组合
     * -每当遍历到数字时去除对应的一个字母，然后开始往下层路径遍历，每次遍历完一层逻辑后，需要将之前添加的元素进行删除
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList();
        }
        System.out.println(digits);
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        letterComR(digits, 0, sb, res);
        return res;
    }

    /**
     * 单层递归的逻辑
     * 1。入参与返回值
     * 2。终止条件：遍历到字母最后位置
     * 3。单层递归逻辑：根据遍历到的号码，得到对应的字母映射，遍历每个不同的映射字母
     *
     * @param digits 收入的原始电话号码
     * @param i      当前遍历的号码位置
     * @param sb     保存单次遍历路径的字母组合
     * @param res    保存所有路径的字母组合
     */
    private void letterComR(String digits, int i, StringBuilder sb, List<String> res) {
        if (i == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char c = digits.charAt(i);  //得到当前遍历的号码数字
        int num = c - '0';
        String letter = letters[num];   //当前号码对应的字母

        for (int j = 0; j < letter.length(); j++) {
            sb.append(letter.charAt(j));
            letterComR(digits, i + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     *
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *  
     * 提示：
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     */
}
