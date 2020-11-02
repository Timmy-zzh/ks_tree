package com.timmy.dmsxl._04string;

public class _01ReverseString {

    public static void main(String[] args) {
        _01ReverseString demo = new _01ReverseString();
//        demo.reverseString("hello");
//        demo.reverseString("Hannah");

//        String result = demo.reverseStr("abcdefg", 2);
        String result = demo.reverseStr("abcd", 4);
        System.out.println("result:" + result);
    }

    /**
     * 题目：344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     * <p>
     * 示例 1：
     * 输入：["h","e","l","l","o"] 输出：["o","l","l","e","h"] 示例 2：
     * 输入：["H","a","n","n","a","h"] 输出：["h","a","n","n","a","H"]
     * <p>
     * 实现思路：
     * 1。双指针法，头尾指针
     * 2。for循环，遍历一半即可
     */
    private void reverseString1(String str) {
        char[] chars = str.toCharArray();
        int start = 0, end = chars.length - 1;
        char temp;
        while (start < end) {
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }
        System.out.println(String.valueOf(chars));
    }

    private void reverseString(String str) {
        char[] chars = str.toCharArray();
        char temp;
        for (int i = 0, j = chars.length - 1; i < chars.length / 2; i++, j--) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        System.out.println(String.valueOf(chars));
    }

    /**
     * 题目：541. 反转字符串II
     * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * <p>
     * 示例:
     * 输入: s = "abcdefg", k = 2
     * 输出: "bacdfeg"
     * <p>
     * 解题思路：for循环每次跳转间隔2k个，取前k个数据进行反转，处理后面的边界
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += (2 * k)) {
            if (i + k < chars.length) {
                // 字符串长度超过i+k个，可以将前k个都反转
                reverse(chars, i, i + k - 1);
                continue;
            }
            reverse(chars, i, chars.length - 1);
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        int mid = (end - start + 1) / 2;
        char temp;
        for (int i = start, j = end; i < start + mid; i++, j--) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }
}
