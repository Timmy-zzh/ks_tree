package com.timmy._review._09double_point;

public class _02替换后的最长重复字符_424 {

    public static void main(String[] args) {
        _02替换后的最长重复字符_424 demo = new _02替换后的最长重复字符_424();
//        int res = demo.characterReplacement("ABAB", 2);
//        int res = demo.characterReplacement("AABABBA", 1);
        int res = demo.characterReplacement("BAAAB", 2);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个字符串，字符串由大写字母组成，可以将字符串中的自负进行替换，最多可以替换k次，最后得到的子串最长
     * 2。解题思路
     * 双指针解法: 通过双指针控制区间，且区间中的字符最多有k个字符不同，
     * -右端指针固定移动，移动前后保证区间里面的字符最多k个不同，左侧根据区间不同字符的长度进行右移
     * -记录每个字符出现的次数，然后在检索区间范围内减去字符出现的次数，就可以计算出来有多少个不同的字符，
     * --当不同字符个数 大于k时，则区间左侧指针右移一位
     */
    public int characterReplacement(String s, int k) {
        System.out.println(s);
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = -1;
        int res = 0;
        int maxCharLength = 0;
        int[] count = new int[256];//   记录字符的个数
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            int ch = chars[right];
            count[ch]++;

            maxCharLength = Math.max(count[ch], maxCharLength);

            if (right - left - maxCharLength > k) {
                char leftCh = chars[++left];    //往右移动一位
                count[leftCh]--;                //之前的字符个数减少1
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
     * 在执行上述操作后，找到包含重复字母的最长子串的长度。
     * 注意：字符串长度 和 k 不会超过 104。
     *
     * 示例 1：
     * 输入：s = "ABAB", k = 2
     * 输出：4
     * 解释：用两个'A'替换为两个'B',反之亦然。
     *
     * 示例 2：
     * 输入：s = "AABABBA", k = 1
     * 输出：4
     * 解释：
     * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
     * 子串 "BBBB" 有最长重复字母, 答案为 4。
     *
     * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
     */
}
