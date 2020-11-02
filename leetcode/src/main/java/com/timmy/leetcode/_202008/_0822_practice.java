package com.timmy.leetcode._202008;


public class _0822_practice {

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        _0822_practice practice = new _0822_practice();
        String result = practice.countAndSay(5);
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");
    }

    /**
     * 38. 外观数列
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     * 注意：整数序列中的每一项将表示为一个字符串。
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * <p>
     * 思路：使用递归，第n个数字表示的是n-1 个数字的含义
     */
    public String countAndSay(int n) {
        return realSay(1, "1", n);
    }

    /**
     * 遍历str 找到相同的字符，当遇到不相同的字符时，从1开始计算
     */
    private String realSay(int i, String str, int n) {
        System.out.println("i:" + i + " ,str:" + str);
        if (i == n) {
            return str;
        }
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (str.length() == 1) {
            sb.append(1);
            sb.append(1);
            return realSay(i + 1, sb.toString(), n);
        }

        int index = 1;
        for (int j = 1; j < chars.length; j++) {
            while (j < chars.length) {
                if (chars[j] == chars[j - 1]) {
                    index++;
                    if (j == chars.length - 1) {
                        sb.append(index);
                        sb.append(chars[j - 1]);
                        break;
                    }
                    j++;
                } else {
                    sb.append(index);
                    sb.append(chars[j - 1]);
                    index = 1;

                    if (j == chars.length - 1) {
                        sb.append(index);
                        sb.append(chars[j]);
                        break;
                    }
                    break;
                }
            }
        }
        return realSay(i + 1, sb.toString(), n);
    }
}
