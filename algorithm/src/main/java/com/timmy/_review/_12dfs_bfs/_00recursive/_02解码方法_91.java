package com.timmy._review._12dfs_bfs._00recursive;

import com.timmy.common.PrintUtils;

public class _02解码方法_91 {

    public static void main(String[] args) {
        _02解码方法_91 demo = new _02解码方法_91();
//        int res = demo.numDecodeings("12");
//        int res = demo.numDecodeings("226");
        int res = demo.numDecodeings("2101");
//        int res = demo.numDecodeings("111111111111111111111111111111111111111111111");
        System.out.println("res:" + res);
    }

    /**
     * 2。动态规划解法：
     * 状态转移方程式：
     * - int dp[i] 表示数字i的解码情况
     * - dp[0] = 1; 一个数字只有一种解码情况
     * - 如果i位置的数字是0，且i-1位置为1或者2，则dp[i]=dp[i-2];
     * - 如果i-1位置的数字是1，或者2时，dp[i]=dp[i-1] + dp[i-2];
     * - 其他情况： dp[i]=dp[i-1];
     *
     * @param s
     * @return
     */
    public int numDecodeings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int dp[] = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i - 1);
            if (curr == '0') {
                //如果当前数字是0，则前面必须是1或者2，才有解；否则数字不正确
                if (prev == '1' || prev == '2') {
                    dp[i] = i == 1 ? 1 : dp[i - 2];
                } else {
                    return 0;
                }
            } else if (prev == '1' || (prev == '2' && curr < '7')) {
                /**
                 * 当前数字不为0，且前一位数字为1，或者2，有两种组合
                 */
                dp[i] = i == 1 ? (dp[i - 1] + 1) : (dp[i - 1] + dp[i - 2]);
            } else {
                //当前数字大于0，且前一位数字大于2，解法和前一位数字相同
                dp[i] = dp[i - 1];
            }
        }
        PrintUtils.print(dp);
        return dp[N - 1];
    }

    /**
     * 1.理解题意
     * -26个大写英文字母编码成对应的26个数字，现在给出一串由数组组成的字符串，对其进行解码
     * 2。解题思路
     * 有点像动态规划-爬楼梯的类型题目：
     * -因为同样的一串数字，存在多种情况，多个不同的字母组合 编码成同样的数字字符串，计算一共有多少种组合情况
     * -使用递归思路：
     * --假如有n个数字（226）组成的字符串，n-1个数字的解码情况有m种可能，最后再加上数字6，解码的情况还是如前面一样，还是有m种情况
     * --再继续看6前面的数字，因为是2，因为26可以组合生成一个新的解码结果，
     * ---这样如果存在n-1和n的位置数字可以组合成一个新的字母，如果n-2之前的数字解码有k种结果，那么n个数字的解码情况有：m+k
     */
    public int numDecodeings_v1(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        return decode(s, s.length() - 1);
    }

    /**
     * 数字字符串解码的种类，与n-1个数字组合，和n-2个数字组合相关
     *
     * @param s
     * @param n
     * @return 获取字符串s的，前n个数字组合的个数
     */
    private int decode(String s, int n) {
        if (n <= 0) {       //只有一个数字，只有一种解码组合情况
            return 1;
        }
        char curr = s.charAt(n);
        char prev = s.charAt(n - 1);
        // n个数字组合的情况
        int count = 0;
        if (curr > '0') {  //如果当前数字大于0，则解码情况和 n-1前面的数字相同
            count = decode(s, n - 1);
        }

        /**
         * 如果前一位数字为1或2，则n-1和n个位置的数字可以进行组合，
         * 当前数字的组合，根据n-2个数字的解码组合情况和n-1的解码组合情况求解
         */
        if (prev == '1' || (prev == '2' && curr < '7')) {
            count = count + decode(s, n - 2);
        }
        return count;
    }

    /**
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     *
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
     * 例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A" ，从而得到 "AAA" ，
     * 或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。
     * 注意，"06" 不能映射为 "F" ，因为 "6" 和 "06" 不同。
     *
     * 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
     * 题目数据保证答案肯定是一个 32 位 的整数。
     *
     * 示例 1：
     * 输入：s = "12"
     * 输出：2
     * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
     *
     * 示例 2：
     * 输入：s = "226"
     * 输出：3
     * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     *
     * 示例 3：
     * 输入：s = "0"
     * 输出：0
     * 解释：没有字符映射到以 0 开头的数字。含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
     *
     * 示例 4：
     * 输入：s = "06"
     * 输出：0
     * 解释："06" 不能映射到 "F" ，因为字符串开头的 0 无法指向一个有效的字符。 
     *
     * 提示：
     * 1 <= s.length <= 100
     * s 只包含数字，并且可能包含前导零。
     *
     * 链接：https://leetcode-cn.com/problems/decode-ways
     */
}
