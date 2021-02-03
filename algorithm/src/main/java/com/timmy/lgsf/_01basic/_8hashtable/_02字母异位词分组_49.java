package com.timmy.lgsf._01basic._8hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _02字母异位词分组_49 {

    public static void main(String[] args) {
        _02字母异位词分组_49 demo = new _02字母异位词分组_49();
        String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        demo.groupAnagrams(Arrays.asList(strings));

    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     *
     * 链接：https://leetcode-cn.com/problems/group-anagrams
     */

    /**
     * 1。理解题意
     * -遍历字符集合，将每个字符中所有字符的和累加后的值（int），用HashMap保存下来
     * HashMap[int,List<String>]
     */
    public List<List<String>> groupAnagrams(List<String> strs) {
        List<List<String>> result = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int charNums = getCharNums(str);
            if (map.containsKey(charNums)) {
                List<String> list = map.get(charNums);
                list.add(str);
                map.put(charNums, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(charNums, list);
            }
        }
        System.out.println(map);
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        System.out.println(result);
        return result;
    }

    //获取字符串中每个字符累加的总和值
    private int getCharNums(String str) {
        int result = 0;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            result += aChar;
        }
        return result;
    }


}
