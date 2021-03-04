package com.timmy.lgsf._04graph._6lovers_hands;

public class _03相似度为K的字符串_854 {

    public static void main(String[] args) {
        _03相似度为K的字符串_854 demo = new _03相似度为K的字符串_854();
//        int res = demo.kSimilarity("ab", "ba");
//        int res = demo.kSimilarity("abac", "baca");
//        int res = demo.kSimilarity("abdc", "bdca");
        int res = demo.kSimilarity("abcbca", "ababcc");
//        int res = demo.kSimilarity("abc", "bca");
        //"abcbca"
        //"ababcc"
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入两个字符串，A字符串中两个字符交换多次，变成字符串B，求一共交换了多少次？
     * 2。解题思路
     * 2。1。规律：以字符串A作为参考，当字符串A中第i个，和字符串B中的字符不一样时，则交换字符串B中的两个字符，使得B中的第i个字符与A中相等
     * 不断递归，并记录交换次数
     *
     * @param A
     * @param B
     * @return
     */
    public int kSimilarity(String A, String B) {
        int res = ks(A, B, 0);
        return res;
    }

    /**
     * @param a     参照字符串A
     * @param b     修改后的进行匹配的字符串B
     * @param index 当前比较的字符
     * @return
     */
    private int ks(String a, String b, int index) {
        if (index == a.length() - 1) {
            return 0;
        }
        //相等
        String aCh = a.substring(index, index + 1);
        String bCh = b.substring(index, index + 1);
        if (aCh.equals(bCh)) {   //相等不交换，进行下一个自负的比较
            return ks(a, b, index + 1);
        } else {
            StringBuilder sb = new StringBuilder(b);
            //先找到b中与aCh相等的字符，并进行交换后，是的第index位置的A，B相等，进行下一轮比较
            for (int i = index + 1; i < b.length(); i++) {
                if (b.substring(i, i + 1).equals(aCh) && !b.substring(i, i + 1).equals(a.substring(i, i + 1))) {
                    //B中字符，index，与i 位置的字符交换
                    sb.replace(index, index + 1, aCh);
                    sb.replace(i, i + 1, bCh);
                    break;
                }
            }
            return 1 + ks(a, sb.toString(), index + 1);
        }
    }

    /**
     * 如果可以通过将 A 中的两个小写字母精确地交换位置 K 次得到与 B 相等的字符串，
     * 我们称字符串 A 和 B 的相似度为 K（K 为非负整数）。
     *
     * 给定两个字母异位词 A 和 B ，返回 A 和 B 的相似度 K 的最小值。
     *
     * 示例 1：
     * 输入：A = "ab", B = "ba"
     * 输出：1
     *
     * 示例 2：
     * 输入：A = "abc", B = "bca"
     * 输出：2
     *
     * 示例 3：
     * 输入：A = "abac", B = "baca"
     * 输出：2
     *
     * 示例 4：
     * 输入：A = "aabc", B = "abca"
     * 输出：2
     *
     * 提示：
     * 1 <= A.length == B.length <= 20
     * A 和 B 只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母。
     *
     * 链接：https://leetcode-cn.com/problems/k-similar-strings
     */
}
