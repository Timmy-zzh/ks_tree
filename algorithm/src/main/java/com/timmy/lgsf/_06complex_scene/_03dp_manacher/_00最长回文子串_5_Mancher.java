package com.timmy.lgsf._06complex_scene._03dp_manacher;

import com.timmy.common.PrintUtils;


public class _00最长回文子串_5_Mancher {

    public static void main(String[] args) {
        _00最长回文子串_5_Mancher demo = new _00最长回文子串_5_Mancher();
        String res = demo.longestPalindrome("babad");
//        String res = demo.longestPalindrome("abaaba");
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.Mancher 马拉车算法
     * 线性时间内查找字符串中任意位置开始的所有回文子串
     * 目的也是一样，将后续需要用到的计算量，前期先计算好了，后面循环时直接使用
     * 2.1.数据预处理
     * 解决奇数，偶数长度回文串：--》字符间插入特定字符
     * -例如原始字符串：abaaba
     * -存在奇数位回文子串：aba  --》通过插入字符#后变成：#a#b#a# --》最后变成7位进行判断
     * -存在偶数位回文子串：abaa -->                 :  #a#b#a#a# -->最后变成9位字符然后进行中卫扩展判断
     * --推导公式：任意长度n的回文子串，通过插入#字符后，长度变成：2*n+1
     * 2.2.解决头尾越界判断： 头尾添加特定字符
     * -abaaba --》 #a#b#a#a#b#a# --> ?#a#b#a#a#b#a#?  -->新字符串长度:2*n+3
     * 2.3.解决两层嵌套循环:  记录每个位置中心扩展的最大半径
     * -?#a#b#a#a#b#a#?
     * -001030161030100
     * --在中间的#字符,存在6长度的半径,且左右两边的中心位置的半径存在镜像关系的长度
     * 2.4.Mancher知识点
     * -利用对称镜像填充数组result[] : center- i_mirror = i - center
     * -回文长度换算:某位置最大回文半径=该位置最大回文串长度
     * -回文串原起始位置 = (该位置的新下标索引-该位置最大回文半径)/2
     * 2.5.Mancher思路步骤
     * -转成字符数组,添加特定字符
     * -遍历新数组,记录每个位置i的最长回文半径result[i]
     * --选定当前中心位置center及右边界right,以中心位置计算i的镜像位置i_mirror
     * --如果i不超过右边界,result[i]取right-i 与 result[i_mirror] 的较小值
     * --以i位中心,result[i] 位半径再扩展比对,并更新result[i]
     * --如果i+result[i]超过右边界,则更新center和right
     * -遍历result[i]找到最大的回文半径
     * -换算成原始字符串中起始位置及长度,返回最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        System.out.println(s);
        int startIndex = 0, maxLen = 1;
        char[] chars = s.toCharArray();
        //1.插入新字符，组成新的字符数组
        char[] newChars = preProcess(chars);
        int n = newChars.length;

        //状态转移方程：result数组元素值表示下标位置i的回文半径
        int[] result = new int[n];
        /**
         * Mancher算法的核心：遍历新的字符数组，计算每个字符回文串的最大半径，并保存在数组result中
         * 以下标i为中心，进行中心位置扩展判断，求以i为中心的回文串最大半径
         * 为了不用每次都进行中心位置扩展，通过result数组的值进行备忘录计算
         * --通过center，和right的值控制
         */
        result[0] = 0;
        int center = 0;
        int right = 0;
        for (int i = 1; i < n - 1; i++) {
            //下标i的镜像位置
            int i_mirror = 2 * center - i;
            if (i < right) {
                result[i] = Math.min(right - i, result[i_mirror]);
            } else {
                result[i] = 0;
            }
            //以i为中心进行扩展
            while (newChars[i - 1 - result[i]] == newChars[i + 1 + result[i]]) { //首位字符相等，半径增加
                result[i]++;
            }
            //更新center，和right的值
            if (i + result[i] > right) {
                center = i;
                right = i + result[i];
            }
        }
        PrintUtils.print(result);
        //求回文串最长长度和起始位置
        int centerIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > maxLen) {
                maxLen = result[i];
                centerIndex = i;
            }
        }
        startIndex = (centerIndex - maxLen) / 2;
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
