package com.timmy.dmsxl._04string;

public class _02Replace {
    public static void main(String[] args) {
        _02Replace demo = new _02Replace();

//        String result = demo.replaceSpace("We are happy.");
//        System.out.println("result:" + result);

//        String result = demo.reverseWords("  hello world!  ");
//        String result2 = demo.reverseWords("a good   example");
//        System.out.println("result:" + result);
//        System.out.println("result2:" + result2);

        String result = demo.reverseLeftWords("abcdefg", 2);
        String result2 = demo.reverseLeftWords("lrloseumgh", 6);
        System.out.println("result:" + result);
        System.out.println("result2:" + result2);
    }

    /**
     * 题目：剑指Offer 05.替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * <p>
     * 解题思路：
     * 1。先找到有多少个空格，然后申请替换空格后的字符长度
     * 2。注意从后往前遍历，替换
     */
    private String replaceSpace(String s) {
        char[] charSour = s.toCharArray();
        int spacCount = 0;
        for (int i = 0; i < charSour.length; i++) {
            if (charSour[i] == ' ') {
                spacCount++;
            }
        }
        char[] resultChars = new char[charSour.length + 2 * spacCount];
        int j = resultChars.length - 1;
        for (int i = charSour.length - 1; i >= 0; i--) {
            if (charSour[i] == ' ') {
                resultChars[j--] = '0';
                resultChars[j--] = '2';
                resultChars[j--] = '%';
            } else {
                resultChars[j--] = charSour[i];
            }
        }
        return new String(resultChars);
    }

    /**
     * 题目：151.翻转字符串里的单词
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * <p>
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * <p>
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * <p>
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     * <p>
     * 解题思路：
     * 1。取出冗余空格：前面，后面，中间部分
     * 2。将整个字符串反转
     * 3。将单个单词反转
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        chars = removeSpac(chars);
        reverseChars(chars);
        System.out.println("chars:" + new String(chars));

        //将单词反转
        boolean enter = false;
        int start = 0, end;
        for (int i = 0; i < chars.length; i++) {
            if (!enter) {
                start = i;
                enter = true;
            }

            if (chars[i] == ' ') {
                end = i;
                reverse(chars, start, end - 1);
                start = i + 1;
            }

            if (i == chars.length - 1) {
                end = i;
                reverse(chars, start, end);
            }
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

    //字符串反转,双指针法
    private void reverseChars(char[] chars) {
        int i = 0, j = chars.length - 1;
        char temp;
        for (; i < chars.length / 2; i++, j--) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

    private char[] removeSpac(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length - 1 && chars[i] == chars[i + 1] && chars[i] == ' ') {
                continue;
            }
            sb.append(chars[i]);
        }
        String strTemp = sb.toString();
        //取出头部的空格
        if (strTemp.startsWith(" ")) {
            strTemp = strTemp.substring(1);
        }
        if (strTemp.endsWith(" ")) {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }
        System.out.println("strTemp:" + strTemp);
        return strTemp.toCharArray();
    }

    /**
     * 题目：剑指Offer58-II.左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * <p>
     * 示例 2：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     * 限制：
     * 1 <= k < s.length <= 10000
     * <p>
     * 解题思路：abcdefg 2 -->反转前2位，cdefgab
     * 1.先反转前面2位： bacdefg
     * 2.反转第3为到末尾的字符： bagfedc
     * 3.全部反转： cdefgab
     */
    private String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }
}
