package com.timmy.dmsxl._03hashtable;

import java.util.HashMap;

/**
 * 三数之和与四数之和解法
 */
public class _02HashMap {

    public static void main(String[] args) {
        _02HashMap demo = new _02HashMap();
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
//        int count = demo.fourSumCount(A, B, C, D);
//        System.out.println("count:" + count);

//        boolean canConstruct = demo.canConstruct("aa", "aab");
        boolean canConstruct = demo.canConstruct("a", "b");
        System.out.println("canConstruct:" + canConstruct);
    }

    /**
     * 第454题.四数相加II
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，
     * 使得 A[i] + B[j] + C[k] + D[l] = 0。
     * <p>
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
     * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
     * <p>
     * 实现思路：四个数组，不要求去重，只要找到abcd四个数组元素只和等于0即可，
     * 4层for循环暴力破解
     * 2层for循环记录下元素只和与出现的次数
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i : A) {
            for (int j : B) {
                int sum = i + j;
                if (hashMap.containsKey(sum)) {
                    hashMap.put(sum, hashMap.get(sum) + 1);
                } else {
                    hashMap.put(sum, 1);
                }
            }
        }

        int count = 0;
        for (int i : C) {
            for (int j : D) {
                int sum2 = 0 - (i + j);
                //如果HashMap1中存在sum2的值，则表面abcd存在元素值为0的情况
                if (hashMap.containsKey(sum2)) {
                    count += hashMap.get(sum2);
                }
            }
        }
        return count;
    }

    /**
     * 第383题. 赎金信
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom
     * 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * <p>
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。
     * 杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     * <p>
     * 解题思路：字符a能不能有字符b组成，且b字符元素只能使用一次，小写字母
     * 可以先遍历字符b，记录每个字符出现的次数，然后遍历字符a，减去字符元素出现的次数，
     * 最后判断是否有次数<0的
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] letter = new int[26];
//        PrintUtils.print(letter);
        char[] chars = magazine.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            letter[chars[i] - 'a']++;
        }
        char[] chars1 = ransomNote.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            letter[chars1[i] - 'a']--;
        }
        for (int i : letter) {
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

}
