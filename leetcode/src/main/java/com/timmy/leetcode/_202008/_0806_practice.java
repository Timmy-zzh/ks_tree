package com.timmy.leetcode._202008;

import com.timmy.common.PrintUtils;

public class _0806_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
//        int[] nums1 = {1, 2};
//        int[] nums2 = {3, 4};
//        double result = findMedianSortedArrays(nums1, nums2);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");

//        System.out.println("-----------------------------------------");
////        String result = longestPalindrome("babad");
////        String result = longestPalindrome("cbbd");
//        String result = longestPalindrome("ac");
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");

        System.out.println("-----------------------------------------");
        String result = convert("LEETCODEISHIRING", 3);
//        String result = convert("AB", 1);
//        String result = convert("PAYPALISHIRING", 2);
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");
    }

    /**
     * Z 字形变换
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * <p>
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        //求列数
        int cols = (s.length() / (numRows * 2) + 1) * (numRows);
        char[][] status = new char[numRows][cols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < cols; j++) {
                status[i][j] = ' ';
            }
        }
        int tempRows = 0;//行
        int tempCols = 0;//列
        int strIndex = 0;//当前获取的字符
        char[] chars = s.toCharArray();
        boolean isDown = true;// 默认往下走
        while (strIndex < s.length()) {
            if (isDown) {       //往下
                if (tempRows < numRows - 1) {
                    status[tempRows++][tempCols] = chars[strIndex++];
                } else {    //往下到底了
                    isDown = false;
                    status[tempRows--][tempCols++] = chars[strIndex++];
                }
            } else {      //往上
                if (tempRows > 0) {
                    status[tempRows--][tempCols++] = chars[strIndex++];
                } else {
                    isDown = true;
                    status[tempRows++][tempCols] = chars[strIndex++];
                }
            }
        }
        PrintUtils.print(status);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < cols; j++) {
                if (status[i][j] != ' ') {
                    sb.append(status[i][j]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 1.两层for循环，内侧中判断字符串(i->j)是否是回文子串
     * 2。回文子串的判断
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     */
    private static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        String result = String.valueOf(chars[0]);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isHuiwenStr(chars, i, j) && (j - i + 1) > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    private static boolean isHuiwenStr(char[] chars, int i, int j) {
        int m = i, n = j;
        while (m <= j && n >= i) {
            if (chars[m] != chars[n]) {
                break;
            }
            m++;
            n--;
        }
        return m >= n;
    }

    /**
     * 求两个有序数组的中位数
     * 1。先归并排序为一个有序数组
     * 2。求出中位数 mid
     * <p>
     * nums1 = [1, 3]
     * nums2 = [2]
     * 则中位数是 2.0
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] addArr = new int[length1 + length2];
        int index = 0;
        int i = 0, j = 0;
        while (i < length1 || j < length2) {
            if (i == length1) {
                addArr[index++] = nums2[j++];
            } else if (j == length2) {
                addArr[index++] = nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                addArr[index++] = nums1[i++];
            } else {
                addArr[index++] = nums2[j++];
            }
        }
        PrintUtils.print(addArr);
        int mid = addArr.length / 2;
        System.out.println("mid:" + mid);
        if (addArr.length % 2 == 1) {   //数组长度为基数
            return addArr[mid];
        } else {
            return (addArr[mid - 1] + addArr[mid]) / 2.0;
        }
    }
}
