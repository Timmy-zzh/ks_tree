package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 魔法字典，使用hashmap存储单词
 */
public class _13MagicDictionary_V2 {

    Map<Integer, List<String>> map;

    public _13MagicDictionary_V2() {
        map = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (String str : dictionary) {
            if (!map.containsKey(str.length())) {
                map.put(str.length(), new ArrayList<String>());
            }
            map.get(str.length()).add(str);
        }
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            PrintUtils.printStr(entry.getValue());
        }
    }

    public boolean search(String searchWord) {
        if (!map.containsKey(searchWord.length())) {
            return false;
        }
        List<String> list = map.get(searchWord.length());
        for (String str : list) {
            if (solveDiff(str, searchWord) == 1) {
                return true;
            }
        }
        return false;
    }

    //判断两个单词中不同的字符有多少个？
    private int solveDiff(String str, String searchWord) {
        int diff = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != searchWord.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}
