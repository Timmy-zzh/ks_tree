package com.timmy.lgsf._06complex_scene._03dp_manacher;

public class _00最长回文子串_5_Mancher {

    public static void main(String[] args) {
        _00最长回文子串_5_Mancher demo = new _00最长回文子串_5_Mancher();
//        String res = demo.longestPalindrome_best("babad");
        String res = demo.longestPalindrome_best("abaaba");
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.Mancher 马拉车算法
     * 目的也是一样，将后续需要用到的计算量，前期先计算好了，后面循环时直接使用
     * 2.1.
     * -在原始字符串中插入字符，是的每次中位数计算时，都能得到奇位数的最长回文子串
     * -再使用一维数组作为备忘录
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int startIndex = 0, maxLen = 1;
        char[] chars = s.toCharArray();
        int N = chars.length;
        return s.substring(startIndex, startIndex + maxLen);
    }

    /**
     * Mancher算法：
     * 1。
     * 2。解题思路：mancher算法
     * -先对原始字符串进行特殊字符的插入，得到新的字符串数组：2*n+3
     * -对新字符数组进行遍历i，对每个字符i作为基准，进行中心扩展算法，使用result[i]表示字符i的最长回文子串的半径
     * --选定当前中心位置center及右边界right，以中心位置计算i的镜像位置i_mirror
     * --如果i不超过右边界，result[i]取right-i,与result[i_mirror]的较小值
     * --以i为中心，result[i]为半径再扩展比对，并更新result[i]的值
     * --如果i+result[i]超过右边界，则更新center和right值
     * -遍历result[i]找到最大的回文半径
     * -转换成原始数组，计算开始位置和最长回文串
     *
     * @param s
     * @return
     */
    public String longestPalindrome_best(String s) {
        char[] chars = s.toCharArray();
        char[] newChars = preProcess(chars);
        int N = newChars.length;
        //默认的中心点center
        int center = 0;
        //right表示以center为中心点，中心扩展的右边半径位置
        int right = 0;
        int[] result = new int[N];

        for (int i = 1; i < N - 1; i++) {
            //i的镜像位置
            int i_mirror = 2 * center - i;
            if (i < right) {
                result[i] = Math.min(right - i, result[i_mirror]);
            } else {
                result[i] = 0;
            }

            //中心扩展算法
            while (newChars[i + 1 + result[i]] == newChars[i - 1 - result[i]]) {
                result[i]++;
            }

            //更新center与right
            if (i + result[i] > right) {
                center = i;
                right = i + result[i];
            }
        }

        int maxLen = 0;
        int centerIndex = 0;
        //找出newChars数组中的最大半径
        for (int i = 0; i < N; i++) {
            if (result[i] > maxLen) {
                maxLen = result[i];
                centerIndex = i;
            }
        }
        //原始数组chars的开始位置
        int startIndex = (centerIndex - maxLen) / 2;
//        String str = new String(chars, startIndex, maxLen);
        return s.substring(startIndex, startIndex + maxLen);
    }

    //字符数组预处理，插入特殊字符
    private char[] preProcess(char[] chars) {
        int len = chars.length;
        char[] newChars = new char[2 * len + 3];
        int index = 0;
        newChars[index++] = '^';
        for (int i = 0; i < len; i++) {
            newChars[index++] = '#';
            newChars[index++] = chars[i];
        }
        newChars[index++] = '#';
        newChars[index++] = '$';
        return newChars;
    }


    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     * 示例 3：
     * 输入：s = "a"
     * 输出："a"
     *
     * 示例 4：
     * 输入：s = "ac"
     * 输出："a"
     *  
     * 提示：
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     *
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     */
}
