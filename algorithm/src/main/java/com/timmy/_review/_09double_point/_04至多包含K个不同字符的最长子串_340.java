package com.timmy._review._09double_point;

import java.util.HashMap;

public class _04至多包含K个不同字符的最长子串_340 {

    public static void main(String[] args) {
        _04至多包含K个不同字符的最长子串_340 demo = new _04至多包含K个不同字符的最长子串_340();
//        int res = demo.lengthOfLongestSubstringKDistinct("eceba", 3);
        int res = demo.lengthOfLongestSubstringKDistinct("WORLD", 4);
        System.out.println("res:" + res);
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = -1;
        int res = 0;
        char[] chars = s.toCharArray();
        Counter counter = new Counter();

        for (int right = 0; right < chars.length; right++) {
            counter.add(chars[right], 1);

            while (counter.size() > k) {        // 区间内元素种类超过2，不断从左侧指针元素减少
                counter.add(chars[++left], -1);
            }
            res = Math.max(res, right - left);
        }

        return res;
    }

    public class Counter extends HashMap<Integer, Integer> {

        //求该元素对应存在的个数
        public int get(int ele) {
            return containsKey(ele) ? super.get(ele) : 0;
        }

        // 对该元素出现的次数，进行追加
        public void add(int ele, int incre) {
            put(ele, get(ele) + incre);
            if (get(ele) == 0) {
                remove(ele);
            }
        }
    }

    /**
     * 给定字符串S，找到最多有k个不同字符的最长子串T。
     * 样例 1:
     * 输入: S = "eceba" 并且 k = 3
     * 输出: 4
     * 解释: T = "eceb"
     *
     * 样例 2:
     * 输入: S = "WORLD" 并且 k = 4
     * 输出: 4
     * 解释: T = "WORL" 或 "ORLD"
     *
     * 挑战
     * O(n) 时间复杂度
     */
}
