package com.timmy.lgsf._01basic._05string;

public class _01字符串匹配 {

    public static void main(String[] args) {
        String s = "goodgoogle";
        String t = "google";
        int index = findStr(s, t);
        System.out.println("index:" + index);
    }

    /**
     * 在主串中查找匹配串中出现的位置，如果没有匹配则返回-1
     * 解题思路：在主串中查找是否存在与模式串一样的字串，并返回模式串出现的下标位置
     * 1。主串遍历：
     * --判断主串遍历到的字符是否与模式串第一个字符是否相等
     * 2。不想等，则主串继续往后遍历
     * --相等，则主串与模式串同时往后便利，匹配
     * 3。最后遍历到模式串最后一个字符，则说明主串中存在模式串
     *
     * @param s 主串
     * @param t 模式串
     * @return
     */
    private static int findStr(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(0)) {
                int jc = 0;
                for (int j = 0; j < t.length(); j++) {
                    if (s.charAt(i + j) != t.charAt(j)) {
                        break;
                    }
                    jc = j;
                }
                if (jc == t.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }
}
